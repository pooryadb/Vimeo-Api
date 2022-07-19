package db.poorya.namavatest.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.R
import db.poorya.namavatest.databinding.FragHomeBinding
import db.poorya.namavatest.ext.*
import db.poorya.namavatest.presentation.AppViewModel
import db.poorya.namavatest.presentation.main.adapter.VideoAdapter
import db.poorya.namavatest.utils.AppConfig
import db.poorya.namavatest.utils.StateAdapter
import db.poorya.namavatest.utils.state.AppApiErrorEnum
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var stateAdapter: StateAdapter

    private var binding: FragHomeBinding? = null

    private val appViewModel by activityViewModels<AppViewModel>()

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
        setupRecyclerView()

        initObservers()
    }

    private fun setupEditTextSearch() = binding?.tiQuery?.apply {

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
        if (query.isEmpty()) {
            videoAdapter.submitData(lifecycle, PagingData.empty())
        } else if (query != appViewModel.searchText)
            appViewModel.searchVideo(query)
    }

    private fun setupRecyclerView() = binding?.apply {
        videoAdapter.apply {

            onClickListener = {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragToDetailFrag(it)
                )
            }

            pagingStates(object : PagingStateListener {
                override fun onEmpty() {
                    "onEmpty".logD("loading")
                    layEmpty.toShow()
                    prg.toGone()
                }

                override fun onRefresh(isFresh: Boolean) {
                    if (isFresh) {
                        "onNewResult".logD("loading")
                        if (itemCount <= AppConfig.ITEM_PER_PAGE)
                            rcVideos.scrollToPosition(0)
                    } else {
                        "onRefresh".logD("loading")
                        prg.toShow()
                    }
                }

                override fun onShowContent() {
                    "onShowContent".logD("loading")
                    layEmpty.toGone()
                    prg.toGone()
                }

                override fun onError(error: Throwable) {
                    "onError1: $error".logD("loading")
                    if (error is NullPointerException)
                        submitData(lifecycle, PagingData.empty())
                }

            })

        }

        rcVideos.apply {
            stateAdapter.onRetry = { videoAdapter.retry() }
            adapter = videoAdapter.withLoadStateFooter(
                stateAdapter
            )
        }
    }


    private fun initObservers() {
        appViewModel.appLiveData.apply {

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

                binding?.prg?.toGone()
                binding?.tiQuery?.error = errorMsg
                appViewModel.searchText = ""
            }
        }

        appViewModel.liveSearchVideo.observe(viewLifecycleOwner) {
            binding?.tiQuery?.error = null
            it?.let {
                binding?.layEmpty?.toGone()
                videoAdapter.submitData(lifecycle, it)
            }
        }

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}