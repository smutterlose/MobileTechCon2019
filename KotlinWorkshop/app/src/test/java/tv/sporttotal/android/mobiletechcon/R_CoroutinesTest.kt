package tv.sporttotal.android.mobiletechcon

import kotlinx.coroutines.*

class R_CoroutinesTest {
    fun calculate(value: Int): Deferred<Int> {
        return GlobalScope.async {
            Thread.sleep(2000)
            value * value
        }
    }

    suspend fun doIt() {
        val result = calculate(100).await()
        withContext(Dispatchers.Main) {
           //e.g. post result  on MainThread
        }
    }

    init {
    }
}