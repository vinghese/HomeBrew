package edu.ddukk.homebrew.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.homebrew.adapters.RecyclerAdapter
import edu.ddukk.homebrew.data.RecycerDataItem
import edu.ddukk.homebrew.R

class RecycerViewDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycer_view_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycerView: RecyclerView = findViewById(R.id.rvRecyclerViewDemo)

        recycerView.layoutManager = LinearLayoutManager(this)

        val data = RecycerDataItem.getData()

        val recyclerAdapter = RecyclerAdapter(data)

        recycerView.adapter = recyclerAdapter

    }
}