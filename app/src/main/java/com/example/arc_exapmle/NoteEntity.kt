package com.example.arc_exapmle

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
class NoteEntity(
    var title: String,
    var description: String,
    var priority: Int
) {

    @PrimaryKey(autoGenerate = true)
    private var id = 0


    fun setId(id: Int) {
        this.id = id
    }


    fun getId(): Int {
        return id
    }
}