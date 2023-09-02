package com.example.busgo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() //Hiding the title bar and action bar.

        setContentView(R.layout.activity_client)

        val searchAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.searchAutoCompleteTextView)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val busTypeRadioGroup = findViewById<RadioGroup>(R.id.busTypeRadioGroup)
        val viewSchedulesButton = findViewById<Button>(R.id.viewSchedulesButton)
        val scheduleTextView = findViewById<TextView>(R.id.scheduleTextView)
        val bookSeatsButton = findViewById<Button>(R.id.bookSeatsButton)

        val searchResults = listOf("Kandy - Colombo", "Anuradhapura - Galle", "Colombo - Matara")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, searchResults)
        searchAutoCompleteTextView.setAdapter(adapter)

        searchButton.setOnClickListener {
            val searchText = searchAutoCompleteTextView.text.toString()

            if (searchText.isNotEmpty()) {

            }
            searchAutoCompleteTextView.text.clear()
        }

        viewSchedulesButton.setOnClickListener {
            val regularSchedules = listOf(
                "Regular Bus Schedules:\n- Morning: 8:00 AM\n- Afternoon: 1:00 PM\n- Evening: 6:00 PM",
                "Regular Bus Schedules:\n- Morning: 9:00 AM\n- Afternoon: 2:00 PM\n- Evening: 7:00 PM",
                "Regular Bus Schedules:\n- Morning: 10:00 AM\n- Afternoon: 3:00 PM\n- Evening: 8:00 PM"
            )

            val luxurySchedules = listOf(
                "Luxury Bus Schedules:\n- Morning: 7:30 AM\n- Afternoon: 12:30 PM\n- Evening: 5:30 PM",
                "Luxury Bus Schedules:\n- Morning: 8:30 AM\n- Afternoon: 1:30 PM\n- Evening: 6:30 PM",
                "Luxury Bus Schedules:\n- Morning: 9:30 AM\n- Afternoon: 2:30 PM\n- Evening: 7:30 PM"
            )

            val expressSchedules = listOf(
                "Express Bus Schedules:\n- Morning: 7:00 AM\n- Afternoon: 12:00 PM\n- Evening: 5:00 PM",
                "Express Bus Schedules:\n- Morning: 8:00 AM\n- Afternoon: 1:00 PM\n- Evening: 6:00 PM",
                "Express Bus Schedules:\n- Morning: 9:00 AM\n- Afternoon: 2:00 PM\n- Evening: 7:00 PM"
            )

            val selectedBusType = findViewById<RadioButton>(busTypeRadioGroup.checkedRadioButtonId).text.toString()
            val schedules = when (selectedBusType) {
                "Regular" -> regularSchedules.random()
                "Luxury" -> luxurySchedules.random()
                "Express" -> expressSchedules.random()
                else -> ""
            }
            scheduleTextView.text = schedules
        }

        bookSeatsButton.setOnClickListener {
            val selectedText = searchAutoCompleteTextView.text.toString()

            if (selectedText.isNotEmpty()) {
                // Start the BookSeatsActivity
                val intent = Intent(this, BookSeatsActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a search result before booking seats.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
