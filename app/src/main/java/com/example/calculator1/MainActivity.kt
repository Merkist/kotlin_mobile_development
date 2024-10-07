package com.example.calculator1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val part1Button: Button = findViewById(R.id.calculator1_button)
        val part2Button: Button = findViewById(R.id.calculator2_button)

        part1Button.setOnClickListener {
            val intent = Intent(this, part1_calculator::class.java)
            startActivity(intent)
        }

        part2Button.setOnClickListener {
            val intent = Intent(this, part2_calculator::class.java)
            startActivity(intent)
        }
    }
}