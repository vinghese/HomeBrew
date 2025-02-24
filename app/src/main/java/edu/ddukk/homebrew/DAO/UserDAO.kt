package edu.ddukk.homebrew.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ddukk.homebrew.data.User

@Dao
interface UserDAO {

    @Query("Select * from User")
    suspend fun getUsers(): List<User>

    @Query("Select * from User where id in (:userId)")
    suspend fun getUsersById(userId: Int): User

    @Query("Select * from User where email LIKE :email")
    suspend fun getUsersByEmail(email: String) : User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}