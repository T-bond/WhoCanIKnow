package hu.t_bond.whocaniknow

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import hu.t_bond.whocaniknow.component.network.model.ContactsResult
import hu.t_bond.whocaniknow.component.network.model.ResultInfo
import hu.t_bond.whocaniknow.component.network.model.contact.*
import hu.t_bond.whocaniknow.component.network.request.NATIONALITY
import hu.t_bond.whocaniknow.component.persistence.StorageManagerImpl
import hu.t_bond.whocaniknow.component.persistence.repository.DailyContactsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.argThat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTests {

    private val dailyContactsRepository = mock(DailyContactsRepository::class.java)
    private val storageManager = StorageManagerImpl(dailyContactsRepository)

    companion object {

        private val exampleContact = Contact(
            GENDER.MALE,
            ContactName("Dr.", "Teszt", "Elek"),
            ContactLocation(
                ContactLocationStreet("Test road", 42),
                "Test City",
                "Test State",
                "4242",
                ContactLocationCoordinates(41.0, 43.0),
                ContactLocationTimezone("+00:00", "Zulu")
            ),
            "test@example.org",
            ContactCredentials(
                UUID.fromString("a1597a56-ce38-4ce0-9de5-538f6c694fb0"),
                "TestElek",
                "testismylife",
                "salty",
                "97E48FF6882B3BACDA4BD5E5C0F4D1B8",
                "F3B2712A88E662A20464B307305D0C4A90662E82",
                "F31938749523CCB414784F2A7B51C3426E8C7BC98A5782BAFFBE4404F331085F"
            ),
            ContactPointInTime(
                SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("01-01-2000")!!,
                42
            ),
            ContactPointInTime(
                SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("01-01-2005")!!,
                37
            ),
            "+36990194121",
            "+36990194122",
            ContactId("id", "42"),
            ContactPicture(
                Uri.parse("https://randomuser.me/api/portraits/men/75.jpg"),
                Uri.parse("https://randomuser.me/api/portraits/med/men/75.jpg"),
                Uri.parse("https://randomuser.me/api/portraits/thumb/men/75.jpg")
            ),
            NATIONALITY.US,
        )
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("hu.t_bond.whocaniknow", appContext.packageName)
    }


    @Test
    fun `Checking saving data works`() {
        // Arrange
        val savableContactsResult = ContactsResult(
            listOf(exampleContact),
            ResultInfo("", 1, 1, "")
        )

        // Act
        storageManager.saveDataForToday(savableContactsResult)

        // Assert
        verify(dailyContactsRepository).insertAll(argThat {
            contactsResult == savableContactsResult
        })
    }
}