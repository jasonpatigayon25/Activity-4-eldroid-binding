package com.patigayon.activity4

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.patigayon.activity4.databinding.Activity4Binding

class Activity4 : AppCompatActivity() {

    private lateinit var binding: Activity4Binding
    private val sharedPrefFile = "settingsPreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Activity4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.lightMode.isChecked = sharedPreferences.getBoolean("lightMode", true)
        binding.darkMode.isChecked = !binding.lightMode.isChecked
        binding.notificationCheckbox.isChecked = sharedPreferences.getBoolean("notifications", false)
        binding.emailEditText.setText(sharedPreferences.getString("email", ""))
        binding.nicknameEditText.setText(sharedPreferences.getString("nickname", ""))

        binding.saveButton.setOnClickListener {
            val editor = sharedPreferences.edit()

            editor.putBoolean("lightMode", binding.lightMode.isChecked)
            editor.putBoolean("notifications", binding.notificationCheckbox.isChecked)
            editor.putString("email", binding.emailEditText.text.toString())
            editor.putString("nickname", binding.nicknameEditText.text.toString())

            editor.apply()

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
