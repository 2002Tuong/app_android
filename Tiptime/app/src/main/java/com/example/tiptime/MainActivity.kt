package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{
            calculateTip()
        }
    }
    private fun calculateTip()
    {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null)
        {
            binding.tipResultTextview.text = ""
            return
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
    }
}
