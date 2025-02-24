package edu.ddukk.homebrew.room

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.ddukk.homebrew.DAO.TimeTableDAO
import edu.ddukk.homebrew.DAO.UserDAO
import edu.ddukk.homebrew.R
import edu.ddukk.homebrew.data.Programs
import edu.ddukk.homebrew.data.User
import edu.ddukk.homebrew.databases.AppDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RoomNoteActivity : AppCompatActivity() {

    private val db: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    private val userDAO: UserDAO by lazy { db.userDAO() }
    private val timetableDAO: TimeTableDAO by lazy { db.timetableDAO() }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRoomTest: Button = findViewById(R.id.btnRoomInsert)
        btnRoomTest.setOnClickListener {

            try {

                //  userDAO = db.userDAO()
                InsertDemo();

            } catch (error: Exception) {
                Log.e("DB Error", error.message as String)
                throw error
            }
        }

        val btnRoomRead: Button = findViewById(R.id.btnRoomRead)
        btnRoomRead.setOnClickListener {
            try {
                //    userDAO = db.userDAO()
//                SelectDemo();
                ProgramsSelectDemo()
            } catch (error: Exception) {
                Log.e("DB Error", error.message as String)
                throw error
            }
        }
    }

    private fun InsertDemo() = runBlocking {
        launch {
            val res = userDAO?.insertUser(
                User(
                    firstName = "Anu",
                    lastName = "Varghese",
                    email = "anu@gmail.com",
                    password = "anu"
                )
            )
            Toast.makeText(applicationContext, "Data Inserted", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            launch {
                val res = userDAO?.insertUser(
                    User(
                        firstName = "Binu",
                        lastName = "Simon",
                        email = "Binu@gmail.com",
                        password = "binu"
                    )
                )
                Toast.makeText(applicationContext, "Data Inserted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun SelectDemo() = runBlocking {
        launch {
            val res: List<User>? = userDAO?.getUsers()
            for (item in res!!.iterator()) {
                Log.d("User", item.toString())
                Toast.makeText(
                    applicationContext, item.id.toString() + " " + item.lastName, Toast
                        .LENGTH_SHORT
                )
                    .show()
            }
            // val item = res?.get(0) as User

        }
    }

    private fun ProgramsSelectDemo() = runBlocking {
        launch {
            val res: List<Programs>? = timetableDAO.getAllPrograms()
            for (item in res!!.iterator()) {
                Log.d("Programs", item.toString())
                Toast.makeText(
                    applicationContext, item.programsId.toString() + " " + item.programeName, Toast
                        .LENGTH_SHORT
                )
                    .show()
            }
            // val item = res?.get(0) as User

        }
    }

}