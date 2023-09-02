package com.example.busgo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val validCredentials = mapOf(
        "user1" to "password1",
        "user2" to "password2",
        "user3" to "password3"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() //Hiding the title bar and action bar.

        setContentView(R.layout.activity_main)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                // Show alert dialog for empty fields
                showEmptyFieldsDialog()
            } else if (validCredentials.containsKey(username) && validCredentials[username] == password) {
                // Navigate to HomeActivity on successful login
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                // Show custom dialog for incorrect credentials
                showIncorrectCredentialsDialog()
            }
        }
    }

    private fun showEmptyFieldsDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Please enter the username and password!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun showIncorrectCredentialsDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_invalid_credentials, null)
        val okButton = dialogView.findViewById<Button>(R.id.okButton)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)

        val dialog = dialogBuilder.create()

        okButton.setOnClickListener {
            val usernameEditText: EditText = findViewById(R.id.usernameEditText)
            val passwordEditText: EditText = findViewById(R.id.passwordEditText)

            // Clear the fields
            usernameEditText.text.clear()
            passwordEditText.text.clear()

            dialog.dismiss()
        }

        dialog.show()
    }
}
