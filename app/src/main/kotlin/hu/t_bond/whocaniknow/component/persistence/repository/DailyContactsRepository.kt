package hu.t_bond.whocaniknow.component.persistence.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.t_bond.whocaniknow.component.persistence.entity.DailyContactsEntity
import java.time.LocalDate

@Dao
interface DailyContactsRepository {

    @Query("SELECT * FROM DailyContactsEntity WHERE date = :date LIMIT 1")
    fun findByDate(date: LocalDate): DailyContactsEntity?


    @Query("SELECT * FROM DailyContactsEntity WHERE date < :beforeDate")
    fun findOutdated(beforeDate: LocalDate): List<DailyContactsEntity>

    @Insert
    fun insertAll(vararg dailyContacts: DailyContactsEntity)

    @Delete
    fun delete(dailyContacts: DailyContactsEntity)


}