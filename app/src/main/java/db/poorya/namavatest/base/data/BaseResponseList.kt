package db.poorya.namavatest.base.data

import com.google.gson.annotations.SerializedName

open class BaseResponseList<DATA : BaseResponseListData> {

    @SerializedName("data")
    open var data: List<DATA> = emptyList()

    @SerializedName("total")
    val total: String = ""
    fun total(): Int = total.toIntOrNull() ?: -1

    @SerializedName("page")
    val page: String = ""
    fun page(): Int = total.toIntOrNull() ?: -1

}