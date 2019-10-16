/*
package com.example.arc_exapmle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {


        @Synchronized
        open fun getInstance(context: Context?): NoteDataBase?{

            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context!!.applicationContext
                        , NoteDataBase::class.java, "note_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance

        }



    private var instance: NoteDataBase? = null

    abstract fun noteDao(): NoteDao?


}

*/
