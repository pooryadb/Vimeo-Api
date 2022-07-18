package db.poorya.namavatest.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.databinding.FragHomeBinding
import db.poorya.namavatest.ext.*
import db.poorya.namavatest.presentation.main.adapter.VideoAdapter
import db.poorya.namavatest.utils.AppConfig
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var videoAdapter: VideoAdapter

    private var binding: FragHomeBinding? = null

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragHomeBinding.inflate(inflater, container, false)

        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEditTextSearch()

        binding?.rcVideos?.apply {
            videoAdapter.onClickListener = {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragToDetailFrag(it)
                )
            }
            adapter = videoAdapter
        }

        initObservers()
    }

    private fun setupEditTextSearch() = binding?.etQuery?.apply {

        editText?.onChange(AppConfig.SEARCH_DELAY_REMOTE, lifecycleScope) {
            doSearch(it)
        }

        editText?.setOnEditorActionListener { tv, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    tv.hideKeyboard()
                    doSearch(tv.text.toString())
                }
            }
            false
        }

    }

    private fun doSearch(query: String) {
        homeViewModel.searchVideo(query)
    }

    private fun initObservers() {
        homeViewModel.appLiveData.apply {
            loadingApi.observe(viewLifecycleOwner) {
                requireContext().toast(it.apiEnum.toString() + " -loading- " + it.load)
            }

            errorApi.observe(viewLifecycleOwner) {
                requireContext().toast(it.msg ?: "error")
            }
        }

        homeViewModel.liveSearchVideo.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding?.layEmpty?.toGone()
                videoAdapter.submitList(it)
            } else {
                binding?.layEmpty?.toShow()
                videoAdapter.submitList(emptyList())
            }
        }
    }

}