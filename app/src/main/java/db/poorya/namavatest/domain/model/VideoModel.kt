package db.poorya.namavatest.domain.model

data class VideoModel(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val lengthSec: Long,
)
