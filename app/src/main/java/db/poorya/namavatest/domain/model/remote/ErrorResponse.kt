package db.poorya.namavatest.domain.model.remote

import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    @SerializedName("developer_message")
    val developerMessage: String? = null,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("error_code")
    val errorCode: Int? = null,
    @SerializedName("invalid_parameters")
    val invalidParameters: List<InvalidParameter?>? = null,
    @SerializedName("link")
    val link: Any? = null
) {
    data class InvalidParameter(
        @SerializedName("developer_message")
        val developerMessage: String? = null,
        @SerializedName("error")
        val error: String? = null,
        @SerializedName("error_code")
        val errorCode: Int? = null,
        @SerializedName("field")
        val `field`: String? = null
    )
}