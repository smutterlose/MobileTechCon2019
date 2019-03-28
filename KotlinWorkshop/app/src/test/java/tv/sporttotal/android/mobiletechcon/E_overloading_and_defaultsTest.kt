package tv.sporttotal.android.mobiletechcon

class E_overloading_and_defaultsTest {
    enum class CacheStrategy {
        CACHE, NO_CACHE
    }

    //default value within constructor, also two private field declarations
    class Api (private val baseUrl: String,
               private val cacheStrategy: CacheStrategy = CacheStrategy.NO_CACHE)
}