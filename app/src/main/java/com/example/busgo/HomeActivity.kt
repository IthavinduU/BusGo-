package com.example.busgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() //Hiding the title bar and action bar.

        setContentView(R.layout.activity_home)

        val driverButton = findViewById<View>(R.id.driverButton)
        val clientButton = findViewById<View>(R.id.clientButton)
        val exitButton = findViewById<View>(R.id.exitButton)

        driverButton.setOnClickListener {
            val intent = Intent(this, DriverActivity::class.java)
            startActivity(intent)
        }

        clientButton.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity() // Close the application
        }
    }
}
