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
        parentColumns = arrayOf("user_id"),
        childColumns = arrayOf("user")
    )]
)
class NoteEntity(
    var title: String,

    var description: String,
    var priority: Int,
    @ColumnInfo(name = "user")
    var userId: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Int = 0
)
