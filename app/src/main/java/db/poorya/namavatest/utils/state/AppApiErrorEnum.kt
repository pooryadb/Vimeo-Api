package db.poorya.namavatest.utils.state

import db.poorya.namavatest.base.other.AppErrorApi


enum class AppApiErrorEnum : AppErrorApi {
    OnUnknownError,
    OnConnectionLost,
    OnBadRequest,
}