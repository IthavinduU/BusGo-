package com.example.busgo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val searchAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.searchAutoCompleteTextView)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val bookSeatsButton = findViewById<Button>(R.id.bookSeatsButton)

        val searchResults = listOf("Result 1", "Result 2", "Result 3")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, searchResults)
        searchAutoCompleteTextView.setAdapter(adapter)

        searchButton.setOnClickListener {
            val searchText = searchAutoCompleteTextView.text.toString()

            if (searchText.isNotEmpty()) {

            }

            // Reset the search bar
            searchAutoCompleteTextView.text.clear()
        }

        bookSeatsButton.setOnClickListener {
            val selectedText = searchAutoCompleteTextView.text.toString()

            if (selectedText.isNotEmpty()) {
                // Handle seat booking here
                // You can add your own logic to book seats
                val message = "Seats booked for: $selectedText"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a search result before booking seats.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
