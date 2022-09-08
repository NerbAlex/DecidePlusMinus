package ru.inc.decideplusminus.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSystemUi()
    }

    @SuppressLint("RestrictedApi")
    private fun initSystemUi() {

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.toolbar.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}