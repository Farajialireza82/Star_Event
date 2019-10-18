package com.example.arc_exapmle

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
 interface NoteDao {

    @Insert
    fun insert(note: NoteEntity?)

    @Update
    fun update(note: NoteEntity?)

    @Delete
    fun delete(note: NoteEntity?)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
     fun getAllNotes(): LiveData<List<NoteEntity>?>


}