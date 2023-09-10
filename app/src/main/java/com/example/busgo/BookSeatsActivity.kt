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
        supportActionBar?.hide() // Hiding the title bar and action bar.

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
                    val (bookedSeats, totalPrice) = generateRandomSeats(numberOfSeats)
                    showBookedSeatsDialog(bookedSeats, totalPrice)
                    populateSeatGridLayout(seatGridLayout)
                }
            } else {
                Toast.makeText(this, "Please enter the number of seats.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateRandomSeats(numberOfSeats: Int): Pair<List<String>, Int> {
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

        val totalPrice = bookedSeats.size * 1050 // Price per seat is 1050 rupees
        return Pair(bookedSeats, totalPrice)
    }

    private fun showBookedSeatsDialog(bookedSeats: List<String>, totalPrice: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Booked Seats and Total Price")
        builder.setMessage("Booked seats: ${bookedSeats.joinToString(", ")}\nTotal Price: ${totalPrice} rupees")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
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
        // Your existing code for populating the grid with seats and prices
    }
}
