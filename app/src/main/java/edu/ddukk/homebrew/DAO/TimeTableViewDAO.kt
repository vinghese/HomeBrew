package edu.ddukk.homebrew.DAO

import androidx.room.Dao
import androidx.room.Query
import edu.ddukk.homebrew.data.TimeTableView

@Dao
interface TimeTableViewDAO {

    @Query("Select * from TimeTableView")
    suspend fun getAllTimeTableView(): List<TimeTableView>

    @Query("Select * from TimeTableView where day_of_week= :dayofweek")
    suspend fun getAllTimeTableView(dayofweek: String): List<TimeTableView>

}
