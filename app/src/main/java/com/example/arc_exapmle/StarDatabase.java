package com.example.arc_exapmle;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.arc_exapmle.note.NoteDao;
import com.example.arc_exapmle.note.NoteEntity;
import com.example.arc_exapmle.user.UserDao;
import com.example.arc_exapmle.user.UserEntity;

@Database(entities = {NoteEntity.class , UserEntity.class}, version = 3, exportSchema = false )

public abstract class StarDatabase extends RoomDatabase {

    private static StarDatabase instance;

    public abstract NoteDao noteDao();

    public abstract UserDao userDao();


    public static synchronized StarDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , StarDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }





}
