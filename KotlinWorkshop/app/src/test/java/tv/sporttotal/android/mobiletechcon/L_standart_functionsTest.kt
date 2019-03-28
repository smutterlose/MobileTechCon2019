package tv.sporttotal.android.mobiletechcon

import tv.sporttotal.android.mobiletechcon.utils.BayernLiga

class L_standart_functionsTest {

    init {
        val league: BayernLiga = BayernLiga.getInstance()

        val game = league.nextGame
        // shorten code
        //.apply, .let, .run, .also und with()
//        game.setLiveStream("http://...")
//        game.setPublished(true)
        game.apply {
            setLiveStream("http://..")
            setPublished(true)
        }
    }
}