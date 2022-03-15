package ca.qc.cstj.s05localdatasource.presentation.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.text
import ca.qc.cstj.s05localdatasource.databinding.ActivitySplashBinding
import ca.qc.cstj.s05localdatasource.presentation.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.user.observe(this) {
            //TODO: Updater l'interface graphique
            binding.txvUserName.text = it.fullName

            when(it.isOnline) {
                true -> binding.imgUserIsOnline.setImageResource(R.drawable.ic_baseline_cloud_24)
                false -> binding.imgUserIsOnline.setImageResource(R.drawable.ic_baseline_cloud_off_24)
            }

            //Bas 2 txts + switch
            binding.tilFirstName.text = it.firstName
            binding.tilLastName.text = it.lastName
            binding.swtOnline.isChecked = it.isOnline

        }

        binding.btnSave.setOnClickListener {
            val firstName = binding.tilFirstName.text
            val lastName = binding.tilLastName.text

            viewModel.save(firstName, lastName, binding.swtOnline.isChecked)
        }

        binding.btnOpen.setOnClickListener {
            startActivity(MainActivity.newIntent(this))
        }

    }
}