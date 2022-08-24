package com.example.diceroll

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            val diceImageView1: ImageView = findViewById(R.id.imageView)
            val rollResult1 = rollDice()
            diceImageView1.setImageResource(getDrawableRsc(rollResult1))
            diceImageView1.contentDescription = rollResult1.toString()

            val diceImageView2: ImageView = findViewById(R.id.imageView2)
            val rollResult2 = rollDice()
            diceImageView2.setImageResource(getDrawableRsc(rollResult2))
            diceImageView2.contentDescription = rollResult2.toString()
        }
    }
}

class Dice(private var numSides: Int = 6) {
    fun roll() = (1..numSides).random()
}

//roll the dice anh return the value that the dice roll
fun rollDice(side: Int = 6) = Dice(side).roll()
fun getDrawableRsc(rollResult : Int) : Int
{
    val drawableRsc = when(rollResult)
    {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    return drawableRsc
}