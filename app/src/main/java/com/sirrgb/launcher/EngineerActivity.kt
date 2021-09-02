package com.sirrgb.launcher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class EngineerActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_engineer)

		val allMainActivities = getAllMainActivities()
		allMainActivities.forEach {
			val label = it.loadLabel(packageManager)
			println(label)
		}
		val labels = allMainActivities.map {
			it.loadLabel(packageManager)
		}
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, labels)
		val mListView = findViewById<ListView>(R.id.listView)
		mListView.adapter = arrayAdapter
	}


	private fun getAllMainActivities(): List<ResolveInfo> {
		val intent = Intent(Intent.ACTION_MAIN)
		intent.addCategory(Intent.CATEGORY_LAUNCHER)
		return packageManager.queryIntentActivities(intent, 0)
	}
}
