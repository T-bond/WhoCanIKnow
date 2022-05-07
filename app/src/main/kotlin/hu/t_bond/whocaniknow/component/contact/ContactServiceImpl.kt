package hu.t_bond.whocaniknow.component.contact

import hu.t_bond.whocaniknow.component.network.ContactProvider
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import javax.inject.Inject

class ContactServiceImpl @Inject constructor(
    private val contactProvider: ContactProvider
) : ContactService {
    override suspend fun getContacts(filter: String?): Map<Int, Contact> {
        TODO("Not yet implemented")
    }

    override suspend fun getContact(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun callContact(contact: Contact) {
        TODO("Not yet implemented")
    }
}