package com.sirrgb.launcher

import android.content.ComponentName
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
		val entries = allMainActivities.map {
			val activityInfo = it.activityInfo
			LauncherEntry(it.loadLabel(packageManager), it.loadIcon(packageManager), ComponentName(activityInfo.packageName, activityInfo.name))
		}
		val adapter = LauncherAdapter(this, entries)
		val mListView = findViewById<ListView>(R.id.listView)
		mListView.adapter = adapter
		mListView.setOnItemClickListener { _, view, position, _ ->
			view?.context?.startActivity(
				entries[position].startIntent
			)
		}
	}


	private fun getAllMainActivities(): List<ResolveInfo> {
		val intent = Intent(Intent.ACTION_MAIN)
		intent.addCategory(Intent.CATEGORY_LAUNCHER)
		return packageManager.queryIntentActivities(intent, 0)
	}
}
