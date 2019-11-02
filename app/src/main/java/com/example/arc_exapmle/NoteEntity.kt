package com.example.arc_exapmle

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table" )
class NoteEntity(
    var title: String,
    var description: String,
    var priority: Int
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    private var noteId = 0


    fun setId(note_id: Int) {
        this.noteId = note_id
    }


    fun getId(): Int {
        return noteId
    }
}