package hu.t_bond.whocaniknow.component.network.model.contact

data class ContactLocation(
    val street: String,
    val city: String,
    val state: String,
    val postcode: String,
    val coordinates: ContactLocationCoordinates
)
