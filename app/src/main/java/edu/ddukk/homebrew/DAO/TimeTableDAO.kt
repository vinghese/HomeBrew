package edu.ddukk.homebrew.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import edu.ddukk.homebrew.data.Breaks
import edu.ddukk.homebrew.data.Instructors
import edu.ddukk.homebrew.data.Programs
import edu.ddukk.homebrew.data.Subjects
import edu.ddukk.homebrew.data.TimeTable

@Dao
interface TimeTableDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimetable(timetable: TimeTable)

    @Update
    suspend fun updateTimetable(timetable: TimeTable)

    @Delete
    suspend fun deleteTimetable(timetable: TimeTable)

    @Query("SELECT * FROM timetable WHERE day_of_week = :day ORDER BY period")
    suspend fun getTimetableForDay(day: String): List<TimeTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subjects)

    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<Subjects>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstructor(instructor: Instructors)

    @Query("SELECT * FROM instructors")
    suspend fun getAllInstructors(): List<Instructors>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreak(breakInfo: Breaks)

    @Query("SELECT * FROM breaks")
    suspend fun getAllBreaks(): List<Breaks>

    @Query("SELECT * FROM programs")
    suspend fun getAllPrograms(): List<Programs>


    @Transaction
    @Query(
        """
        SELECT timetable.* 
        FROM timetable
        INNER JOIN subjects ON timetable.subject_id = subjects.subject_id
        WHERE subjects.subject_name = :subjectName
        ORDER BY timetable.day_of_week, timetable.period
    """
    )
    suspend fun getTimetableForSubject(subjectName: String): List<TimeTable>

    @Transaction
    @Query(
        """
        SELECT timetable.* 
        FROM timetable
        INNER JOIN instructors ON timetable.instructor_id = instructors.instructor_id
        WHERE instructors.instructor_name = :instructorName
        ORDER BY timetable.day_of_week, timetable.period
    """
    )
    suspend fun getTimetableForInstructor(instructorName: String): List<TimeTable>


}