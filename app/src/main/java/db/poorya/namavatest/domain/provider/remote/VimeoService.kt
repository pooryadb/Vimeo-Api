package db.poorya.namavatest.domain.provider.remote

import db.poorya.namavatest.domain.model.remote.VideoConfigResponse
import db.poorya.namavatest.domain.model.remote.VideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VimeoService {

    @GET("videos")
    suspend fun searchVideo(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<VideosResponse>

    @GET("https://player.vimeo.com/video/{id}/config")
    suspend fun getVideoConfig(
        @Path(value = "id", encoded = true) id: Long,
    ): Response<VideoConfigResponse>

}