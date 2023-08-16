package com.example.busgo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val searchAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.searchAutoCompleteTextView)
        val searchButton = findViewById<Button>(R.id.searchButton)

        val searchResults = listOf("Result 1", "Result 2", "Result 3")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, searchResults)
        searchAutoCompleteTextView.setAdapter(adapter)

        searchButton.setOnClickListener {
            val searchText = searchAutoCompleteTextView.text.toString()

            if (searchText.isNotEmpty()) {

            }

            searchAutoCompleteTextView.text.clear()
        }
    }
}
