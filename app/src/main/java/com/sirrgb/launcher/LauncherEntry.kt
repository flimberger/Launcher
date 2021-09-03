package com.sirrgb.launcher

import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.Drawable

class LauncherEntry(val label: CharSequence, val icon: Drawable, componentName: ComponentName) {
	val startIntent: Intent = Intent(Intent.ACTION_MAIN)

	init {
		startIntent.component = componentName
	}
}
