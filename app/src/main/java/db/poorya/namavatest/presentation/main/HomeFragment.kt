package db.poorya.namavatest.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.databinding.FragHomeBinding
import db.poorya.namavatest.domain.model.VideoModel
import db.poorya.namavatest.ext.hideKeyboard
import db.poorya.namavatest.ext.onChange
import db.poorya.namavatest.ext.toast
import db.poorya.namavatest.presentation.main.adapter.VideoAdapter
import db.poorya.namavatest.utils.AppConfig
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var videoAdapter: VideoAdapter

    private var binding: FragHomeBinding? = null

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
                // TODO: open detail fragment
                requireContext().toast(it.title)
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragToDetailFrag(it)
                )
            }
            adapter = videoAdapter
        }

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
        videoAdapter.submitList(
            (0..10).map {
                VideoModel(
                    it.toLong(),
                    "Video $it",
                    "Description $it".repeat(it + 1),
                    "https://picsum.photos/320/180?random=$it",
                    it * 15L
                )
            }
        )
    }

}