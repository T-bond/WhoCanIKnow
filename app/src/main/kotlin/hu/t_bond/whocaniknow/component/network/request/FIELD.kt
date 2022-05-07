package hu.t_bond.whocaniknow.component.network.request

import hu.t_bond.whocaniknow.component.network.converter.Parameterizable

enum class FIELD(private val field: String) : Parameterizable {
    GENDER("gender"),
    NAME("name"),
    LOCATION("location"),
    EMAIL("email"),
    LOGIN("login"),
    REGISTERED("registered"),
    DOB("dob"),
    PHONE("phone"),
    CELL("cell"),
    ID("id"),
    PICTURE("picture"),
    NAT("nat");

    override fun toParameter(): String = field
}