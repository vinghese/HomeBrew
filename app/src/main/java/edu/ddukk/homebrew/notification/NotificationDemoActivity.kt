package edu.ddukk.homebrew.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.ddukk.homebrew.MainActivity
import edu.ddukk.homebrew.R
import edu.ddukk.homebrew.utils.CustomDialog
import edu.ddukk.homebrew.utils.showCustomToast

class NotificationDemoActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManagerCompat
    val channelId = "Progress Notification" as String


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btnToast = findViewById<Button>(R.id.btnToast);
        var btnCustomToast = findViewById<Button>(R.id.btnCustomToast);
        var btnDialog = findViewById<Button>(R.id.btnDialog);
        var btnNotification = findViewById<Button>(R.id.btnNotification);


        var txtInput = findViewById<TextView>(R.id.tvInput);

        btnToast.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, txtInput.text, Toast.LENGTH_LONG).show();
        })

        btnCustomToast.setOnClickListener(View.OnClickListener {

            Toast(this).showCustomToast(txtInput.text.toString(), this);

        })

        btnDialog.setOnClickListener(View.OnClickListener {
            CustomDialog(this).show("Dialog Title", "Dialog Message") {

                val resp = it.toString()
                if (resp.equals("YES")) {
                    Toast.makeText(applicationContext, "Yes option clicked", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "No option clicked", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })


        notificationManager = NotificationManagerCompat.from(this)

        btnNotification.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            var progressMax = 100
            val notification = NotificationCompat.Builder(this, channelId).apply {
                setSmallIcon(android.R.drawable.stat_sys_download)
                setContentTitle("Download Notification")
                setContentText("Downloading")
                setPriority(NotificationCompat.PRIORITY_LOW)
                setOngoing(true)
                setOnlyAlertOnce(true)
                setProgress(progressMax, 0, true)
                setContentIntent(pendingIntent)
                setAutoCancel(true)

            }
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
            } else {
                Toast.makeText(applicationContext, "Else", Toast.LENGTH_SHORT).show()
                notificationManager.notify(0, notification.build())
            }

            Thread(Runnable {
                SystemClock.sleep(2000)
                var progress = 0
                while (progress<progressMax){
                    SystemClock.sleep(1000)
                    progress+=20
                    notification.setContentTitle("Progress : $progress%")
                        .setProgress(progressMax,progress, false)
                    notificationManager.notify(1,notification.build())
                }

                notification.setContentText("Download Completed")
                    .setProgress(0,0,false)
                    .setOngoing(false)
                notificationManager.notify(1, notification.build())
            }).start()

        })



    }

}