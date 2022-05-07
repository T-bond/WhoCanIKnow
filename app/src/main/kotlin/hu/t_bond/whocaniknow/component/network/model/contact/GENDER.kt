package hu.t_bond.whocaniknow.component.network.model.contact

import hu.t_bond.whocaniknow.component.network.converter.Parameterizable

enum class GENDER(private val gender: String) : Parameterizable {
    MALE("male"),
    FEMALE("female");

    override fun toParameter(): String = gender
}
