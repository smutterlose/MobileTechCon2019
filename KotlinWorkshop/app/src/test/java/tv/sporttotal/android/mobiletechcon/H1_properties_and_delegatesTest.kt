package tv.sporttotal.android.mobiletechcon

class H1_properties_and_delegatesTest {

    class SomeGame {
        // getter hides private method
        var live = false
        get() = calculateIfGameLive()

        private fun calculateIfGameLive(): Boolean {
            return true
        }
    }
}