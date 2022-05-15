package hu.t_bond.whocaniknow.component.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import java.time.LocalDate

@Entity
data class DailyContactsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: LocalDate,
    val contactsResult: ContactsResult,
)