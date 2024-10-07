package com.example.calculator1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import java.util.Locale

class part2_calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part2_calcultor)

        //введення значень
        val inputH: EditText = findViewById(R.id.editText_H_oil_input)
        val inputC: EditText = findViewById(R.id.editText_C_oil_input)
        val inputS: EditText = findViewById(R.id.editText_S_oil_input)
        val inputO: EditText = findViewById(R.id.editText_O_oil_input)
        val inputV: EditText = findViewById(R.id.editText_V_oil_input)
        val inputW: EditText = findViewById(R.id.editText_W_oil_input)
        val inputA: EditText = findViewById(R.id.editText_A_oil_input)
        val inputQ: EditText = findViewById(R.id.editText_Q_oil_input)

        //контрольний приклад
        inputH.setText("11.2")
        inputC.setText("85.5")
        inputS.setText("2.5")
        inputO.setText("0.8")
        inputV.setText("333.3")
        inputW.setText("2")
        inputA.setText("0.15")
        inputQ.setText("40.4")

        //результати обчислень
        val resultH: TextView = findViewById(R.id.textView_H_oil_working)
        val resultC: TextView = findViewById(R.id.textView_C_oil_working)
        val resultS: TextView = findViewById(R.id.textView_S_oil_working)
        val resultO: TextView = findViewById(R.id.textView_O_oil_working)
        val resultV: TextView = findViewById(R.id.textView_V_oil_working)
        val resultA: TextView = findViewById(R.id.textView_A_oil_working)
        val resultQ: TextView = findViewById(R.id.textView_Q_oil_working)

        //кнопки обчислення та повернення назад
        val backButton: Button = findViewById(R.id.button_back2)
        val sumButton: Button = findViewById(R.id.button_calculate2)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sumButton.setOnClickListener {
            val valueH = inputH.text.toString()
            val valueC = inputC.text.toString()
            val valueS = inputS.text.toString()
            val valueO = inputO.text.toString()
            val valueV = inputV.text.toString()
            val valueW = inputW.text.toString()
            val valueA = inputA.text.toString()
            val valueQ = inputQ.text.toString()

            // перевірка та наявність значень
            if (valueH.isNotEmpty() && valueC.isNotEmpty() && valueS.isNotEmpty()
                && valueV.isNotEmpty() && valueO.isNotEmpty() && valueW.isNotEmpty()
                && valueA.isNotEmpty() && valueQ.isNotEmpty()) {
                try {
                    val valueH_f = valueH.toFloat()
                    val valueC_f = valueC.toFloat()
                    val valueS_f = valueS.toFloat()
                    val valueO_f = valueO.toFloat()
                    val valueV_f = valueV.toFloat()
                    val valueW_f = valueW.toFloat()
                    val valueA_f = valueA.toFloat()
                    val valueQ_f = valueQ.toFloat()

                    // перевірка на коретність значень
                    if (valueH_f+valueC_f+valueS_f+valueO_f == 100.0f) {
                        val k1 = (100 - valueW_f - valueA_f)/100
                        val k2 = (100 - valueW_f)/100
                        val oilH = k1*valueH_f
                        val oilC = k1*valueC_f
                        val oilS = k1*valueS_f
                        val oilO = k1*valueO_f
                        val oilV = k2*valueV_f
                        val oilA = k2*valueA_f
                        val oilQ = valueQ_f*((100 - valueW_f - oilA)/100) - 0.025*valueW_f

                        resultH.text = String.format(Locale.US, "%.2f", oilH)
                        resultC.text = String.format(Locale.US, "%.2f", oilC)
                        resultS.text = String.format(Locale.US, "%.2f", oilS)
                        resultO.text = String.format(Locale.US, "%.2f", oilO)
                        resultV.text = String.format(Locale.US, "%.2f", oilV)
                        resultA.text = String.format(Locale.US, "%.2f", oilA)
                        resultQ.text = String.format(Locale.US, "%.2f", oilQ)

                    } else {
                        Toast.makeText(this, "Сума складових мазуту повинна бути 100%",
                            Toast.LENGTH_SHORT).show()
                    }

                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Введіть коректні числа.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Всі поля повинні бути заповненні.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}