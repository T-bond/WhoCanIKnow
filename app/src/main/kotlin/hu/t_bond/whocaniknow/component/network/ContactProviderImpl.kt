package hu.t_bond.whocaniknow.component.network

import android.net.Uri
import com.google.gson.Gson
import hu.t_bond.whocaniknow.component.network.converter.ParameterizableConverterFactory
import hu.t_bond.whocaniknow.component.network.converter.ParameterizableTypeHierarchyAdapter
import hu.t_bond.whocaniknow.component.network.converter.QueryParameterArrayTransformatorInterceptor
import hu.t_bond.whocaniknow.component.network.converter.UriTypeHierarchyAdapter
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.ResultInfo
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY
import hu.t_bond.whocaniknow.component.network.request.PasswordConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ContactProviderImpl @Inject constructor() : ContactProvider {

    private companion object {
        private const val BASE_URL = "https://randomuser.me/api/"
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(QueryParameterArrayTransformatorInterceptor())
        .build()

    private val gsonConfiguration = Gson().newBuilder().apply {
        registerTypeHierarchyAdapter(Uri::class.java, UriTypeHierarchyAdapter())
        registerTypeHierarchyAdapter(
            GENDER::class.java,
            ParameterizableTypeHierarchyAdapter(GENDER::class.java)
        )
        registerTypeHierarchyAdapter(
            NATIONALITY::class.java,
            ParameterizableTypeHierarchyAdapter(NATIONALITY::class.java)
        )
    }.create()

    private val api: ContactsAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonConfiguration))
            .addConverterFactory(ParameterizableConverterFactory())
            .build().create(ContactsAPI::class.java)

    override fun getContacts(
        seed: String?,
        gender: GENDER?,
        nationalities: Set<NATIONALITY>?,
        passwordConfiguration: PasswordConfiguration?,
        page: Int?,
        limit: Int?
    ): ContactsResult {
        val password = passwordConfigurationToParameter(passwordConfiguration)
        val call = api.getUsers(seed, gender, nationalities, password, page, limit)

        return call.execute().body() ?: ContactsResult(
            emptyList(),
            ResultInfo(seed ?: "", 0, page ?: 1, "")
        )
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