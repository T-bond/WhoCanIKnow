package hu.t_bond.whocaniknow.component.network

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.ResultInfo
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY
import hu.t_bond.whocaniknow.component.network.request.PasswordConfiguration
import java.net.UnknownHostException
import javax.inject.Inject


class ContactProviderImpl @Inject constructor(
    private val api: ContactsAPI,
) : ContactProvider {

    companion object {
        private val emptyResponse = ContactsResult(
            emptyList(),
            ResultInfo("", 0, 1, "")
        )
    }

    override fun getContacts(
        seed: String?,
        gender: GENDER?,
        nationalities: Set<NATIONALITY>?,
        passwordConfiguration: PasswordConfiguration?,
        page: Int?,
        limit: Int?
    ): ContactsResult {
        val password = passwordConfigurationToParameter(passwordConfiguration)
        return try {
            val call = api.getUsers(seed, gender, nationalities, password, page, limit)

            call.execute().body() ?: emptyResponse
        } catch (e: UnknownHostException) {
            emptyResponse
        }
    }

    private fun passwordConfigurationToParameter(passwordConfiguration: PasswordConfiguration?): String? =
        passwordConfiguration?.run {

            val charsets = passwordCharacters.joinToString(",") {
                it.value
            }

            val lengthSimplified = setOf(passwordMinLength, passwordMaxLength)
                .filterNotNull()
            val size =
                if (lengthSimplified.isNotEmpty())
                    lengthSimplified.joinToString("-")
                else
                    null

            listOfNotNull(charsets, size).joinToString(",")
        }
}