package hu.t_bond.whocaniknow.ui.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.t_bond.whocaniknow.component.contact.ContactService
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val contactService: ContactService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    val contact: LiveData<Contact> =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val contact = contactService.getContact(args.contactId)

            emit(contact)
        }

    fun callContact() {
        TODO("Not yet implemented.")
    }

}