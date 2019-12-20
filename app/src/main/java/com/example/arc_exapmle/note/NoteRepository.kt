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
    private var allNotes: LiveData<List<NoteEntity>>
    private var noteDao: NoteDao

    init {
        val dataBase: StarDatabase =
            StarDatabase.getInstance(application)
        noteDao = dataBase.noteDao()
        allNotes = noteDao.getAllNotes(userId)
    }


    suspend fun insert(note: NoteUI) {

            noteDao.insert( NoteEntity(note.title, note.description, note.priority, note.userID , note.id))




    }

    fun update(note: NoteUI) {

        UpdateNoteAsyncTask(noteDao).execute(note)

    }


    fun delete(note: NoteUI) {

        DeleteNoteAsyncTask(noteDao).execute(note)

    }

    fun deleteAllNotes() {

        DeleteAllNotesAsyncTask(noteDao).execute()

    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return if (userId == 36255528) {

            noteDao.getEveryNoteThereIs()

        } else {

            allNotes

        }

    }


    private class UpdateNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteUI, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteUI): Unit? {

            val noteEntity = NoteEntity(
                params[0].title,
                params[0].description,
                params[0].priority,
                params[0].userID,
                params[0].id
            )

            noteDao.update(noteEntity)

            return null

        }
    }

    private class DeleteAllNotesAsyncTask(noteDaoNew: NoteDao) : AsyncTask<Unit, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: Unit): Unit? {

            noteDao.deleteAll()

            return null

        }
    }

    private class DeleteNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteUI, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteUI): Unit? {

            val noteEntity = NoteEntity(
                params[0].title,
                params[0].description,
                params[0].priority,
                params[0].userID,
                params[0].id
            )

            noteDao.delete(noteEntity)

            return null

        }
    }

}

