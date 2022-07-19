package db.poorya.namavatest.utils

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import db.poorya.namavatest.base.other.ApiErrorModel
import db.poorya.namavatest.base.other.AppApi
import db.poorya.namavatest.domain.model.remote.ErrorResponse
import db.poorya.namavatest.ext.logD
import db.poorya.namavatest.ext.logE
import db.poorya.namavatest.utils.state.AppApiErrorEnum.*
import kotlinx.coroutines.CancellationException
import okhttp3.ResponseBody
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Handle all api-request-errors
 *
 * @constructor 1: response received
 * @constructor 2: exception occurred
 */
class ErrorHandler {

    /**
     * @param e         [e.first] : HTTP status code , [e.second] : response body
     * @param appApi    mirrored to [ApiErrorModel]
     * @param liveData  [ApiErrorModel]
     */
    constructor(
        e: Pair<Int, ResponseBody?>,
        appApi: AppApi,
        liveData: MutableLiveData<ApiErrorModel>,
        page: Int = AppConfig.FIRST_PAGE_NUMBER,
        dispatchRetry: (() -> Unit)? = null
    ) {
        try {
            val bodyString = e.second?.string()
            val response: ErrorResponse =
                Gson().fromJson(bodyString, ErrorResponse::class.java)
            response.logD("ErrorHandler 1")
            when (e.first) {
                400 -> {//bad request > toast all
                    liveData.value =
                        ApiErrorModel(
                            appApi, OnBadRequest, response.error, page
                        )
                }
                else -> {
                    liveData.value =
                        ApiErrorModel(
                            appApi, OnUnknownError, response.error, page
                        )
                }
            }
        } catch (e: Exception) {
            e.logE("ErrorHandler 2")
            liveData.value = ApiErrorModel(
                appApi, OnConnectionLost, null,
                page, dispatchRetry
            )
        }
    }

    /**
     * @param e
     * @param appApi    mirrored to [ApiErrorModel]
     * @param liveData  [ApiErrorModel]
     */
    constructor(
        e: Throwable,
        appApi: AppApi,
        liveData: MutableLiveData<ApiErrorModel>,
        page: Int = AppConfig.FIRST_PAGE_NUMBER,
        dispatchRetry: (() -> Unit)? = null
    ) {
        e.logE("ErrorHandler 3")
        if (e is ConnectException || e is UnknownHostException || e is IOException
            || (e.message != null && e.message!!.contains("Unable to resolve host"))
        ) {
            liveData.value = ApiErrorModel(
                appApi,
                OnConnectionLost,
                "OnConnectionLost_error",
                page, dispatchRetry
            )
        } else if (e is CancellationException) {
            "paging canceled".logE("ErrorHandler")
        } else {
            liveData.value = ApiErrorModel(
                appApi, OnUnknownError, null,
                page
            )
        }
    }

}
