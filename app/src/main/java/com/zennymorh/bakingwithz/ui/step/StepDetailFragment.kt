package com.zennymorh.bakingwithz.ui.step

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.zennymorh.bakingwithz.R
import com.zennymorh.bakingwithz.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_step_detail.*

/**
 * A simple [Fragment] subclass.
 */
class StepDetailFragment : Fragment() {

    private var simpleExoPlayer: SimpleExoPlayer? = null
    private val bandwidthMeter by lazy {
        DefaultBandwidthMeter()
    }
    private val adaptiveTrackSelectionFactory by lazy {
        AdaptiveTrackSelection.Factory(bandwidthMeter)
    }
    private val mainHandler = Handler()

    private val args: StepDetailFragmentArgs by navArgs()

    private var videoUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as MainActivity).title = "Procedure"
        return inflater.inflate(R.layout.fragment_step_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.text = args.stepSelected.description
        videoUrl = args.stepSelected.videoURL

        if (videoUrl.isNotEmpty()) {
            prepareExoPlayer()
        }

    }

    private fun prepareExoPlayer() {
        // Creating your exoPlayer
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(activity!!,
            DefaultTrackSelector(adaptiveTrackSelectionFactory))
        // Setting the player to your id
        recipe_exo_player.player = simpleExoPlayer

        val dataSourceFactory = DefaultDataSourceFactory(
            activity!!, Util.getUserAgent(activity!!, "BakingApp"), bandwidthMeter)
        // Produces Extractor instances for parsing the media data.
        val extractorsFactory = DefaultExtractorsFactory()
        // This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource(
            videoUrl.toUri(),
            dataSourceFactory, extractorsFactory, mainHandler, null
        )
        simpleExoPlayer?.prepare(videoSource)
        simpleExoPlayer?.playWhenReady = true
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Procedure"
    }

    override fun onStop() {
        super.onStop()
            simpleExoPlayer?.stop()
            simpleExoPlayer?.release()
    }
}
