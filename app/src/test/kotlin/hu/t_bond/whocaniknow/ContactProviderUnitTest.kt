package hu.t_bond.whocaniknow

import hu.t_bond.whocaniknow.component.network.ContactProviderImpl
import hu.t_bond.whocaniknow.component.network.ContactsAPI
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.ResultInfo
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.mock.Calls


class ContactProviderUnitTest {

    private val contactsAPI = mock(ContactsAPI::class.java)
    private val contractProvider = ContactProviderImpl(contactsAPI)
    private val defaultResultInfo = ResultInfo("random", 1, 0, "1.0.0")

    @Test
    fun `Can acquire contacts from network`() {
        // Arrange
        val expected = ContactsResult(
            emptyList(),
            defaultResultInfo,
        )
        `when`(contactsAPI.getUsers()).thenReturn(Calls.response(expected))

        // Act
        val actual = contractProvider.getContacts()

        // Assert
        assertEquals(expected, actual)
    }
}