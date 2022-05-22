package hu.t_bond.whocaniknow.component.contact

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.t_bond.whocaniknow.component.network.ContactProvider
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import hu.t_bond.whocaniknow.component.network.model.contact.ContactName
import hu.t_bond.whocaniknow.component.persistence.StorageManager
import javax.inject.Inject


class ContactServiceImpl @Inject constructor(
    private val contactProvider: ContactProvider,
    private val storageManager: StorageManager,
    @ApplicationContext
    private val applicationContext: Context,
) : ContactService {
    override suspend fun getContacts(filter: String?): Map<Int, Contact> {
        var contacts: ContactsResult? = null
        if (storageManager.hasDataForToday()) {
            contacts = storageManager.getDataForToday()
        }

        if (contacts == null) {
            contacts = contactProvider.getContacts(limit = 50)
            storageManager.saveDataForToday(contacts)
        }

        return contacts.contacts.filterByName(filter).mapIndexed { index, contact ->
            index to contact
        }.toMap()
    }

    override suspend fun getContact(id: Int): Contact? {
        return getContacts()[id]
    }

    override fun callContact(contact: Contact) {
        startActivity(
            applicationContext,
            Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${contact.cellphoneNumber.replace("[\\-()\\s]", "")}")
                flags = flags.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            }, Bundle.EMPTY
        )
    }

    private fun List<Contact>.filterByName(filter: String?): List<Contact> {
        if (filter == null || filter.isBlank()) {
            return this
        }

        return filter { contact ->
            contact.name.fullName.replace(" ", "")
                .contains(filter.replace(" ", ""), true)
        }
    }

    private val ContactName.fullName: String
        get() = "$title $firstName $lastName"
}