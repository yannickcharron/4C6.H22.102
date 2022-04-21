package ca.qc.cstj.s09navigationdrawer.presentation.ui.planet

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.LoadingResource
import ca.qc.cstj.s09navigationdrawer.core.notifyAllItemChanged
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentListPlanetsBinding
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import ca.qc.cstj.s09navigationdrawer.presentation.adapters.PlanetRecyclerViewAdapter

class PlanetListFragment : Fragment(R.layout.fragment_list_planets) {

    private val binding: FragmentListPlanetsBinding by viewBinding()
    private val viewModel: PlanetListViewModel by viewModels()

    private lateinit var planetRecyclerViewAdapter: PlanetRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter(listOf(), ::onRecyclerViewPlanetClick)

        binding.rcvPlanets.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = planetRecyclerViewAdapter
        }

//        binding.swlRefresh.setOnRefreshListener {
//            viewModel.refreshPlanets()
//            binding.swlRefresh.isRefreshing = false
//        }

        viewModel.planets.observe(viewLifecycleOwner) {

            when(it) {
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is LoadingResource.Success -> {
                    binding.pgbLoading.hide()
                    planetRecyclerViewAdapter.planets = it.data!!
                    planetRecyclerViewAdapter.notifyAllItemChanged()
                    binding.rcvPlanets.visibility = View.VISIBLE
                }
                is LoadingResource.Loading -> {
                    binding.pgbLoading.show()
                    binding.rcvPlanets.visibility = View.INVISIBLE
                }
            }

        }

    }

    private fun onRecyclerViewPlanetClick(planet: Planet) {
        //TODO:
    }


}