package hu.t_bond.whocaniknow.component.network.request

import hu.t_bond.whocaniknow.component.network.converter.Parameterizable

enum class NATIONALITY(val value: String) : Parameterizable {
    AU("au"),
    BR("br"),
    CA("ca"),
    CH("ch"),
    DE("de"),
    DK("dk"),
    ES("es"),
    FI("fi"),
    FR("fr"),
    GB("gb"),
    IE("ie"),
    IR("ir"),
    NO("no"),
    NL("nl"),
    NZ("nz"),
    TR("tr"),
    US("us");

    override fun toParameter(): String = value
}