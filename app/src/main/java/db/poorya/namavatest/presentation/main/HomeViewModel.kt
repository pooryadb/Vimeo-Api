package db.poorya.namavatest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import db.poorya.namavatest.base.architecture.BaseViewModel
import db.poorya.namavatest.domain.model.local.VideoModel
import db.poorya.namavatest.domain.repository.AppRepository
import db.poorya.namavatest.utils.AppConfig
import db.poorya.namavatest.utils.state.AppApiEnum
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val _liveSearchVideo = MutableLiveData<List<VideoModel>>()
    val liveSearchVideo: LiveData<List<VideoModel>>
        get() = _liveSearchVideo

    fun searchVideo(
        query: String,
    ) {
        callApi(
            AppApiEnum.SearchVideo,
            appRepository.searchVideo(query, 1, AppConfig.ITEM_PER_PAGE)
        ) {
            _liveSearchVideo.value = it.data.mapIndexed { index, data ->
                VideoModel(
                    id = index.toLong(),
                    title = data.name ?: "",
                    description = data.description ?: "",
                    thumbnailUrl = data.pictures?.baseLink ?: "",
                    duration = data.duration ?: 0,
                    comments = data.metadata?.connections?.comments?.total ?: 0,
                    likes = data.metadata?.connections?.likes?.total ?: 0,
                    views = /*data.metadata?.connections?.comments?.total ?:*/ 0,

                    videoLink = data.playerEmbedUrl ?: ""
                )
            }
        }
    }

}