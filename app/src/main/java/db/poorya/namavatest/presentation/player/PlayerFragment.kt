package db.poorya.namavatest.presentation.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.MimeTypes
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.databinding.FragPlayerBinding
import db.poorya.namavatest.ext.disableFullScreen
import db.poorya.namavatest.ext.enableFullScreen


@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private var binding: FragPlayerBinding? = null

    private val args by navArgs<PlayerFragmentArgs>()

    private var exoPlayer: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            enableFullScreen()
        }

        binding = FragPlayerBinding.inflate(inflater, container, false)

        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPlayer()

    }

    private fun setupPlayer() {
        exoPlayer = ExoPlayer.Builder(context!!).build()

        exoPlayer?.apply {
            val item = MediaItem.Builder()
                .setUri(args.url)
                .setMimeType(MimeTypes.APPLICATION_M3U8)
                .build()

            addMediaItem(item)
            prepare()
        }

        binding?.exoPlayer?.player = exoPlayer

        exoPlayer?.play()
    }

    override fun onDestroyView() {
        requireActivity().apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            disableFullScreen()
        }

        exoPlayer?.release()

        binding = null
        super.onDestroyView()
    }

}