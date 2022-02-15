package ca.qc.cstj.s02constraintlayout.presentation.ui.pilot

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import ca.qc.cstj.s02constraintlayout.R
import ca.qc.cstj.s02constraintlayout.databinding.ActivityPilotBinding
import ca.qc.cstj.s02constraintlayout.domain.models.Pilot
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class PilotActivity : AppCompatActivity() {

    //Permettre d'accéder aux composants graphiques (Button, TextView) de l'interface
    private lateinit var binding: ActivityPilotBinding
    private val viewModel: PilotViewModel by viewModels()

    //private val _pilot = Pilot("Bee Zoon",10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Transformer et charge le xml en mémoire dans le binding
        binding = ActivityPilotBinding.inflate(layoutInflater)
        //Créer l'interface à partir du binding
        setContentView(binding.root)

        viewModel.pilot.observe(this) {
            with(binding) {
                txvPilotName.text = it.name
                txvLevel.text = getString(R.string.level, it.level)
                txvLife.text = it.life.toString()
                txvShield.text = it.shield.toString()
                txvEnergy.text = it.energy.toString()
                txvCube.text = it.cube.toString()
            }
        }

        //refreshUI()

        binding.btnStart?.setOnClickListener {
//            if(_pilot.canFly()) {
//                binding.btnStart?.isEnabled = false
//                _pilot.fly(binding.sldRevolution.value.toInt(), binding.swtTraining.isChecked)
//
//                val layoutParams = binding.imvRocket.layoutParams as ConstraintLayout.LayoutParams
//                val startAngle = layoutParams.circleAngle
//                val endAngle = startAngle - 360
//                val animation = ValueAnimator.ofFloat(startAngle, endAngle)
//
//                animation.repeatCount = binding.sldRevolution.value.toInt() - 1
//                animation.duration = Random.nextLong(1500, 3500)
//                animation.interpolator = AccelerateInterpolator()
//
//                animation.addUpdateListener {
//                    val animatedValue = it.animatedValue as Float
//                    val layoutParamsAnimation = binding.imvRocket.layoutParams as ConstraintLayout.LayoutParams
//                    layoutParamsAnimation.circleAngle = animatedValue
//                    binding.imvRocket.layoutParams = layoutParamsAnimation
//                    binding.imvRocket.rotation = (animatedValue - 90) * 3
//                }
//
//                animation.doOnEnd {
//                    binding.btnStart?.isEnabled = true
//                    binding.imvRocket.rotation = 0.0F
//                    refreshUI()
//                }
//
//
//
//                animation.start()
//
//            } else {
//                Snackbar.make(binding.root, getString(R.string.low_resources), Snackbar.LENGTH_INDEFINITE)
//                    .setAction(getString(R.string.msgContinue)) {
//                        _pilot.recharge()
//                        refreshUI()
//                    }
//                    .show()
//            }
//
        }
    }

//    private fun refreshUI() {
//        with(binding) {
//            txvPilotName.text = _pilot.name
//            txvLevel.text = getString(R.string.level, _pilot.level)
//            txvLife.text = _pilot.life.toString()
//            txvShield.text = _pilot.shield.toString()
//            txvEnergy.text = _pilot.energy.toString()
//            txvCube.text = _pilot.cube.toString()
//        }

}