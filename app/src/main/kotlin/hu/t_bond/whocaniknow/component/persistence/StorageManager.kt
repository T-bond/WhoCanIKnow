package hu.t_bond.whocaniknow.component.persistence

import hu.t_bond.whocaniknow.component.network.model.ContactsResult

interface StorageManager {

    fun hasDataForToday(): Boolean

    fun getDataForToday(): ContactsResult

    fun saveDataForToday(contactsResult: ContactsResult)

}