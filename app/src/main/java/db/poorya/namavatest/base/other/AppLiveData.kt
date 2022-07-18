package db.poorya.namavatest.base.other

import db.poorya.namavatest.utils.liveData.SingleLiveData

/**
 * useful application liveData vars
 * must init #Singleton
 * only [BaseViewModel] create this object
 */
object AppLiveData {

    var errorApi = SingleLiveData<ApiErrorModel>()
    val loadingApi = SingleLiveData<ApiLoadingModel>()
    val retryApi = SingleLiveData<MutableList<Pair<AppApi, (() -> Unit)?>>>()
    val cancelRetryApi = SingleLiveData<AppApi>()
    var retrievableApis = mutableListOf<Pair<AppApi, (() -> Unit)?>>()

}