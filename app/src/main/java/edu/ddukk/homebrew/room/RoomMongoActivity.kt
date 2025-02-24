package edu.ddukk.homebrew.room

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.ddukk.homebrew.App
import edu.ddukk.homebrew.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class roles(val _id: String, val role: String, val createdAt: String, val updatedAt:String)

class RoomMongoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_mongo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val uri = App.MONGO_CONNECTION_STRING

        runBlocking {
            launch {
//                val mongoClient : MongoClient = MongoClients.create(uri)
//                val database : MongoDatabase = mongoClient.getDatabase("EMonitor-tut")
//                val roles : MongoCollection<Document> = database.getCollection("roles")
//
//                val role = roles.find().firstOrNull()
//                if(role!= null)
//                    Toast.makeText(applicationContext, role.toString(), Toast.LENGTH_LONG).show()
//                else
//                    Toast.makeText(applicationContext, "no roles found", Toast.LENGTH_LONG).show()

            }
        }



    }
}