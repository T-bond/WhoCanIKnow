package hu.t_bond.whocaniknow.component.network.model

import hu.t_bond.whocaniknow.component.network.model.contact.Contact

data class ContactsResult(
    val contacts: List<Contact>,
    val info: ResultInfo,
)