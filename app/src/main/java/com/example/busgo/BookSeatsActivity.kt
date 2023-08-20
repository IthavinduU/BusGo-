package com.example.busgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random

class BookSeatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_seats)

        val seatNumberEditText = findViewById<EditText>(R.id.seatNumberEditText)
        val bookButton = findViewById<Button>(R.id.bookButton)

        bookButton.setOnClickListener {
            val numberOfSeatsText = seatNumberEditText.text.toString()

            if (numberOfSeatsText.isNotEmpty()) {
                val numberOfSeats = numberOfSeatsText.toInt()
                val bookedSeats = generateRandomSeats(numberOfSeats)
                val message = "Booked seats: ${bookedSeats.joinToString(", ")}"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter the number of seats.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateRandomSeats(numberOfSeats: Int): List<Int> {
        val allSeats = 1..50 // Assuming there are 50 seats available
        val availableSeats = allSeats.toMutableList()
        val bookedSeats = mutableListOf<Int>()

        repeat(numberOfSeats) {
            if (availableSeats.isNotEmpty()) {
                val randomIndex = Random.nextInt(availableSeats.size)
                val bookedSeat = availableSeats.removeAt(randomIndex)
                bookedSeats.add(bookedSeat)
            }
        }

        return bookedSeats
    }
}
