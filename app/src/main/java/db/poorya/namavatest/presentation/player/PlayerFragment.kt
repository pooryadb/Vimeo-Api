package db.poorya.namavatest.presentation.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.databinding.FragPlayerBinding


@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private var binding: FragPlayerBinding? = null

    private val args by navArgs<PlayerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        binding = FragPlayerBinding.inflate(inflater, container, false)

        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            wv.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return false
                }
            }
            val ws: WebSettings = wv.settings
            ws.javaScriptEnabled = true

            wv.loadDataWithBaseURL(
                args.url,
                "<iframe src=\"${args.url}\" width=\"100%\" height=\"100%\" frameborder=\"0\"></iframe>",
                "text/html",
                "utf-8",
                null
            )
        }

    }

    override fun onDestroyView() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        super.onDestroyView()
    }

}