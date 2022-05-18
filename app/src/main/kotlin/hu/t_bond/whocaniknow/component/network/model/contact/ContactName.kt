package hu.t_bond.whocaniknow.component.network.model.contact

import com.google.gson.annotations.SerializedName

data class ContactName(
    val title: String,
    @field:SerializedName("first")
    val firstName: String,
    @field:SerializedName("last")
    val lastName: String,
) {

    fun fullName() = listOfNotNull(title, firstName, lastName).joinToString(" ")
}
