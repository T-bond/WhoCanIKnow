package hu.t_bond.whocaniknow.component.network

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY
import hu.t_bond.whocaniknow.component.network.request.PasswordConfiguration

interface ContactProvider {

    fun getContacts(
        seed: String? = null,
        gender: GENDER? = null,
        nationalities: Set<NATIONALITY>? = null,
        passwordConfiguration: PasswordConfiguration? = null,
        page: Int? = null,
        limit: Int? = null,
    ): ContactsResult

}