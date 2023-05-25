package com.anilyilmaz.awesomesunsetwallpapers.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anilyilmaz.awesomesunsetwallpapers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity () {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

