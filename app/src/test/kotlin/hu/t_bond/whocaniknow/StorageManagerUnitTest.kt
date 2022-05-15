package hu.t_bond.whocaniknow

import hu.t_bond.whocaniknow.component.persistence.StorageManagerImpl
import hu.t_bond.whocaniknow.component.persistence.repository.DailyContactsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.time.LocalDate


class StorageManagerUnitTest {

    private val dailyContactsRepository = mock(DailyContactsRepository::class.java)
    private val storageManager = StorageManagerImpl(dailyContactsRepository)

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
}