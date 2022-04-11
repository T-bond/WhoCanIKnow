package hu.t_bond.whocaniknow.component.network

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER
import javax.inject.Inject

class ContactProviderImpl @Inject constructor() : ContactProvider {
    override fun getContacts(
        seed: String?,
        gender: GENDER?,
        nationalities: Set<NATIONALITY>,
        passwordCharacters: Set<CHARSETS>,
        passwordMinLength: Int?,
        passwordMaxLength: Int?,
        page: Int,
        limit: Int
    ): ContactsResult {
        TODO("Not yet implemented")
    }
}