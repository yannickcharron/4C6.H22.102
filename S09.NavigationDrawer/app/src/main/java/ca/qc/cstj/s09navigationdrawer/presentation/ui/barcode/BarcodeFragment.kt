package ca.qc.cstj.s09navigationdrawer.presentation.ui.barcode

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentBarcodeBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig

class BarcodeFragment : Fragment(R.layout.fragment_barcode) {

    private val binding: FragmentBarcodeBinding by viewBinding()
    private val viewModel: BarcodeViewModel by viewModels()

    private val zxingActivityLauncher : ActivityResultLauncher<ScanOptions>
        = registerForActivityResult(ScanContract(), ::handleZxingResult)

    private val quickieActivityLauncher = registerForActivityResult(ScanQRCode(), ::handleQuickieResult)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnZXing.setOnClickListener {
            val options = ScanOptions()
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            options.setPrompt("Veuillez scanner votre code à barres")
            options.setCameraId(0)
            options.setBeepEnabled(true)

            //Démarrer le scan
            zxingActivityLauncher.launch(options)
        }

        binding.btnQuickie.setOnClickListener {
            quickieActivityLauncher.launch(null)

        //            quickieActivityLauncher.launch(ScannerConfig.build {
//                setBarcodeFormats(listOf(BarcodeFormat.FORMAT_ALL_FORMATS)) // set interested barcode format
//                setHapticSuccessFeedback(false) // enable (default) or disable haptic feedback when a barcode was detected
//                setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
//                setHorizontalFrameRatio(2.2f) // set the horizontal overlay ratio (default is 1 / square frame)
//                setUseFrontCamera(false) // use the front camera
//            })
        }

        viewModel.checkIn.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    binding.txvCodeContent.text = it.data.toString()
                }
            }
        }

    }

    private fun handleQuickieResult(qrResult: QRResult) {

        when(qrResult) {
            is QRResult.QRSuccess -> {
                binding.txvCodeContent.text = qrResult.content.rawValue
            }
            is QRResult.QRUserCanceled -> {
                binding.txvCodeContent.text = getString(R.string.user_canceled)
            }
            is QRResult.QRMissingPermission -> {
                binding.txvCodeContent.text = getString(R.string.missing_permission)
            }
            is QRResult.QRError -> {
                binding.txvCodeContent.text = qrResult.exception.message
            }
        }


    }

    private fun handleZxingResult(result: ScanIntentResult) {
        if(result.contents == null) {
            binding.txvCodeContent.text = getString(R.string.error)
        } else {
            binding.txvCodeContent.text = result.contents
            viewModel.addCheckIn(result.contents)
        }
    }

}