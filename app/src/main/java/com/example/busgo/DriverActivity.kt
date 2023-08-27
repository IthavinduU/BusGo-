package com.example.busgo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DriverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)

        val busNumberEditText: EditText = findViewById(R.id.busNumberEditText)
        val numSeatsEditText: EditText = findViewById(R.id.numSeatsEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val driverName = findViewById<EditText>(R.id.driverNameEditText).text.toString()
            val conductorName = findViewById<EditText>(R.id.conductorNameEditText).text.toString()
            val busType = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.busTypeRadioGroup).checkedRadioButtonId)?.text.toString()
            val busNumber = busNumberEditText.text.toString()
            val numSeats = numSeatsEditText.text.toString()

            val busData = "Driver Name: $driverName\n" +
                    "Conductor Name: $conductorName\n" +
                    "Bus Type: $busType\n" +
                    "Bus Number: $busNumber\n" +
                    "Number of Seats: $numSeats"

            // Display the added data in an AlertDialog
            showDetailsDialog(busData)
        }
    }

    private fun showDetailsDialog(details: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bus Details")
        builder.setMessage(details)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
