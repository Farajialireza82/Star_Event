package com.example.arc_exapmle.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insert(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table ")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE user_id =:userId")
    fun findUserById(userId: Int): List<UserEntity>


}