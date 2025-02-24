package edu.ddukk.homebrew.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView

class TimeReceiver(private val textView: TextView) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val currentTime = intent.getStringExtra("currentTime")
        Log.d(">>>>>>>>>Time<<<<<<<<<<<<<", currentTime.toString())
        textView.text = currentTime.toString()
    }
}