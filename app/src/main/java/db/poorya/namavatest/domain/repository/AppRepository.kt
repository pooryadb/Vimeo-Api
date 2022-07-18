package db.poorya.namavatest.domain.repository


import db.poorya.namavatest.base.architecture.BaseRepository
import db.poorya.namavatest.domain.provider.remote.VimeoService
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val vimeoService: VimeoService
) : BaseRepository() {

    suspend fun searchVideo(
        query: String,
        page: Int,
        perPage: Int
    ) = vimeoService.searchVideo(query, page, perPage)

    fun getVideoConfig(
        videoId: Long
    ) = callApi { vimeoService.getVideoConfig(videoId) }
}