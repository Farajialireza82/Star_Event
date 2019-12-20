package com.example.arc_exapmle.note

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
 interface NoteDao {

    @Insert
    fun insert(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table WHERE user = :userID")
     fun getAllNotes(userID: Int): LiveData<List<NoteEntity>>

   @Query("SELECT * FROM note_table")
   fun getEveryNoteThereIs(): LiveData<List<NoteEntity>>




}