package com.lemon.team.electronics.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView<ActivityImageBinding>(
            this,
            R.layout.activity_image
        ).also { binding ->
            binding.lifecycleOwner=this
        }
    }
}