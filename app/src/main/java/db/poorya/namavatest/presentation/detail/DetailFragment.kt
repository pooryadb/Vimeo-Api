package db.poorya.namavatest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.databinding.FragDetailBinding
import db.poorya.namavatest.ext.loadCompat


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: FragDetailBinding? = null

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragDetailBinding.inflate(inflater, container, false)

        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            tvTitle.text = args.video.title
            tvDesc.text = args.video.description
            ivThumbnail.loadCompat(args.video.thumbnailUrl)
            ivThumbnail.setOnClickListener {
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragToDplayerFrag(args.video.videoLink)
                )
            }

            tvPlayed.text = args.video.views.toString()
            tvLike.text = args.video.likes.toString()
            tvComment.text = args.video.comments.toString()
        }

    }

}