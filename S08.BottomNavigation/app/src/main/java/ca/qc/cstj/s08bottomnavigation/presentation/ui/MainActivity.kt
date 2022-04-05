package ca.qc.cstj.s08bottomnavigation.presentation.ui

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ca.qc.cstj.s08bottomnavigation.R
import ca.qc.cstj.s08bottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navFragmentContainerView = supportFragmentManager.findFragmentById(R.id.navFragmentContainerView) as NavHostFragment
        val navController = navFragmentContainerView.navController


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_favorites, R.id.navigation_search, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bnvNavigation.setupWithNavController(navController)
    }
}