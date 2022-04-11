package hu.t_bond.whocaniknow.model.contact

import java.util.*

data class ContactCredentials(
    val uuid: UUID,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
)
