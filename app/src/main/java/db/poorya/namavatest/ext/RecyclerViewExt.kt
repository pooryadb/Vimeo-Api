package db.poorya.namavatest.ext

import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter

fun PagingDataAdapter<*, *>.pagingStates(listener: PagingStateListener) {

    addLoadStateListener { loadState ->

        if (loadState.refresh is LoadState.Error) {
            listener.onError((loadState.refresh as LoadState.Error).error)
            return@addLoadStateListener
        }

        val isEmpty =
            loadState.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    itemCount < 1
        "loadState: $loadState".logE("pagingStates")
        when {
            isEmpty -> listener.onEmpty()
            loadState.refresh is LoadState.Loading -> listener.onRefresh(false)
            loadState.append is LoadState.Loading -> listener.onAppend()
            else -> listener.onShowContent()
        }

    }

}

interface PagingStateListener {
    fun onEmpty() {

    }

    fun onAppend() {

    }

    /**
     * @param isFresh : when fresh data submitted ( page = 1)
     */
    fun onRefresh(isFresh: Boolean) {

    }

    fun onShowContent() {

    }

    fun onError(error: Throwable) {

    }
}