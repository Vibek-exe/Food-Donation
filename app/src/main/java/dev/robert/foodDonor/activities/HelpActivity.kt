package dev.robert.foodDonor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import dev.robert.foodDonor.databinding.ActivityHelpBinding

@AndroidEntryPoint
class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.helpToolbar)
        binding.helpToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}