package edu.ddukk.homebrew.adapters

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.ddukk.homebrew.R
import edu.ddukk.homebrew.data.DataClass

class ArrayAdapterDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_array_adapter_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.lvArrayAdapter)

        val array: MutableList<String> = DataClass.SimpleStringData()

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout
                .simple_list_item_1, array
        )

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as String
            Toast.makeText(applicationContext, item + " Clicked", Toast.LENGTH_LONG).show();
        }


        val btnAdd: Button = findViewById(R.id.btnAddItem_ArrayAdapterDemo);
        val etInput: EditText = findViewById(R.id.etArrayAdapter_Input);

        btnAdd.setOnClickListener {
            val item = etInput.text.toString();
            if (item != "") {
                array.add(item)
                arrayAdapter.notifyDataSetChanged()
            } else
                Toast.makeText(
                    applicationContext, "Please enter a valid item", Toast
                        .LENGTH_SHORT
                ).show();

        }

    }
}