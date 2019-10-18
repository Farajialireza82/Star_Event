package com.example.arc_exapmle

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(application: Application) {
    private var allNotes: LiveData<List<Note>?>
    private var noteDao: NoteDao

    init {
        val dataBase: NoteDatabase = NoteDatabase.getInstance(application)
        noteDao = dataBase.noteDao()
        allNotes = noteDao.getAllNotes()
    }


    fun insert(note: Note) {

        InsertNoteAsyncTask(noteDao).execute(note)

    }

    fun update(note: Note) {

        UpdateNoteAsyncTask(noteDao).execute(note)

    }

    fun delete(note: Note) {

        DeleteNoteAsyncTask(noteDao).execute(note)

    }

    fun deleteAllNotes() {

        DeleteAllNotesAsyncTask(noteDao).execute()

    }

    fun getAllNotes(): LiveData<List<Note>?> {
        return allNotes
    }

    private class InsertNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: Note?): Unit? {


            noteDao.insert(params[0])

            return null

        }
    }

    private class UpdateNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: Note?): Unit? {

            noteDao.update(params[0])

            return null

        }
    }

    private class DeleteAllNotesAsyncTask(noteDaoNew: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: Note?): Unit? {


            noteDao.deleteAll()

            return null

        }
    }

    private class DeleteNoteAsyncTask(noteDaoNew: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        private var noteDao: NoteDao = noteDaoNew

        override fun doInBackground(vararg params: Note?): Unit? {

            noteDao.delete(params[0])

            return null

        }
    }

}

