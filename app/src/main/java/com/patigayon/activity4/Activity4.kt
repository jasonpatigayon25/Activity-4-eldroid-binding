package com.patigayon.activity4

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Activity4 : AppCompatActivity() {

    private val sharedPrefFile = "settingsPreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        val lightModeRadioButton = findViewById<RadioButton>(R.id.lightMode)
        val darkModeRadioButton = findViewById<RadioButton>(R.id.darkMode)
        val notificationCheckBox = findViewById<CheckBox>(R.id.notificationCheckbox)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val nicknameEditText = findViewById<EditText>(R.id.nicknameEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        lightModeRadioButton.isChecked = sharedPreferences.getBoolean("lightMode", true)
        darkModeRadioButton.isChecked = !lightModeRadioButton.isChecked
        notificationCheckBox.isChecked = sharedPreferences.getBoolean("notifications", false)
        emailEditText.setText(sharedPreferences.getString("email", ""))
        nicknameEditText.setText(sharedPreferences.getString("nickname", ""))

        saveButton.setOnClickListener {
            val editor = sharedPreferences.edit()

            editor.putBoolean("lightMode", lightModeRadioButton.isChecked)
            editor.putBoolean("notifications", notificationCheckBox.isChecked)
            editor.putString("email", emailEditText.text.toString())
            editor.putString("nickname", nicknameEditText.text.toString())

            editor.apply()

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
    }
}