package db.poorya.namavatest.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import db.poorya.namavatest.databinding.ItemVideoBinding
import db.poorya.namavatest.domain.model.VideoModel

class VideoAdapter :
    ListAdapter<VideoModel, VideoVH>(VideoDiffCallback()) {

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
        holder.adapter = this
        holder.bind(getItem(position))
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