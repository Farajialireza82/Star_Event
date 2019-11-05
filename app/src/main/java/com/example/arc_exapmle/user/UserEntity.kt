package com.example.arc_exapmle.user

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "user_table" )
class UserEntity(
    var username:String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id" )
    var user_id: Int

) : Serializable