package com.bigloan.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SubscriptionList : AppCompatActivity() {
    private lateinit var btnPremier: Button
    private lateinit var btnPro: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subscription_list)

        btnPremier = findViewById(R.id.buttonPremier)
        btnPro = findViewById(R.id.buttonPro)

        btnPremier.setOnClickListener {
            // Create an Intent to navigate to the second activity
            val intent = Intent(this@SubscriptionList, SubscriptionActivity::class.java).apply {
                // Pass the user inputs to the second activity
                putExtra("subscriptionType", "Premier")
            }
            startActivity(intent)
        }

        btnPro.setOnClickListener {
            // Create an Intent to navigate to the second activity
            val intent = Intent(this@SubscriptionList, SubscriptionActivity::class.java).apply {
                // Pass the user inputs to the second activity
                putExtra("subscriptionType", "Pro")
            }
            startActivity(intent)
        }

    }
}