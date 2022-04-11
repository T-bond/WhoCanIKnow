package hu.t_bond.whocaniknow.component.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkComponent {

    @Binds
    abstract fun bindContactProvider(contactProviderImpl: ContactProviderImpl): ContactProvider

}