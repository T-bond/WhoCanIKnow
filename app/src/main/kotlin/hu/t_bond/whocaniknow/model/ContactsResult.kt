package hu.t_bond.whocaniknow.model

import hu.t_bond.whocaniknow.model.contact.Contact

data class ContactsResult(
    val contacts: List<Contact>,
    val info: ResultInfo,
)