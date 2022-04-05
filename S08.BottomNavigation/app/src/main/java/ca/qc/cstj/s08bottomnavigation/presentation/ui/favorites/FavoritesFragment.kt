package ca.qc.cstj.s08bottomnavigation.presentation.ui.favorites

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s08bottomnavigation.R
import ca.qc.cstj.s08bottomnavigation.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding: FragmentFavoritesBinding by viewBinding()
    private val viewModel: FavoritesViewModel by viewModels()

    //Dans l'activité la majorité du code est dans onCreated() => onViewCreated()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}