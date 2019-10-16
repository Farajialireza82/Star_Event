package com.example.arc_exapmle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
    private var title: String?,
    private var description: String?,
    private var priority: Int?
) {

    @PrimaryKey(autoGenerate = true)
    private var id = 0

    fun setId(id : Int){
        this.id = id
    }


    fun getId(): Int {
        return id
    }

    fun getTitle(): String {
        return title!!
    }

    fun getDescription(): String {
        return description!!
    }


    fun getPriority(): Int? {
        return priority
    }



}