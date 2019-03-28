package tv.sporttotal.android.mobiletechcon

import android.view.View

class C_lets_shortenTest {

    val anything: Any = "hallo"

    fun doSomething() {
        if (anything is String) {
            //Smart cast
            val char = anything.get(0)
            //shorter
            val char2 = anything[0]
        }
        //TODO smart cast
        val char2 = (anything as String?)?.get(0)
    }

    lateinit var view: View
    fun somethingWithAView() {
        view.setOnClickListener { it.toString() }
    }

    lateinit var listener:View.OnClickListener
    fun butWhatAbout() {
        // since no getter the set method has to be called explicitly
        view.setOnClickListener(listener)
    }
}