package edu.ddukk.homebrew

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.ddukk.homebrew.broadcasts.TimeReceiver
import edu.ddukk.homebrew.services.MyService

class ServiceDemoActivity : AppCompatActivity() {

    private lateinit var timeTextView: TextView
    private lateinit var timeReceiver: TimeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_service_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        timeTextView = findViewById(R.id.timeTextView)
        val startButton: Button = findViewById(R.id.startButton)
        val stopButton: Button = findViewById(R.id.stopButton)

        timeReceiver = TimeReceiver(timeTextView)
        val intentFilter = IntentFilter("com.example.servicedemo.UPDATE_TIME")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(timeReceiver, intentFilter, Context.RECEIVER_EXPORTED)
            Toast.makeText(applicationContext, "Receiver Registered", Toast.LENGTH_LONG).show()
        }

        startButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        stopButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeReceiver)
    }
}