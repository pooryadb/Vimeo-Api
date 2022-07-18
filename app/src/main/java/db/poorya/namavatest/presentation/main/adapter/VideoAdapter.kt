package db.poorya.namavatest.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import db.poorya.namavatest.databinding.ItemVideoBinding
import db.poorya.namavatest.domain.model.local.VideoModel

class VideoAdapter :
    PagingDataAdapter<VideoModel, VideoVH>(VideoDiffCallback()) {

    var binding: ItemVideoBinding? = null

    class VideoDiffCallback : DiffUtil.ItemCallback<VideoModel>() {
        override fun areItemsTheSame(
            oldItem: VideoModel,
            newItem: VideoModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: VideoModel,
            newItem: VideoModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        getItem(position)?.let {
            holder.adapter = this
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoVH(binding)
    }

    var onClickListener: ((VideoModel) -> Unit)? = null

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        binding = null
    }
}