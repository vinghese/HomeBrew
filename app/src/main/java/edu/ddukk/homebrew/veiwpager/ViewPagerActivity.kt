package edu.ddukk.homebrew.veiwpager

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.ddukk.homebrew.R
import edu.ddukk.homebrew.adapters.ViewPagerAdapter
import edu.ddukk.homebrew.fragment.FragmentAttendance
import edu.ddukk.homebrew.fragment.FragmentTimetable

class ViewPagerActivity : AppCompatActivity() {

    val viewPageAdapter: ViewPagerAdapter by lazy { ViewPagerAdapter(this) }
    val viewPager: ViewPager2 by lazy { findViewById(R.id.viewpager) }
    val tabLayout: TabLayout by lazy { findViewById(R.id.tabs) }

    //Drawer
    val drawerLayout: DrawerLayout by lazy { findViewById(R.id.drawer_layout) }
    val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) }
    val actionBarDrawerToggle: ActionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(
            this,
            drawerLayout, toolbar, R.string.nav_open, R.string.nav_close
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_pager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPageAdapter.addFragment(FragmentTimetable(), "Timetable")
        viewPageAdapter.addFragment(FragmentAttendance(), "Attendance")


        viewPager.adapter = viewPageAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPageAdapter.getPageTitle(position)
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else
            return super.onOptionsItemSelected(item)
    }
}