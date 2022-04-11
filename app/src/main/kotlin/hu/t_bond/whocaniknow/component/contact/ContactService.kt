package hu.t_bond.whocaniknow.component.contact

import hu.t_bond.whocaniknow.component.network.model.contact.Contact

interface ContactService {

    fun getContacts(filter: String? = null): Map<Int, Contact>

    fun getContact(id: Int): Contact

    fun callContact(contact: Contact)

}