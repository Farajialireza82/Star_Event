package com.example.arc_exapmle

import androidx.lifecycle.LiveData
import androidx.room.Entity
import java.io.Serializable


@Entity
class UserEntity(
    var name: String,
    var user_id: String
) : Serializable