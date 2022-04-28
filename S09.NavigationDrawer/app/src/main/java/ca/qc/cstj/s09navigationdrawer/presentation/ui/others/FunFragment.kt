package ca.qc.cstj.s09navigationdrawer.presentation.ui.others

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentFunBinding

class FunFragment : Fragment(R.layout.fragment_fun){

    private val binding : FragmentFunBinding by viewBinding()
    private val viewModel : FunViewModel by viewModels()

    private lateinit var mediaPlayer : MediaPlayer

    private var counter = 0

    private val timer = object:CountDownTimer(10000,1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.txvCounter.text = (++counter).toString()
            binding.pgbLoading.setProgress(counter, true)
        }

        override fun onFinish() {
            this.cancel()
            counter = 0
            binding.pgbLoading.setProgress(counter, false)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.son)
        //mediaPlayer.isLooping = true

        try {
            ///val videoUri =
            // Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.video}")
            //binding.vdvVideo.setVideoURI(videoUri)
            binding.vdvVideo.setVideoPath(Constants.VIDEO_URL)
        } catch(ex: Exception) {
            //TODO: Afficher un erreur
        }

        binding.btnTimer.setOnClickListener {
            binding.vdvVideo.start()
            mediaPlayer.start()

            timer.cancel()
            timer.start()
        }
    }
}