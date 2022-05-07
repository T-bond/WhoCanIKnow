package hu.t_bond.whocaniknow.component.network.model

import com.google.gson.annotations.SerializedName
import hu.t_bond.whocaniknow.component.network.model.contact.Contact

data class ContactsResult(
    @field:SerializedName("results")
    val contacts: List<Contact>,
    val info: ResultInfo,
)