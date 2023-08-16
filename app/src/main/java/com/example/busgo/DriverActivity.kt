package com.example.busgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DriverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)

        val routeSpinner: Spinner = findViewById(R.id.routeSpinner)
        val saveButton: Button = findViewById(R.id.saveButton)

        // Define your route options as an array
        val routeOptions = arrayOf("Select the Route", "Route A", "Route B", "Route C", "Route D")

        // Create an ArrayAdapter using a simple spinner layout and your route options
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, routeOptions)

        // Set the layout style for the dropdown resource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter to the spinner
        routeSpinner.adapter = adapter

        // Set a listener to handle spinner item selection
        routeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedRoute = routeOptions[position]
                // You can do something with the selected route here
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing when nothing is selected
            }
        }

        saveButton.setOnClickListener {
            // Show a Toast message for save successful
            Toast.makeText(this, "Save successful", Toast.LENGTH_SHORT).show()
        }
    }
}
