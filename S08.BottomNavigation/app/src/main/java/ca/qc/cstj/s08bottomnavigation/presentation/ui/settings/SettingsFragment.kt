package ca.qc.cstj.s08bottomnavigation.presentation.ui.settings

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s08bottomnavigation.R
import ca.qc.cstj.s08bottomnavigation.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding()
    private val viewModel: SettingsViewModel by viewModels()

    //Dans l'activité la majorité du code est dans onCreated() => onViewCreated()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewLifecycleOwner = this dans une activity
        viewModel.count.observe(viewLifecycleOwner) {
            binding.txvCount.text = it.toString()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.add()
        }

    }

}