package tv.sporttotal.android.mobiletechcon

class J1_OperatorsTest {
    class MyCoolThing {
        operator fun plus(thing: MyCoolThing): MyCoolThing {
            return MyCoolThing()
        }
    }

    fun lets_do_something() {
        val oneThing = MyCoolThing()
        val oneMoreThing = MyCoolThing()

        val third = oneThing + oneMoreThing
    }
}