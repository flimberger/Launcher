package com.sirrgb.launcher

import android.content.Context
import android.database.DataSetObserver
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView

class LauncherEntry(val label: CharSequence, val icon: Drawable)

class LauncherAdapter(private val context: Context, private val objects: List<LauncherEntry>) : ListAdapter {
	override fun registerDataSetObserver(p0: DataSetObserver?) {
		// do nothing
	}

	override fun unregisterDataSetObserver(p0: DataSetObserver?) {
		// do nothing
	}

	override fun getCount(): Int {
		return objects.size
	}

	override fun getItem(p0: Int): LauncherEntry {
		return objects[p0]
	}

	override fun getItemId(p0: Int): Long {
		return p0.toLong()
	}

	override fun hasStableIds(): Boolean {
		return false
	}

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		val view = convertView ?: inflater.inflate(R.layout.launcher_entry, parent, false)
		val entry = getItem(position)
		view.findViewById<TextView>(R.id.app_name).text = entry.label.toString()
		view.findViewById<ImageView>(R.id.app_icon).setImageDrawable(entry.icon)
		return view
	}

	override fun getItemViewType(p0: Int): Int {
		return Adapter.IGNORE_ITEM_VIEW_TYPE
	}

	override fun getViewTypeCount(): Int {
		return 1
	}

	override fun isEmpty(): Boolean {
		return objects.isEmpty()
	}

	override fun areAllItemsEnabled(): Boolean {
		return true
	}

	override fun isEnabled(p0: Int): Boolean {
		return true
	}
}
