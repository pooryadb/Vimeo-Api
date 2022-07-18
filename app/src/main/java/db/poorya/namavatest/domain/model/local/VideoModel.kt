package db.poorya.namavatest.domain.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoModel(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val duration: Long,
    val comments: Long,
    val likes: Long,
    val views: Long,

    val videoLink: String
) : Parcelable
