package hu.t_bond.whocaniknow.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.t_bond.whocaniknow.component.contact.ContactService
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val contactService: ContactService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    val contact: LiveData<Contact> = MutableLiveData(contactService.getContact(args.contactId))

    fun callContact() {
        TODO("Not yet implemented.")
    }

}