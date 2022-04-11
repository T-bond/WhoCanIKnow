package hu.t_bond.whocaniknow.component.network

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER

interface ContactProvider {

    fun getContacts(
        seed: String? = null,
        gender: GENDER? = null,
        nationalities: Set<NATIONALITY> = emptySet(),
        passwordCharacters: Set<CHARSETS> = emptySet(),
        passwordMinLength: Int? = null,
        passwordMaxLength: Int? = null,
        page: Int = 1,
        limit: Int = 5000,
    ): ContactsResult

}