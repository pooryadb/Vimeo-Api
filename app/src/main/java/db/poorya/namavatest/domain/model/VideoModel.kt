package db.poorya.namavatest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoModel(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val lengthSec: Long,
) : Parcelable
