package hu.t_bond.whocaniknow.component.network.model.contact

import com.google.gson.annotations.SerializedName
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY

data class Contact(
    val gender: GENDER,
    val name: ContactName,
    val location: ContactLocation,
    val email: String,
    @field:SerializedName("login")
    val credentials: ContactCredentials,
    @field:SerializedName("dob")
    val birthday: ContactPointInTime,
    val registered: ContactPointInTime,
    @field:SerializedName("phone")
    val phoneNumber: String,
    @field:SerializedName("cell")
    val cellphoneNumber: String,
    val id: ContactId,
    val picture: ContactPicture,
    @field:SerializedName("nat")
    val nationality: NATIONALITY,
)
