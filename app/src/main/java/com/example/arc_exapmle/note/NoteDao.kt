package com.example.arc_exapmle.note

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.arc_exapmle.user.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

   @Query("SELECT * FROM note_table WHERE user = :userID")
    fun getAllNotes(userID: Int): Flow<List<NoteEntity>>




}

