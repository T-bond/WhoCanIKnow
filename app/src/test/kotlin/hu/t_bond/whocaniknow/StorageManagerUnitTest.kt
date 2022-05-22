package hu.t_bond.whocaniknow

import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.ResultInfo
import hu.t_bond.whocaniknow.component.persistence.StorageManagerImpl
import hu.t_bond.whocaniknow.component.persistence.entity.DailyContactsEntity
import hu.t_bond.whocaniknow.component.persistence.repository.DailyContactsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.time.LocalDate


class StorageManagerUnitTest {

    private val dailyContactsRepository = mock(DailyContactsRepository::class.java)
    private val storageManager = StorageManagerImpl(dailyContactsRepository)

    companion object {
        private val emptyResponse = ContactsResult(
            emptyList(),
            ResultInfo("", 0, 1, "")
        )
    }

    @Test
    fun `Checking for non existing data for today works`() {
        // Arrange
        val today = LocalDate.now()
        `when`(dailyContactsRepository.findByDate(today)).thenReturn(null)

        // Act
        val actual = storageManager.hasDataForToday()

        // Assert
        assertEquals(false, actual)
    }

    @Test
    fun `Checking for existing data for today works`() {
        // Arrange
        val today = LocalDate.now()
        val dailyData = DailyContactsEntity(date = LocalDate.now(), contactsResult = emptyResponse)
        `when`(dailyContactsRepository.findByDate(today)).thenReturn(dailyData)

        // Act
        val actual = storageManager.hasDataForToday()

        // Assert
        assertEquals(true, actual)
    }

    @Test
    fun `Checking for querying existing data for today works`() {
        // Arrange
        val today = LocalDate.now()
        val expected = emptyResponse
        val dailyData = DailyContactsEntity(date = LocalDate.now(), contactsResult = expected)
        `when`(dailyContactsRepository.findByDate(today)).thenReturn(dailyData)

        // Act
        val actual = storageManager.getDataForToday()

        // Assert
        assertEquals(expected, actual)
    }

}