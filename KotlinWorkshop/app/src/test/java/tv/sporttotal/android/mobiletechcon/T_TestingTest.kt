package tv.sporttotal.android.mobiletechcon

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito.mock

class T_TestingTest {

    class Something {
        suspend fun calculate() {

        }
    }

    @Test
    fun test() {
        // gets the java class
        val javaStringClass = mock(String::class.java)
        // Mockito for Kotlin
        val something = mock<String> {
            on { length } doReturn 4
        }

        val myMock = mock<Something> {
            onBlocking { calculate() }
        }
    }

    @Test
    @DisplayName("Should call the API")
    fun should_call_the_api() {
        val string = "hallo"

        string `should be equal to` "hallo"
    }
}