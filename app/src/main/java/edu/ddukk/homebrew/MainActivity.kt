package edu.ddukk.homebrew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import edu.ddukk.homebrew.adapters.ArrayAdapterDemoActivity
import edu.ddukk.homebrew.fragment.FragmentDemoActivity
import edu.ddukk.homebrew.notification.NotificationDemoActivity
import edu.ddukk.homebrew.recyclerview.RecycerViewDemoActivity
import edu.ddukk.homebrew.room.RoomMongoActivity
import edu.ddukk.homebrew.room.RoomNoteActivity
import edu.ddukk.homebrew.veiwpager.ViewPagerActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnClick: Button = findViewById(R.id.btnClick)

        val txtWelcome: TextView = findViewById(R.id.tvWelcome)

        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnNotificationDemo = findViewById<Button>(R.id.btnNotificationDemo)
        val btnFragmentDemo = findViewById<Button>(R.id.btnFragmentActivity)

        val btnService = findViewById<Button>(R.id.btnServiceDemo)

        txtWelcome.setText(R.string.hello)

        btnService.setOnClickListener {
            startActivity(Intent(applicationContext, ServiceDemoActivity::class.java))
        }

        btnClick.setOnClickListener {
            val pass = etPassword.text

            Toast.makeText(applicationContext, pass, Toast.LENGTH_LONG).show()

        }

        btnNotificationDemo.setOnClickListener({
            val notificationIntent = Intent(
                applicationContext,
                NotificationDemoActivity::class.java
            )
            startActivity(notificationIntent)
        })

        btnFragmentDemo.setOnClickListener({
            val fragmentIntent = Intent(applicationContext, FragmentDemoActivity::class.java)
            startActivity(fragmentIntent)
        })

        val btnArrayAdapterDemo = findViewById<Button>(R.id.btnArrayAdapter)
        val btnRecyclerViewDemo = findViewById<Button>(R.id.btnRecyclerView)
        btnArrayAdapterDemo.setOnClickListener {
            startActivity(Intent(this, ArrayAdapterDemoActivity::class.java))
        }

        btnRecyclerViewDemo.setOnClickListener {
            startActivity(Intent(this, RecycerViewDemoActivity::class.java))
        }

        val btnRoomDemo: Button = findViewById(R.id.btnRoomDemo)
        btnRoomDemo.setOnClickListener {
            startActivity(Intent(this, RoomNoteActivity::class.java))
        }

        val btnMongo: Button = findViewById(R.id.btnMongoDemo)
        btnMongo.setOnClickListener {
            startActivity(Intent(this, RoomMongoActivity::class.java))
        }

        val btnViewPager: Button = findViewById(R.id.btnViewPagerDemo)
        btnViewPager.setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }

    }


}