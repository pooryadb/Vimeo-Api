package db.poorya.namavatest.domain.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import db.poorya.namavatest.base.other.ApiLoadingModel
import db.poorya.namavatest.base.other.AppApi
import db.poorya.namavatest.base.other.AppLiveData
import db.poorya.namavatest.domain.model.local.VideoModel
import db.poorya.namavatest.domain.repository.AppRepository
import db.poorya.namavatest.ext.logD
import db.poorya.namavatest.ext.logE
import db.poorya.namavatest.utils.AppConfig
import db.poorya.namavatest.utils.ErrorHandler

class VideoDataSource(
    private val apiEnum: AppApi,
    private val appRepository: AppRepository,
    private val appLiveData: AppLiveData,
    private val searchText: String = ""
) : PagingSource<Int, VideoModel>() {

    var dispatchRetry: (() -> Unit)? = null
    var page = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoModel> {
        page = params.key ?: AppConfig.FIRST_PAGE_NUMBER
        return try {
            appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, true, page)
            val response = appRepository.searchVideo(searchText, page, AppConfig.ITEM_PER_PAGE)
            if (response.isSuccessful) {

                response.logD("PagingSource response")
                if (response.body() != null) {

                    appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, false, page)

                    val responseConverted = response.body()!!.data.map { data ->
                        VideoModel(
                            id = data.uri?.split("/")?.last()?.toLongOrNull() ?: -1,
                            title = data.name ?: "",
                            description = data.description ?: "",
                            thumbnailUrl = data.pictures?.baseLink ?: "",
                            duration = data.duration ?: 0,
                            comments = data.metadata?.connections?.comments?.total ?: 0,
                            likes = data.metadata?.connections?.likes?.total ?: 0,
                            views = data.stats?.plays ?: 0,

                            videoLink = data.playerEmbedUrl ?: ""
                        )
                    }

                    LoadResult.Page(
                        data = responseConverted,
                        prevKey = if (page == AppConfig.FIRST_PAGE_NUMBER) null else page - 1,
                        nextKey = if (response.body()!!.data.size < AppConfig.ITEM_PER_PAGE) null else page + 1
                    )
                } else {
                    ErrorHandler(
                        Pair(response.code(), response.errorBody()),
                        apiEnum,
                        appLiveData.errorApi,
                        page,
                        dispatchRetry
                    )
                    appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, null, page)

                    val error = Exception("Check Data, Body is null!").apply {
                        this.logE("PagingSource error1")
                    }
                    LoadResult.Error(error)
                }

            } else {
                ErrorHandler(
                    Pair(response.code(), response.errorBody()),
                    apiEnum,
                    appLiveData.errorApi,
                    page,
                    dispatchRetry
                )
                appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, null, page)

                val error = Exception("Check Data, unsuccessful response").apply {
                    this.logE("PagingSource error2")
                }
                LoadResult.Error(error)
            }

        } catch (exception: Exception) {
            ErrorHandler(exception, apiEnum, appLiveData.errorApi, page, dispatchRetry)
            appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, null, page)

            exception.logE("PagingSource error3")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VideoModel>): Int? = state.anchorPosition

}