package com.example.arc_exapmle;

import android.content.Intent;

import androidx.room.Entity;
import androidx.room.Index;

import com.example.arc_exapmle.activity.LoginActivity;
import com.example.arc_exapmle.activity.MainActivity;


@Entity( tableName = "user_table" , indices = {@Index("artist_id")})
class  Note2Java{
}

