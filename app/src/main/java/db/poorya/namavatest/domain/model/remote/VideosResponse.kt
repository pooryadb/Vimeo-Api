package db.poorya.namavatest.domain.model.remote

import com.google.gson.annotations.SerializedName
import db.poorya.namavatest.base.data.BaseResponseList
import db.poorya.namavatest.base.data.BaseResponseListData

class VideosResponse :
    BaseResponseList<VideosResponse.Data>() {

    data class Data(
        @SerializedName("uri")
        val uri: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("description")
        val description: String? = "",
        @SerializedName("player_embed_url")
        val playerEmbedUrl: String? = "",
        @SerializedName("duration")
        val duration: Long? = 0,
        @SerializedName("pictures")
        val pictures: Pictures? = null,
        @SerializedName("metadata")
        val metadata: MetaData? = null,
        ) : BaseResponseListData() {

        data class Pictures(
            @SerializedName("base_link")
            val baseLink: String = "",
        )

        data class MetaData(
            @SerializedName("connections")
            val connections: Connections? = null,
        ) {

            data class Connections(
                @SerializedName("comments")
                val comments: Comments? = null,
                @SerializedName("likes")
                val likes: Likes? = null
            ) {

                data class Comments(
                    @SerializedName("total")
                    val total: Long = 0
                )

                data class Likes(
                    @SerializedName("total")
                    val total: Long = 0
                )
            }

        }

    }

}