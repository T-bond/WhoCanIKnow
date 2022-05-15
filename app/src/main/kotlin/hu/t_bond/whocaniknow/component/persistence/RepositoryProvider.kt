package hu.t_bond.whocaniknow.component.persistence

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.t_bond.whocaniknow.component.persistence.repository.DailyContactsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryProvider {

    @Singleton
    @Provides
    fun providesDailyContactsRepository(@ApplicationContext applicationContext: Context): DailyContactsRepository =
        Room.databaseBuilder(
            applicationContext,
            DatabaseConfiguration::class.java, "contacts"
        ).build().dailContactsRepository()

}