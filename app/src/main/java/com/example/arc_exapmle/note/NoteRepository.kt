package com.example.arc_exapmle.note

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.arc_exapmle.StarDatabase
import com.example.arc_exapmle.user.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NoteRepository(cNoteDao: NoteDao, private val userIdNumber: Int) {

    private val noteDao = cNoteDao
    val userId: Int = userIdNumber


    suspend fun insert(note: NoteUI) {

        noteDao.insert(
            NoteEntity(
                note.title,
                note.description,
                note.priority,
                note.userID,
                note.id
            )
        )


    }


    suspend fun delete(note: NoteUI) {

        val noteEntity = NoteEntity(
            note.title,
            note.description,
            note.priority,
            note.userID,
            note.id
        )



        noteDao.delete(noteEntity)


    }

    suspend fun deleteAllNotes() {

        noteDao.deleteAll()

    }

    fun getAllNotes(): Flow<List<NoteEntity>> {


            return noteDao.getAllNotes(userId)
        }

    }



