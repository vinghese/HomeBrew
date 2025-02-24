package edu.ddukk.homebrew

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.widget.Toast

class App: Application(){
    val channelId = "Progress Notification" as String

    companion object {
        val MONGO_CONNECTION_STRING = "mongodb+srv://emonitor:emonitor2025@cluster0.jzxrx.mongodb" +
                ".net/?retryWrites=true&w=majority&appName=Cluster0"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannles()
    }

    private fun createNotificationChannles() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Progress Notification",
                NotificationManager.IMPORTANCE_HIGH)
            channel.description ="Progress Notification Channel"
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

            Toast.makeText(applicationContext,"Notification Channel Created", Toast.LENGTH_SHORT).show()
        } 


    }
}