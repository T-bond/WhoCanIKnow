package hu.t_bond.whocaniknow.component.persistence

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PersistenceComponent {

    @Binds
    abstract fun bindStorageManager(storageManagerImpl: StorageManagerImpl): StorageManager

}