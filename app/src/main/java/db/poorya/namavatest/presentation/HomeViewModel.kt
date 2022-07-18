package db.poorya.namavatest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import db.poorya.namavatest.base.architecture.BaseViewModel
import db.poorya.namavatest.domain.datasource.VideoDataSource
import db.poorya.namavatest.domain.model.local.VideoModel
import db.poorya.namavatest.domain.repository.AppRepository
import db.poorya.namavatest.utils.liveData.SingleLiveData
import db.poorya.namavatest.utils.state.AppApiEnum
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val _liveSearchVideo = MutableLiveData<PagingData<VideoModel>?>()
    val liveSearchVideo: LiveData<PagingData<VideoModel>?>
        get() = _liveSearchVideo

    private val _liveVideoLink = SingleLiveData<String>()
    val liveVideoLink: LiveData<String>
        get() = _liveVideoLink

    var searchText = ""

    fun searchVideo(
        query: String,
    ) {
        searchText = query

        callApiPaging(
            _liveSearchVideo,
            VideoDataSource(
                AppApiEnum.SearchVideo,
                appRepository,
                appLiveData,
                this.searchText
            )
        )
    }

    fun getVideoConfig(videoId: Long) {
        callApi(
            AppApiEnum.GetVideoConfig,
            appRepository.getVideoConfig(videoId)
        ) {
            _liveVideoLink.value = it.request?.files?.hls?.cdns?.akfireInterconnectQuic?.url ?: ""
        }
    }

}