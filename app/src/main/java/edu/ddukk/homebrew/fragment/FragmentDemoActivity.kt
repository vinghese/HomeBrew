package edu.ddukk.homebrew.fragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import edu.ddukk.homebrew.R

class FragmentDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var fragment: Fragment

        val btnFragmentOne = findViewById<Button>(R.id.btnShowFragmentOne)
        val btnFragmentTwo: Button = findViewById(R.id.btnShowFragmentTwo)

        btnFragmentOne.setOnClickListener({
            fragment = FragmentAttendance()
            showFragment(fragment)
        })

        btnFragmentTwo.setOnClickListener({
            fragment = FragmentTimetable()
            showFragment(fragment)
        })


    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_demo, fragment)
        fragmentTransaction.commit()
    }
}