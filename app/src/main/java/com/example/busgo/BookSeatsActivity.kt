package com.example.busgo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class BookSeatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() //Hiding the title bar and action bar.

        setContentView(R.layout.activity_book_seats)

        val seatNumberEditText = findViewById<EditText>(R.id.seatNumberEditText)
        val bookButton = findViewById<Button>(R.id.bookButton)
        val seatGridLayout = findViewById<GridLayout>(R.id.seatGridLayout)

        bookButton.setOnClickListener {
            val numberOfSeatsText = seatNumberEditText.text.toString()

            if (numberOfSeatsText.isNotEmpty()) {
                val numberOfSeats = numberOfSeatsText.toInt()

                if (numberOfSeats > 4) {
                    // Show dialog for booking more than 4 seats
                    showMaxSeatsDialog()
                } else {
                    val bookedSeats = generateRandomSeats(numberOfSeats)
                    showBookedSeatsPopup(bookedSeats)
                    populateSeatGridLayout(seatGridLayout)
                }
            } else {
                Toast.makeText(this, "Please enter the number of seats.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateRandomSeats(numberOfSeats: Int): List<String> {
        val rows = listOf("A", "B", "C") // List of rows
        val allSeats = mutableListOf<String>()
        for (row in rows) {
            for (seatNumber in 1..10) { // Assuming 10 seats per row
                allSeats.add("$seatNumber$row")
            }
        }
        val availableSeats = allSeats.toMutableList()
        val bookedSeats = mutableListOf<String>()

        repeat(numberOfSeats) {
            if (availableSeats.isNotEmpty()) {
                val randomIndex = Random.nextInt(availableSeats.size)
                val bookedSeat = availableSeats.removeAt(randomIndex)
                bookedSeats.add(bookedSeat)
            }
        }

        return bookedSeats
    }

    private fun showBookedSeatsPopup(bookedSeats: List<String>) {
        val message = "Booked seats: ${bookedSeats.joinToString(", ")}"
        val popup = Toast.makeText(this, message, Toast.LENGTH_LONG)
        popup.show()
    }

    private fun showMaxSeatsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Booking Limit")
            .setMessage("You can only book up to 4 seats from one account.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    private fun populateSeatGridLayout(seatGridLayout: GridLayout) {
        seatGridLayout.removeAllViews()

        // Add seat price header
        val seatPriceHeader = Button(this)
        seatPriceHeader.text = "Price"
        seatPriceHeader.setBackgroundResource(R.drawable.background) // Set header background
        seatPriceHeader.setTextColor(resources.getColor(android.R.color.black, null))
        seatPriceHeader.textSize = 14f
        seatGridLayout.addView(seatPriceHeader)

        // Example: Populate the grid with seat views and corresponding prices
        val rows = listOf("A", "B", "C")
        val seatPrices = listOf("$10", "$15", "$20") // Example prices
        for (row in rows) {
            for (seatNumber in 1..10) {
                val seatView = Button(this)
                seatView.text = "$seatNumber$row"
                seatView.setBackgroundResource(R.drawable.background1) // Set your seat background here
                seatView.layoutParams = GridLayout.LayoutParams()
                (seatView.layoutParams as GridLayout.LayoutParams).columnSpec = GridLayout.spec(seatNumber)
                seatGridLayout.addView(seatView)

                val seatPriceView = Button(this)
                val priceIndex = rows.indexOf(row)
                seatPriceView.text = seatPrices[priceIndex]
                seatPriceView.setBackgroundResource(R.drawable.background1) // Set price background
                seatPriceView.setTextColor(resources.getColor(android.R.color.black, null))
                seatPriceView.textSize = 14f
                seatPriceView.layoutParams = GridLayout.LayoutParams()
                (seatPriceView.layoutParams as GridLayout.LayoutParams).columnSpec = GridLayout.spec(seatNumber)
                seatGridLayout.addView(seatPriceView)
            }
        }
    }
}
