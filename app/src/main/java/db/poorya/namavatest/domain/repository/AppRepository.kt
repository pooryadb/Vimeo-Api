package db.poorya.namavatest.domain.repository


import db.poorya.namavatest.base.architecture.BaseRepository
import db.poorya.namavatest.domain.provider.remote.VimeoService
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val vimeoService: VimeoService
) : BaseRepository() {

    fun searchVideo(
        query: String,
        page: Int,
        perPage: Int
    ) = callApi { vimeoService.searchVideo(query, page, perPage) }
}