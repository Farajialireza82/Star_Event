package com.example.arc_exapmle.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.arc_exapmle.user.UserEntity
import java.io.Serializable

@Entity(
    tableName = "note_table",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("note_id"),
        childColumns = arrayOf("user_id")
    )]
)
class NoteEntity(
    var title: String,
    var description: String,
    var priority: Int,
    @ColumnInfo(name = "user_id" )
    var userId:Int
    ) : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private var noteId = 0


    fun setNoteId(note_id: Int) {
        this.noteId = note_id
    }


    fun getNoteId(): Int {
        return noteId
    }
}