package com.lemon.team.electronics.ui.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.ActivityImageBinding
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.observeEvent

class ImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageBinding
    val viewModel : ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView<ActivityImageBinding>(
            this,
            R.layout.activity_image
        ).also { binding ->
            binding.lifecycleOwner=this
            binding.imageUrl=intent.getStringExtra(Constants.MAIN_URL)
            binding.viewModel =viewModel
        }
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.onclickBack.observeEvent(this) {
            onBackPressed()
        }
    }
}