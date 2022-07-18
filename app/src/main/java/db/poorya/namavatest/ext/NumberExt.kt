package db.poorya.namavatest.ext

fun Number.formatSecToTime(): String {
    val minute = this.toLong() / 60
    val second = this.toLong() % 60
    return "%02d:%02d".format(minute, second)
}