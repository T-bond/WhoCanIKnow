package hu.t_bond.whocaniknow.component.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.t_bond.whocaniknow.component.persistence.converter.Converters
import hu.t_bond.whocaniknow.component.persistence.entity.DailyContactsEntity
import hu.t_bond.whocaniknow.component.persistence.repository.DailyContactsRepository

@Database(entities = [DailyContactsEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DatabaseConfiguration : RoomDatabase() {
    abstract fun dailContactsRepository(): DailyContactsRepository
}
