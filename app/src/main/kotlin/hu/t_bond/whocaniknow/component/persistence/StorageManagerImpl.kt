package hu.t_bond.whocaniknow.component.persistence

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.persistence.entity.DailyContactsEntity
import hu.t_bond.whocaniknow.component.persistence.repository.DailyContactsRepository
import java.time.LocalDate
import javax.inject.Inject

class StorageManagerImpl @Inject constructor(
    private val dailyContactsRepository: DailyContactsRepository
) : StorageManager {

    override fun hasDataForToday(): Boolean {
        val data = dailyContactsRepository.findByDate(LocalDate.now())

        return data != null
    }

    override fun getDataForToday(): ContactsResult? {
        return dailyContactsRepository.findByDate(LocalDate.now())?.contactsResult
    }

    override fun saveDataForToday(contactsResult: ContactsResult) {
        val today = LocalDate.now()

        dailyContactsRepository.insertAll(
            DailyContactsEntity(
                date = today,
                contactsResult = contactsResult,
            )
        )

        dailyContactsRepository.findOutdated(today).forEach {
            dailyContactsRepository.delete(it)
        }
    }
}