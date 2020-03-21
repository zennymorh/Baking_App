package com.zennymorh.bakingapp.ui.step


import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_step_detail.*

/**
 * A simple [Fragment] subclass.
 */
class StepDetailFragment : Fragment() {
    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val playbackPosition = 0L
    private val bandwidthMeter by lazy {
        DefaultBandwidthMeter()
    }
    private val adaptiveTrackSelectionFactory by lazy {
        AdaptiveTrackSelection.Factory(bandwidthMeter)
    }
    val mainHandler = Handler()

    val args: StepDetailFragmentArgs by navArgs()
    var videoUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(activity!!,
            DefaultTrackSelector(adaptiveTrackSelectionFactory))
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

        simpleExoPlayer.prepare(videoSource)

        simpleExoPlayer.playWhenReady = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("Playback Position",simpleExoPlayer.currentPosition)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Procedure"

    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
        simpleExoPlayer.release()
    }
}
