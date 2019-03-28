package tv.sporttotal.android.mobiletechcon

import tv.sporttotal.android.mobiletechcon.utils.SomeButtonView

class B_nullTest {

    init {
        // can be null because of '?'
        var someButton: SomeButtonView? = null
        someButton?.setPressed(true)

        var someString:String? = null
        val length = someString?.length ?:0
    }
}