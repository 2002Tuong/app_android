package com.example.tiptime_update

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.tiptime_update.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculateTip()
            hideSoftKeyboard(this)
        }
        binding.costOfServiceEditText.setOnKeyListener { v, keyCode, _ -> handleKeyEvent(v,keyCode) }
    }

    private fun calculateTip() : Int
    {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null)
        {
            binding.tipResultTextview.text = ""
            return 1
        }
        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage
        tip =  when(binding.roundUpSwitch.isChecked)
        {
            true-> ceil(tip)
            else-> tip
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResultTextview.text = getString(R.string.tip_amount,formattedTip)
        return 1
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_ALL_APPS) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                0
            )
        }
    }
}
