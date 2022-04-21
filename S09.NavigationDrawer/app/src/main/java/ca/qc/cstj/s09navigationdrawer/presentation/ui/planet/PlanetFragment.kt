package ca.qc.cstj.s09navigationdrawer.presentation.ui.planet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentPlanetBinding
import com.bumptech.glide.Glide

class PlanetFragment : Fragment(R.layout.fragment_planet) {

    private val binding: FragmentPlanetBinding by viewBinding()
    private val viewModel: PlanetViewModel by viewModels {
        PlanetViewModel.Factory(args.href)
    }

    private val args : PlanetFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.planet.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_LONG).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                is Resource.Success -> {
                    val planet = it.data!!

                    binding.txvPlanetName.text = planet.name
                    binding.txvDetailDiscoveredBy.text = planet.discoveredBy

                    Glide.with(this)
                        .load(planet.image)
                        .placeholder(R.drawable.planet)
                        .error(R.drawable.ic_baseline_error_24)
                        .into(binding.imvDetailPlanet)

                    (requireActivity() as  AppCompatActivity).supportActionBar?.title = planet.name
                }
            }
        }
    }


}