package db.poorya.namavatest.base.other

import db.poorya.namavatest.utils.AppConfig

/**
 * trace api-requests when error occurred
 *
 * @param apiEnum       request name
 * @param errorApiEnum  error name
 * @param msg           error data (mostly returns description from server)
 * @param page if api is pagination, then gives you page number
 */
data class ApiErrorModel(
    val apiEnum: AppApi,
    val errorApiEnum: AppErrorApi,
    val msg: String?,
    val page: Int = AppConfig.FIRST_PAGE_NUMBER,
    var dispatchRetry: (() -> Unit)? = null
) {

    override fun toString(): String {
        return "ApiErrorModel(" +
                "apiEnum: $apiEnum" +
                ", errorApiEnum: $errorApiEnum, " +
                "msg: $msg, page: $page)"
    }
}