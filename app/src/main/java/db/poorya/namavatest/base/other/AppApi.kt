package db.poorya.namavatest.base.other

interface AppApi {
    fun retryType(): RetryEnum
    fun loadingType(): LoadingEnum
    fun isPaging(): Boolean
}

enum class LoadingEnum {
    Transparent,
    Colored,
    Glassy,
    Hide
}

/**
 * how **cancel btn** should act
 */
enum class RetryEnum(val priority: Int) {
    /**
     * on background api
     */
    NoRetrying(3),

    /**
     * handle dismiss in api-caller fragment
     */
    Default(2),

    /**
     * call [android.app.Activity.onBackPressed]
     */
    BackPress(1),

    /**
     * exit app
     */
    Required(0)
    ;

    companion object {
        fun getByPriority(priority: Int): RetryEnum =
            values().firstOrNull { it.priority == priority } ?: Default
    }
}