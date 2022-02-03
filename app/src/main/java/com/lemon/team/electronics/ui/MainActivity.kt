package com.lemon.team.electronics.ui

import android.os.Bundle
import com.lemon.team.electronics.R
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.lemon.team.electronics.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_3DElectronics)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also { binding ->
            binding.lifecycleOwner = this
        }
    }

    override fun onStart() {
        super.onStart()
        binding.navigation
            .setupWithNavController(findNavController(R.id.fragment_host))
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.fragment_host).navigateUp()
        return true
    }
}
