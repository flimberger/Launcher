package com.sirrgb.launcher

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {

	var finalFibonacci = -1
	var listOfFibonacci = arrayListOf<Int>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val sharedFibonacci = getPreferences(Context.MODE_PRIVATE)
		val existString = getString(R.string.biggestFibonacciNum)
		val maxCount = 5

		if (sharedFibonacci.contains(existString) && sharedFibonacci.getInt(getString(R.string.maxCount), -1) == maxCount) {
			//use stored value
		} else {
			calculateFibonacci(maxCount)
		}
		val startButton = findViewById<Button>(R.id.start)
		startButton.setOnClickListener() {
			goTo()
		}
	}

	fun calculateFibonacci(maxCount: Int) {
		var fibonacci = 1
		var startwert = 0
		var nachfolger = 1
		var i = 0			// Counting variable
		listOfFibonacci = arrayListOf(startwert, fibonacci)

		while(i < maxCount) {
			fibonacci = startwert + nachfolger
			startwert = nachfolger
			nachfolger = fibonacci
			listOfFibonacci.add(fibonacci)
			finalFibonacci = fibonacci
			System.out.println(listOfFibonacci)
			i++
		}

		shareFibonacci(maxCount)
	}

	fun shareFibonacci(maxCount: Int) {
		val sharedFibonacci = getPreferences(Context.MODE_PRIVATE)
		with (sharedFibonacci.edit()){
			putInt(getString(R.string.biggestFibonacciNum),finalFibonacci)
			putInt(getString(R.string.maxCount), maxCount)
		}
	}

	private fun goTo() {
		val intent = Intent(this,EngineerActivity::class.java).apply {
			//intent.putExtra("MESSAGE",finalFibonacci)

		}
		intent.putExtra("test",finalFibonacci.toString())
		intent.putIntegerArrayListExtra("ALL_THE_FIBONACCIS",listOfFibonacci)
		startActivity(intent)
	}
}