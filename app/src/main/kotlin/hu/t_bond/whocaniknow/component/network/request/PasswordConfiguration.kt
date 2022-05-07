package hu.t_bond.whocaniknow.component.network.request

data class PasswordConfiguration(
    val passwordCharacters: Set<CHARSETS>,
    val passwordMinLength: Int? = null,
    val passwordMaxLength: Int? = null,
) {

    constructor(
        passwordCharacters: Set<CHARSETS>,
        passwordLength: Int
    ) : this(passwordCharacters, null, passwordLength)

    init {
        check(passwordCharacters.isNotEmpty()) { "At least one charset is required" }

        if (passwordMinLength != null) {
            check(passwordMinLength > 0) { "Password minimum length should be positive" }
            if (passwordMaxLength != null) {
                check(passwordMinLength <= passwordMaxLength) { "Password minimum length should be smaller then password maximum length" }
            }
        }

        if (passwordMaxLength != null) {
            check(passwordMaxLength > 0) { "Password maximum length should be positive" }
        }
    }

}