package tv.sporttotal.android.mobiletechcon

class M_Collection_functionsTest {
    val items: List<String> = listOf("1", "2", "3", "A", "B", "C")

    init {
        val newList: List<Char> = items
            .map { it[0] }
            .filter { it.isDigit() }
    }
}