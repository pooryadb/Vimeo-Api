package db.poorya.namavatest.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import db.poorya.namavatest.databinding.ItemVideoBinding
import db.poorya.namavatest.domain.model.VideoModel
import db.poorya.namavatest.ext.loadCompat

class VideoVH(private val binding: ItemVideoBinding?) :
    RecyclerView.ViewHolder(requireNotNull(binding?.root)) {

    lateinit var adapter: VideoAdapter

    fun bind(item: VideoModel) {

        binding?.apply {
            clMain.setOnClickListener {
                adapter.onClickListener?.invoke(item)
            }

            ivThumbnail.loadCompat(item.thumbnailUrl)
            tvTitle.text = item.title
            tvDesc.text = item.description
        }

    }

}