package hu.t_bond.whocaniknow.component.network

import android.net.Uri
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.t_bond.whocaniknow.component.network.converter.ParameterizableConverterFactory
import hu.t_bond.whocaniknow.component.network.converter.ParameterizableTypeHierarchyAdapter
import hu.t_bond.whocaniknow.component.network.converter.QueryParameterArrayTransformatorInterceptor
import hu.t_bond.whocaniknow.component.network.converter.UriTypeHierarchyAdapter
import hu.t_bond.whocaniknow.component.network.model.contact.GENDER
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ContactsAPIProvider {

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

    @Singleton
    @Provides
    fun providesRetrofitClient(): ContactsAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonConfiguration))
            .addConverterFactory(ParameterizableConverterFactory())
            .build().create(ContactsAPI::class.java)

}