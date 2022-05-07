package hu.t_bond.whocaniknow.component.network

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER
import hu.t_bond.whocaniknow.component.network.request.FIELD
import hu.t_bond.whocaniknow.component.network.request.FORMAT
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsAPI {

    /**
     * Your GET endpoint
     * Get a list of users
     * Responses:
     *  - 200: OK
     *  - 503: Service Unavailable
     *
     * @param seed The seed used for the random generator (optional)
     * @param gender Generated users' gender. Empty or invalid values will  generate from both sex (optional)
     * @param nationalities Nationality of generated user (optional)
     * @param password Generated passwords' parameters (optional)
     * @param page The page of the result list (optional)
     * @param limit The number of users to generate (optional, default to 1)
     * @param include Parameters to include (optional)
     * @param exclude Parameters to exclude (optional)
     * @param format The response format (optional, default to json)
     * @return [Call]<[ContactsResult]>
     */
    @GET(".")
    fun getUsers(
        @Query("seed") seed: String? = null,
        @Query("gender") gender: GENDER? = null,
        @Query(
            "nat",
            encoded = true
        ) nationalities: Iterable<@JvmSuppressWildcards NATIONALITY>? = null,
        @Query("password") password: String? = null,
        @Query("page") page: Int? = null,
        @Query("results") limit: Int? = null,
        @Query("inc") include: Iterable<@JvmSuppressWildcards FIELD>?? = null,
        @Query("exc") exclude: Iterable<@JvmSuppressWildcards FIELD>?? = null,
        @Query("format") format: FORMAT? = null
    ): Call<ContactsResult>

}