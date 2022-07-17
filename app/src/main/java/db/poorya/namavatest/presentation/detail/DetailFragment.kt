package db.poorya.namavatest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
                // TODO: open video player
            }

            tvPlayed.text = 5000.toString()
            tvLike.text = 4990.toString()
            tvComment.text = 500.toString()
        }

    }

}