package com.sirrgb.launcher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class EngineerActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_engineer)

		val allMainActivities = getAllMainActivities()
		val labels = allMainActivities.map {
			LauncherEntry(it.loadLabel(packageManager), it.loadIcon(packageManager))
		}
		val adapter = LauncherAdapter(this, labels)
		val mListView = findViewById<ListView>(R.id.listView)
		mListView.adapter = adapter
	}


	private fun getAllMainActivities(): List<ResolveInfo> {
		val intent = Intent(Intent.ACTION_MAIN)
		intent.addCategory(Intent.CATEGORY_LAUNCHER)
		return packageManager.queryIntentActivities(intent, 0)
	}
}
