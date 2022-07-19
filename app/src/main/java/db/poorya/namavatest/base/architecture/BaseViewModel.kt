package db.poorya.namavatest.base.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import db.poorya.namavatest.base.other.ApiLoadingModel
import db.poorya.namavatest.base.other.AppApi
import db.poorya.namavatest.base.other.AppLiveData
import db.poorya.namavatest.utils.AppConfig
import db.poorya.namavatest.utils.ErrorHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    val appLiveData = AppLiveData

    fun <T : Any> callApiPaging(
        liveResult: MutableLiveData<PagingData<T>?>? = null,
        dataSource: PagingSource<Int, T>,
        onResponse: ((PagingData<T>?) -> Unit)? = null
    ) {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = AppConfig.ITEM_PER_PAGE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { dataSource }
            ).flow.cachedIn(viewModelScope).collectLatest {
                liveResult?.value = it
                onResponse?.invoke(it)
            }

        }
    }

    fun <T> callApi(
        apiEnum: AppApi,
        networkCall: Flow<Response<T>>,
        liveResult: MutableLiveData<T>? = null,
        onResponse: ((T) -> Unit)? = null
    ) {
        viewModelScope.launch {
            networkCall
                .onStart {
                    appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, true)
                }
                .catch {
                    it.printStackTrace()
                    appLiveData.loadingApi.value = ApiLoadingModel(apiEnum, null)
                    ErrorHandler(
                        it,
                        apiEnum,
                        appLiveData.errorApi
                    )
                }
                .collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            liveResult?.value = it
                            appLiveData.loadingApi.value =
                                ApiLoadingModel(apiEnum, false)
                            onResponse?.invoke(it)
                        }
                    } else {
                        appLiveData.loadingApi.value =
                            ApiLoadingModel(apiEnum, null)
                        ErrorHandler(
                            Pair(response.code(), response.errorBody()),
                            apiEnum,
                            appLiveData.errorApi
                        )
                    }
                }
        }

    }

}