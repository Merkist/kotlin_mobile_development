package com.example.calculator1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import java.util.Locale

class part1_calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part1_calculator)

        //введення значень
        val inputH: EditText = findViewById(R.id.editText_H_input)
        val inputC: EditText = findViewById(R.id.editText_C_input)
        val inputS: EditText = findViewById(R.id.editText_S_input)
        val inputN: EditText = findViewById(R.id.editText_N_input)
        val inputO: EditText = findViewById(R.id.editText_O_input)
        val inputW: EditText = findViewById(R.id.editText_W_input)
        val inputA: EditText = findViewById(R.id.editText_A_input)

        //контрольний приклад
        inputH.setText("1.9")
        inputC.setText("21.1")
        inputS.setText("2.6")
        inputN.setText("0.2")
        inputO.setText("7.1")
        inputW.setText("53")
        inputA.setText("14.1")

        //результати обчислень
        val resultH_dry: TextView = findViewById(R.id.textView_H_dry)
        val resultC_dry: TextView = findViewById(R.id.textView_C_dry)
        val resultS_dry: TextView = findViewById(R.id.textView_S_dry)
        val resultN_dry: TextView = findViewById(R.id.textView_N_dry)
        val resultO_dry: TextView = findViewById(R.id.textView_O_dry)
        val resultA_dry: TextView = findViewById(R.id.textView_A_dry)
        val resultH_combustible: TextView = findViewById(R.id.textView_H_combustible)
        val resultC_combustible: TextView = findViewById(R.id.textView_C_combustible)
        val resultS_combustible: TextView = findViewById(R.id.textView_S_combustible)
        val resultN_combustible: TextView = findViewById(R.id.textView_N_combustible)
        val resultO_combustible: TextView = findViewById(R.id.textView_O_combustible)
        val result_LowerHeat_working: TextView = findViewById(R.id.textView_LowerHeat_working)
        val result_LowerHeat_dry: TextView = findViewById(R.id.textView_LowerHeat_dry)
        val result_LowerHeat_combustible: TextView = findViewById(R.id.textView_LowerHeat_combustible)

        //кнопки обчислення та повернення назад
        val backButton: Button = findViewById(R.id.button_back)
        val sumButton: Button = findViewById(R.id.button_calculate)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sumButton.setOnClickListener {
            val valueH = inputH.text.toString()
            val valueC = inputC.text.toString()
            val valueS = inputS.text.toString()
            val valueN = inputN.text.toString()
            val valueO = inputO.text.toString()
            val valueW = inputW.text.toString()
            val valueA = inputA.text.toString()

            // перевірка та наявність значень
            if (valueH.isNotEmpty() && valueC.isNotEmpty() && valueS.isNotEmpty()
                && valueN.isNotEmpty() && valueO.isNotEmpty() && valueW.isNotEmpty()
                && valueA.isNotEmpty()) {
                try {
                    val valueH_f = valueH.toFloat()
                    val valueC_f = valueC.toFloat()
                    val valueS_f = valueS.toFloat()
                    val valueN_f = valueN.toFloat()
                    val valueO_f = valueO.toFloat()
                    val valueW_f = valueW.toFloat()
                    val valueA_f = valueA.toFloat()

                    // перевірка на коретність значень
                    if (valueH_f+valueC_f+valueS_f+valueN_f+valueO_f+valueW_f+valueA_f == 100.0f) {
                        // коефіцієнти перехода
                        val k_wd = 100/(100-valueW_f)
                        val k_wc = 100/(100-valueW_f-valueA_f)

                        // обчислення складу сухої маси палива
                        val dryH = k_wd*valueH_f
                        val dryC = k_wd*valueC_f
                        val dryS = k_wd*valueS_f
                        val dryN = k_wd*valueN_f
                        val dryO = k_wd*valueO_f
                        val dryA = k_wd*valueA_f

                        // обчислення складу горючої маси палива
                        val combustibleH = k_wc*valueH_f
                        val combustibleC = k_wc*valueC_f
                        val combustibleS = k_wc*valueS_f
                        val combustibleN = k_wc*valueN_f
                        val combustibleO = k_wc*valueO_f

                        // обчислення нижчої теплоти згоряння
                        val LowerHeat_working = (339*valueC_f + 1030*valueH_f -
                                108.8*(valueO_f - valueS_f) - 25*valueW_f)/1000
                        val LowerHeat_dry = (LowerHeat_working+0.025*valueW_f)*(100/(100-valueW_f))
                        val LowerHeat_combustible = (LowerHeat_working+0.025*valueW_f)*
                                (100/(100-valueW_f-valueA_f))

                        //вивід отриманих значень
                        resultH_dry.text = String.format(Locale.US, "%.2f", dryH)
                        resultC_dry.text = String.format(Locale.US, "%.2f", dryC)
                        resultS_dry.text = String.format(Locale.US, "%.2f", dryS)
                        resultN_dry.text = String.format(Locale.US, "%.2f", dryN)
                        resultO_dry.text = String.format(Locale.US, "%.2f", dryO)
                        resultA_dry.text = String.format(Locale.US, "%.2f", dryA)
                        resultH_combustible.text = String.format(Locale.US, "%.2f",
                            combustibleH)
                        resultC_combustible.text = String.format(Locale.US, "%.2f",
                            combustibleC)
                        resultS_combustible.text = String.format(Locale.US, "%.2f",
                            combustibleS)
                        resultN_combustible.text = String.format(Locale.US, "%.2f",
                            combustibleN)
                        resultO_combustible.text = String.format(Locale.US, "%.2f",
                            combustibleO)
                        result_LowerHeat_working.text = String.format(Locale.US, "%.2f",
                            LowerHeat_working)
                        result_LowerHeat_dry.text = String.format(Locale.US, "%.2f",
                            LowerHeat_dry)
                        result_LowerHeat_combustible.text = String.format(Locale.US, "%.2f",
                            LowerHeat_combustible)

                    } else {
                        Toast.makeText(this, "Сума всіх значень повинна дорівнювати 100%",
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