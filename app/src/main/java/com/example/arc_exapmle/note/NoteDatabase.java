package com.example.arc_exapmle.note;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.arc_exapmle.user.UserDao;
import com.example.arc_exapmle.user.UserEntity;

@Database(entities = {NoteEntity.class , UserEntity.class}, version = 3, exportSchema = false )

public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public abstract UserDao userDao();

    public static synchronized NoteDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();

        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private UserDao userDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new NoteEntity("Hint 1 ", "Touch the red button to add notes ", 1 , 82 , 0) );
            noteDao.insert(new NoteEntity("Hint 2 ", "scroll right or left to delete them ", 2  , 82 , 0 ));
            noteDao.insert(new NoteEntity("Hint 3 ", "Delete all notes from the ActionBar ", 3 , 82 , 0));
            userDao.insert(new UserEntity("James_Bond" , 123));

            return null;
        }
    }

}
