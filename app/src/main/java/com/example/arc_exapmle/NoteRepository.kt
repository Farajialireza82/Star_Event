package com.example.arc_exapmle

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(application: Application) {
    private var allNotes: LiveData<List<NoteEntity>?>
    private var noteDao: NoteDao

    init {
        val dataBase: NoteDatabase = NoteDatabase.getInstance(application)
        noteDao = dataBase.noteDao()
        allNotes = noteDao.getAllNotes()
    }


    fun insert(note: NoteEntity) {

        InsertNoteAsyncTask(noteDao).execute(note)

    }

    fun update(note: NoteEntity) {

        UpdateNoteAsyncTask(noteDao).execute(note)

    }

    fun delete(note: NoteEntity) {

        DeleteNoteAsyncTask(noteDao).execute(note)

    }

    fun deleteAllNotes() {

        DeleteAllNotesAsyncTask(noteDao).execute()

    }

    fun getAllNotes(): LiveData<List<NoteEntity>?> {
        return allNotes
    }

    private class InsertNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteEntity, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteEntity?): Unit? {


            noteDao.insert(params[0])

            return null

        }
    }

    private class UpdateNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteEntity, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteEntity?): Unit? {

            noteDao.update(params[0])

            return null

        }
    }

    private class DeleteAllNotesAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteEntity, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteEntity?): Unit? {


            noteDao.deleteAll()

            return null

        }
    }

    private class DeleteNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<NoteEntity, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: NoteEntity?): Unit? {

            noteDao.delete(params[0])

            return null

        }
    }

}

