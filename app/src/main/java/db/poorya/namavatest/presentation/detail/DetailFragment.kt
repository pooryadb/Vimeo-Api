package db.poorya.namavatest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.R
import db.poorya.namavatest.databinding.FragDetailBinding
import db.poorya.namavatest.ext.*
import db.poorya.namavatest.presentation.AppViewModel
import db.poorya.namavatest.utils.state.AppApiEnum
import db.poorya.namavatest.utils.state.AppApiErrorEnum


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: FragDetailBinding? = null

    private val appViewModel by activityViewModels<AppViewModel>()

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
                appViewModel.getVideoConfig(args.video.id)
            }
            tvDuration.text = args.video.duration.formatSecToTime()

            tvPlayed.text = args.video.views.toString()
            tvLike.text = args.video.likes.toString()
            tvComment.text = args.video.comments.toString()
        }

        initObservers()
    }

    private fun initObservers() {
        appViewModel.appLiveData.apply {
            loadingApi.observe(viewLifecycleOwner) {
                when (it.apiEnum.cast<AppApiEnum>()) {
                    AppApiEnum.GetVideoConfig -> {
                        if (it.load == true)
                            binding?.prg?.toShow()
                        else
                            binding?.prg?.toGone()
                    }
                    else -> {}
                }
            }

            errorApi.observe(viewLifecycleOwner) {
                val errorMsg: String = when (it.errorApiEnum.cast<AppApiErrorEnum>()) {
                    AppApiErrorEnum.OnBadRequest -> {
                        it.msg ?: getString(R.string.error)
                    }
                    AppApiErrorEnum.OnUnknownError,
                    AppApiErrorEnum.OnConnectionLost -> {
                        getString(R.string.error)
                    }
                    else -> {
                        getString(R.string.error)
                    }
                }

                requireContext().toast(errorMsg)
            }
        }

        appViewModel.liveVideoLink.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragToDplayerFrag(it)
                )
            } else {
                requireContext().toast(getString(R.string.error))
            }
        }

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}