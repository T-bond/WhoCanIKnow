package hu.t_bond.whocaniknow.ui.main

import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.t_bond.whocaniknow.component.contact.ContactService
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer


@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactService: ContactService,
) : ViewModel() {

    private val TAG = javaClass.name
    private var job: Job? = null
    private val _contacts: MutableLiveData<Map<Int, Contact>> = MutableLiveData(emptyMap())

    private val _hasDataAvailable: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _lastData: LocalDateTime? = null
    private var dailyTimer: Timer? = null

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            if (!_hasDataAvailable.value!! && _contacts.value!!.isEmpty()) {
                refreshContactList()
            }
        }
    }

    var filter: String = ""
        set(value) {
            field = value

            refreshContactList()
        }

    init {
        refreshContactList()
    }

    fun getContacts(): LiveData<Map<Int, Contact>> = _contacts

    fun getDataAvailable(): LiveData<Boolean> = _hasDataAvailable

    fun refreshContactList() {
        job?.run {
            if (isActive) {
                return
            }
        }

        job = viewModelScope.launch(Dispatchers.IO) {
            _hasDataAvailable.postValue(true)
            val contacts = contactService.getContacts(filter)
            _contacts.postValue(contacts)
            val hasData = contacts.isNotEmpty()
            _hasDataAvailable.postValue(hasData)
            if (hasData) {
                _lastData = LocalDate.now().atStartOfDay()
                refreshDailyTriggers()
            }
        }
    }

    fun refreshDailyTriggers() {
        if (_lastData != null) {
            if (_lastData != LocalDate.now().atStartOfDay()) {
                Log.d(TAG, "refreshDailyTriggers: Date mismatch refresh.")
                refreshContactList()
            }
        }

        dailyTimer?.cancel()

        val tomorrow = Date.from(
            LocalDateTime.now().toLocalDate().atStartOfDay().plusDays(1)
                .atZone(ZoneId.systemDefault()).toInstant()
        )
        dailyTimer = timer("DailyRefresh", true, tomorrow, 86400000 /*24 h*/) {
            Log.d(TAG, "refreshDailyTriggers: Timed refresh.")
            refreshContactList()
        }
    }

}