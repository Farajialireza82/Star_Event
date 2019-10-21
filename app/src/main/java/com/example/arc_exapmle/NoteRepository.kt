package com.example.arc_exapmle

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(application: Application) {
    private var allNotes: LiveData<List<NoteEntity>>
    private var noteDao: NoteDao

    init {
        val dataBase: NoteDatabase = NoteDatabase.getInstance(application)
        noteDao = dataBase.noteDao()
        allNotes = noteDao.getAllNotes()
    }


    fun insert(note: NoteUI) {

        InsertNoteAsyncTask(noteDao).execute(note)

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

        return allNotes
    }

    private class InsertNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteUI, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteUI): Unit? {

            val noteEntity = NoteEntity(params[0].title, params[0].description, params[0].priority)
            noteEntity.setId(params[0].id)

            noteDao.insert(noteEntity)

            return null

        }
    }

    private class UpdateNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteUI, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteUI): Unit? {

            val noteEntity = NoteEntity(params[0].title, params[0].description, params[0].priority)
            noteEntity.setId(params[0].id)

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

            val noteEntity = NoteEntity(params[0].title, params[0].description, params[0].priority)
            noteEntity.setId(params[0].id)

            noteDao.update(noteEntity)

            return null

        }
    }

}

