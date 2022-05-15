package hu.t_bond.whocaniknow.component.persistence

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.persistence.entity.DailyContactsEntity
import java.time.LocalDate
import javax.inject.Inject

class StorageManagerImpl @Inject constructor(
    @ApplicationContext applicationContext: Context
) : StorageManager {

    private val database = Room.databaseBuilder(
        applicationContext,
        DatabaseConfiguration::class.java, "contacts"
    ).build()


    override fun hasDataForToday(): Boolean {
        val data = database.dailContactsRepository().findByDate(LocalDate.now())

        return data != null
    }

    override fun getDataForToday(): ContactsResult? {
        return database.dailContactsRepository().findByDate(LocalDate.now())?.contactsResult
    }

    override fun saveDataForToday(contactsResult: ContactsResult) {
        val today = LocalDate.now()
        val repository = database.dailContactsRepository()

        repository.insertAll(
            DailyContactsEntity(
                date = today,
                contactsResult = contactsResult,
            )
        )

        repository.findOutdated(today).forEach {
            repository.delete(it)
        }
    }
}