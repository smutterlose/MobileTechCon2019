package tv.sporttotal.android.mobiletechcon

import android.app.Activity
import android.os.Bundle
import android.view.View

class G2_lateTest: Activity() {
    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = findViewById(R.id.action_bar)
    }
}