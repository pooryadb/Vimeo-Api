package db.poorya.namavatest.base.other

import db.poorya.namavatest.utils.AppConfig

/**
 * trace api-requesting
 *
 * @param apiEnum request name
 * @param load
 *              true : request sending
 *              false: request answer received
 *              null: error occurred
 * @param page if api is pagination, then gives you page number
 */
data class ApiLoadingModel(
    val apiEnum: AppApi,
    val load: Boolean?,
    val page: Int = AppConfig.FIRST_PAGE_NUMBER
)