package hu.t_bond.whocaniknow.component.persistence.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import hu.t_bond.whocaniknow.component.network.converter.UriTypeHierarchyAdapter
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import java.time.LocalDate

class Converters {

    private val gsonConfiguration = Gson().newBuilder().apply {
        registerTypeHierarchyAdapter(Uri::class.java, UriTypeHierarchyAdapter())
    }.create()

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(value) }
    }

    @TypeConverter
    fun dateToEpochDay(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun fromContactsResult(value: String?): ContactsResult? {
        return value?.let { gsonConfiguration.fromJson(value, ContactsResult::class.java) }
    }

    @TypeConverter
    fun contactsResultToJson(contactsResult: ContactsResult?): String? {
        return gsonConfiguration.toJson(contactsResult)
    }
}