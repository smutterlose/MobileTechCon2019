package tv.sporttotal.android.mobiletechcon

class H2_inheritanceTest {

    interface FeatureFlags {
        val chatEnabled: Boolean
        val videoEnabled: Boolean
    }

    class Defaults : FeatureFlags {
        override val chatEnabled = true
        override val videoEnabled = true
    }

    // overwrite only a part of the interface the rest is delegated
    // no extension needed
    class FireBaseFlags: FeatureFlags by Defaults(){
        override val videoEnabled = true

    }
}