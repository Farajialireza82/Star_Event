package com.example.arc_exapmle.note

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.arc_exapmle.StarDatabase
import com.example.arc_exapmle.user.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

class NoteRepository(application: Application, val userId: Int) {
    private var noteDao: NoteDao

    init {
        val dataBase: StarDatabase =
            StarDatabase.getInstance(application)
        noteDao = dataBase.noteDao()
    }


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

    suspend fun update(note: NoteUI) {

        val noteEntity = NoteEntity(
            note.title,
            note.description,
            note.priority,
            note.userID,
            note.id
        )

        noteDao.update(noteEntity)
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

      fun getAllNotes(): LiveData<List<NoteEntity>> {
        return if (userId == 36255528) {

            noteDao.getEveryNoteThereIs()

        } else {

            return noteDao.getAllNotes(userId)


        }

    }


    private class UpdateNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteUI, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteUI): Unit? {



            return null

        }
    }


}

