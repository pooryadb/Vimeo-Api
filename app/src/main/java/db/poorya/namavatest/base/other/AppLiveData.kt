package db.poorya.namavatest.base.other

import db.poorya.namavatest.utils.liveData.SingleLiveData

/**
 * useful application liveData vars
 * must init #Singleton
 * only [BaseViewModel] create this object
 */
object AppLiveData {

    val errorApi = SingleLiveData<ApiErrorModel>()
    val loadingApi = SingleLiveData<ApiLoadingModel>()

}