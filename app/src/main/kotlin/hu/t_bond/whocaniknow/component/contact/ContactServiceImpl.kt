package hu.t_bond.whocaniknow.component.contact

import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import javax.inject.Inject

class ContactServiceImpl @Inject constructor() : ContactService {
    override fun getContacts(filter: String?): Map<Int, Contact> {
        TODO("Not yet implemented")
    }

    override fun getContact(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun callContact(contact: Contact) {
        TODO("Not yet implemented")
    }
}