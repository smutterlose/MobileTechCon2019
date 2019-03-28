package tv.sporttotal.android.mobiletechcon

class F3_sealed {
    sealed class LoadingState {
        class Loading : LoadingState()
        class Loaded : LoadingState()
        class Empty : LoadingState()
    }

    init {
        val state: LoadingState = LoadingState.Loading()

        val text = when (state) {
            is LoadingState.Loading -> "loading"
            is LoadingState.Loaded -> "loaded"
            is LoadingState.Empty -> "emmpty"
        }
    }
}