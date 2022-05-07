package hu.t_bond.whocaniknow.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.t_bond.whocaniknow.component.contact.ContactService
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactService: ContactService,
) : ViewModel() {

    private val _contacts: LiveData<Map<Int, Contact>> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val contacts = contactService.getContacts()

            emit(contacts)
        }

    private val _hasDataAvailable: MutableLiveData<Boolean> = MutableLiveData(false)

    val filter: MutableLiveData<String> = MutableLiveData("")

    fun getContacts(): LiveData<Map<Int, Contact>> = _contacts

    fun getDataAvailable(): LiveData<Boolean> = _hasDataAvailable

    fun refreshContactList() {
        TODO("Not yet implemented.")
    }

}