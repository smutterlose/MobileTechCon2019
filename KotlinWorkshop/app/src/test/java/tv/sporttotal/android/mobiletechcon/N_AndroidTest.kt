package tv.sporttotal.android.mobiletechcon

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.mainlayout.*

class N_AndroidTest :Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //see build.gradle  -> apply plugin: 'kotlin-android-extensions'
        // inject view from layout xml
        myTextView.text = ""
    }
}