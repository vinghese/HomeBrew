package edu.ddukk.homebrew.databases

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.ddukk.homebrew.DAO.TimeTableDAO
import edu.ddukk.homebrew.DAO.TimeTableViewDAO
import edu.ddukk.homebrew.DAO.UserDAO
import edu.ddukk.homebrew.data.Breaks
import edu.ddukk.homebrew.data.Instructors
import edu.ddukk.homebrew.data.Programs
import edu.ddukk.homebrew.data.Semesters
import edu.ddukk.homebrew.data.Subjects
import edu.ddukk.homebrew.data.TimeTable
import edu.ddukk.homebrew.data.TimeTableView
import edu.ddukk.homebrew.data.User

@Database(
    entities = [User::class, TimeTable::class, Subjects::class, Instructors::class,
        Semesters::class, Breaks::class, Programs::class],
    views = [TimeTableView::class],
    version = 10,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 9, to = 10)
    ]
)


abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun timetableDAO(): TimeTableDAO
    abstract fun viewDAO(): TimeTableViewDAO


    companion object {

        val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE IF EXISTS new_subjects")
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `new_subjects` " +
                            "(`subject_id` INTEGER NOT NULL , `subject_code` TEXT " +
                            "NOT " +
                            "NULL " +
                            "DEFAULT `23-493` ,  " +
                            "`subject_name` TEXT NOT NULL, PRIMARY KEY" +
                            "(`subject_id` AUTOINCREMENT))  "
                )
                database.execSQL(
                    "INSERT INTO new_subjects (subject_name) SELECT subject_name from " +
                            "subjects"
                )
                database.execSQL("DROP TABLE IF EXISTS subjects")
                database.execSQL("ALTER TABLE new_subjects RENAME TO subjects")
            }
        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "test-db"
            )
//                .addMigrations(MIGRATION_6_7)
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }


//    database.execSQL("DROP TABLE IF EXISTS new_folders_table")
//    database.execSQL("CREATE TABLE IF NOT EXISTS `new_folders_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `folderName` TEXT NOT NULL, `infoForSaving` TEXT NOT NULL);")
//    database.execSQL("INSERT INTO `new_folders_table` (folderName,infoForSaving) SELECT folderName,infoForSaving FROM folders_table;")
//    database.execSQL("DROP TABLE IF EXISTS `folders_table`;")
//    database.execSQL("ALTER TABLE `new_folders_table` RENAME TO `folders_table`;")

//    insert into timetable values(1,"Monday","9.00 AM","10.00 AM","1",1,3,3,1,1)

}