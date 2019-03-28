package tv.sporttotal.android.mobiletechcon

class F1_no_more_buildersTest {
    class Document(val header: String, val footer: String, val body: String)

    init {
        //through named parameter the can be interchanged
        //! be careful when writing public interface and you change names
        Document(header = "", body = "", footer = "")
    }
}