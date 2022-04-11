package hu.t_bond.whocaniknow.component.contact

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ContactComponent {

    @Binds
    abstract fun bindContactService(contactServiceImpl: ContactServiceImpl): ContactService

}