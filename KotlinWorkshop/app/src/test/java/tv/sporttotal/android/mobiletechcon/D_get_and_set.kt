package tv.sporttotal.android.mobiletechcon

import android.view.View

class D_get_and_set {
    lateinit var view:View

    fun doSomething() {
        //implicit setter
        view.visibility = View.GONE
    }
}