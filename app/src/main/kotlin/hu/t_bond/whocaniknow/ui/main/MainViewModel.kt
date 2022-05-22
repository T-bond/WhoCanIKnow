package hu.t_bond.whocaniknow.ui.main

import android.net.ConnectivityManager
import android.net.Network
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
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactService: ContactService,
) : ViewModel() {

    private var job: Job? = null
    private val _contacts: MutableLiveData<Map<Int, Contact>> = MutableLiveData(emptyMap())

    private val _hasDataAvailable: MutableLiveData<Boolean> = MutableLiveData(false)

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
            _hasDataAvailable.postValue(contacts.isNotEmpty())
        }
    }

}