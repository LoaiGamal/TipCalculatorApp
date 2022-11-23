package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.math.ceil
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBTN.setOnClickListener { calculateTip() }

        binding.costET.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun calculateTip(){
        val stringInTextField = binding.costET.text.toString()
        val cost = stringInTextField.toDoubleOrNull() ?: return

        val selectedID = binding.radioGroup.checkedRadioButtonId
        val percentage = when(selectedID){
            R.id.optionTwentyPercent -> 0.20
            R.id.optionEighteenPercent -> 0.18
            else -> 0.15
        }

        var result: Double = cost * percentage

        if(binding.roundSwitch.isChecked){
            result = ceil(result)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(result)
        binding.resultTV.text = formattedTip
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}