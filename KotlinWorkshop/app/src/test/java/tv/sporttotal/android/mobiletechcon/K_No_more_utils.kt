package tv.sporttotal.android.mobiletechcon

class K_No_more_utils {

    private fun String?.isNonNullOrEmpty(): Boolean = this?.isEmpty() ?: false

    val someString = ""

    init {
        someString.isNonNullOrEmpty()
    }
}