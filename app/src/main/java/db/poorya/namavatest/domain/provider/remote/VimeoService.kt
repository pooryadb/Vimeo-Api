package db.poorya.namavatest.domain.provider.remote

import db.poorya.namavatest.domain.model.remote.VideoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VimeoService {

    @GET("videos")
    suspend fun searchVideo(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<VideoList>

}