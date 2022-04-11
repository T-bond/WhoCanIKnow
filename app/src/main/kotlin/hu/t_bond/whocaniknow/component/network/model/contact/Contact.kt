package hu.t_bond.whocaniknow.component.network.model.contact

data class Contact(
    val gender: GENDER,
    val name: ContactName,
    val location: ContactLocation,
    val timezone: ContactTimezone,
    val email: String,
    val credentials: ContactCredentials,
    val birthday: ContactPointInTime,
    val registered: ContactPointInTime,
    val phoneNumber: String,
    val cellphoneNumber: String,
    val id: ContactId,
    val picture: ContactPicture,
    val nationality: String,
)
