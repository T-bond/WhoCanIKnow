package hu.t_bond.whocaniknow.component.persistence

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import javax.inject.Inject

class StorageManagerImpl @Inject constructor() : StorageManager {
    override fun hasDataForToday(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDataForToday(): ContactsResult {
        TODO("Not yet implemented")
    }

    override fun saveDataForToday(contactsResult: ContactsResult) {
        TODO("Not yet implemented")
    }
}