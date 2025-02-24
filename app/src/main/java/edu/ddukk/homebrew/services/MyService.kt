package edu.ddukk.homebrew.services

import android.app.Service
import android.content.Intent
import android.icu.util.Calendar
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat

class MyService : Service() {
    private val tag = "MyService"
    private var isRunning = false

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "Service Created")
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        Log.d(tag, "Service Destroyed")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d(tag, "Service Started")
        isRunning = true

        Thread {
            while (isRunning) {
                val date = Calendar.getInstance().time
                val format = SimpleDateFormat.getTimeInstance()
                val currentTime = format.format(System.currentTimeMillis())
                val timeIntent = Intent("com.example.servicedemo.UPDATE_TIME")
                timeIntent.putExtra("currentTime", currentTime)
                sendBroadcast(timeIntent)
                Log.d("*******Time********", currentTime.toString())
                Thread.sleep(1000)

            }
        }.start()

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}