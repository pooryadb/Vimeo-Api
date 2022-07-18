package db.poorya.namavatest.utils.state

import db.poorya.namavatest.base.other.AppApi
import db.poorya.namavatest.base.other.LoadingEnum
import db.poorya.namavatest.base.other.RetryEnum

enum class AppApiEnum(
    private val retryType: RetryEnum = RetryEnum.Default,
    private val loadingType: LoadingEnum = LoadingEnum.Transparent,
    private val isPaging: Boolean = false,
) : AppApi {
    Nothing,

    SearchVideo,

    ;

    override fun retryType(): RetryEnum = retryType
    override fun loadingType(): LoadingEnum = loadingType
    override fun isPaging(): Boolean = isPaging
}