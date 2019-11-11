package com.example.arc_exapmle.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


/*
@Entity(tableName = "user_table",
    indices = [Index("user_id")])
data class UserEntity(
    var username:String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var user_id: Int
)*/
@Entity(tableName = "user_table",
    indices = [Index("user_id")])
data class UserEntity(
    var username:String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var user_id: Int
){
    override fun toString(): String {
        return "username = $username  , userId = $user_id"
    }
}
