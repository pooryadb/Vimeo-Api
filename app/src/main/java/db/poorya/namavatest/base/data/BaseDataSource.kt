package db.poorya.namavatest.base.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import db.poorya.namavatest.base.other.ApiLoadingModel
import db.poorya.namavatest.base.other.AppApi
import db.poorya.namavatest.base.other.AppLiveData
import db.poorya.namavatest.ext.logE
import db.poorya.namavatest.utils.AppConfig
import db.poorya.namavatest.utils.ErrorHandler
import retrofit2.Response

abstract class BaseDataSource<RESPONSE : BaseResponseList<DATA>, DATA : BaseResponseListData>(
    val apiEnum: AppApi,
    private val appLiveData: AppLiveData,
    private val onResponse: ((List<DATA>) -> Unit)? = null
) : PagingSource<Int, DATA>() {

    var dispatchRetry: (() -> Unit)? = null
    var page = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DATA> {
        page = params.key ?: AppConfig.FIRST_PAGE_NUMBER
        return try {
            appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, true, page)
            val response = getResponse(page, params.loadSize)
            if (response.isSuccessful) {

                response.logE("PagingSource response")
                if (response.body() != null) {

                    appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, false, page)

                    onResponse?.invoke(response.body()!!.data)

                    LoadResult.Page(
                        data = response.body()!!.data,
                        prevKey = if (page == AppConfig.FIRST_PAGE_NUMBER) null else page - 1,
                        nextKey = if (response.body()!!.data.size < AppConfig.ITEM_PER_PAGE) null else page + 1
                    )
                } else {
                    ErrorHandler(
                        Pair(response.code(), response.errorBody()),
                        apiEnum,
                        appLiveData.errorApi,
                        page, dispatchRetry
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
                    page, dispatchRetry
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

    override fun getRefreshKey(state: PagingState<Int, DATA>): Int? = state.anchorPosition

    abstract suspend fun getResponse(page: Int, limit: Int): Response<RESPONSE>
    var records: List<DATA>? = null
}