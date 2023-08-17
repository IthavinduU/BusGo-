package com.example.busgo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("ControlFlowWithEmptyBody")
class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val searchAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.searchAutoCompleteTextView)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val busTypeRadioGroup = findViewById<RadioGroup>(R.id.busTypeRadioGroup)
        val viewSchedulesButton = findViewById<Button>(R.id.viewSchedulesButton)
        val scheduleTextView = findViewById<TextView>(R.id.scheduleTextView)
        val bookSeatsButton = findViewById<Button>(R.id.bookSeatsButton)

        val searchResults = listOf("Result 1", "Result 2", "Result 3")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, searchResults)
        searchAutoCompleteTextView.setAdapter(adapter)

        searchButton.setOnClickListener {
            val searchText = searchAutoCompleteTextView.text.toString()

            if (searchText.isNotEmpty()) {
                // Implement your search logic here
            }
            searchAutoCompleteTextView.text.clear()
        }

        viewSchedulesButton.setOnClickListener {
            val schedules = when (findViewById<RadioButton>(busTypeRadioGroup.checkedRadioButtonId).text.toString()) {
                "Regular" -> "Regular Bus Schedules:\n- Morning: 8:00 AM\n- Afternoon: 1:00 PM\n- Evening: 6:00 PM"
                "Luxury" -> "Luxury Bus Schedules:\n- Morning: 9:00 AM\n- Afternoon: 2:00 PM\n- Evening: 7:00 PM"
                "Express" -> "Express Bus Schedules:\n- Morning: 7:30 AM\n- Afternoon: 12:30 PM\n- Evening: 5:30 PM"
                else -> ""
            }
            scheduleTextView.text = schedules
        }

        bookSeatsButton.setOnClickListener {
            val selectedText = searchAutoCompleteTextView.text.toString()

            if (selectedText.isNotEmpty()) {
                val message = "Seats booked for: $selectedText"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a search result before booking seats.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
