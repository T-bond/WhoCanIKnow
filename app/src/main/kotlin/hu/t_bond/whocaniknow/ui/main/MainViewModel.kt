package hu.t_bond.whocaniknow.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.t_bond.whocaniknow.component.contact.ContactService
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactService: ContactService,
) : ViewModel() {

    private val _contacts: LiveData<Map<Int, Contact>> =
        MutableLiveData(contactService.getContacts())

    private val _filter: MutableLiveData<String> = MutableLiveData("")

    fun getContacts(): LiveData<Map<Int, Contact>> = _contacts

    fun refreshContactList() {
        TODO("Not yet implemented.")
    }

}