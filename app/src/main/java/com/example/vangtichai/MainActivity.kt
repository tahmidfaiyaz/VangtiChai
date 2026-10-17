
package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var amount = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amount = savedInstanceState?.getString("saved_amount") ?: ""

        val amountText = findViewById<TextView>(R.id.amountText)
        val resultText = findViewById<TextView>(R.id.resultText)

        amountText.text = if (amount.isEmpty()) "0" else amount
        calculateChange(resultText)


        calculateChange(resultText)
        val digitButtons = listOf(
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9
        )

        for (buttonId in digitButtons) {
            val button = findViewById<Button>(buttonId)

            button.setOnClickListener {
                val digit = button.text.toString()

                if (amount == "0") {
                    amount = digit
                } else {
                    amount += digit
                }

                amountText.text = amount
                calculateChange(resultText)
            }
        }

        val clearButton = findViewById<Button>(R.id.clearButton)

        clearButton.setOnClickListener {
            amount = ""
            amountText.text = "0"
            resultText.text = "Enter an amount to calculate change."
        }
    }

    private fun calculateChange(resultText: TextView) {
        var remaining = amount.toIntOrNull() ?: 0

        val denominations = listOf(500, 100, 50, 20, 10, 5, 2, 1)
        val result = StringBuilder()

        for (denomination in denominations) {
            val count = remaining / denomination
            remaining %= denomination

            result.append(String.format("%-8s %s\n", "৳$denomination", count))
        }

        resultText.text = result.toString()
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("saved_amount", amount)

    }
}
