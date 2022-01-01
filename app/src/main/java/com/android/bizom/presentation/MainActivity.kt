package com.android.bizom.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.android.bizom.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.fragmentContainerView).addOnDestinationChangedListener{ controller, destination, arguments ->
            title = when (destination.id) {
                R.id.fragment_old_claims -> "Home"
                R.id.claim_fragment -> getString(R.string.add_claim)
                else -> getString(R.string.app_name)
            }
        }
    }
}