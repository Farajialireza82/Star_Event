package com.example.arc_exapmle.user

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.arc_exapmle.note.NoteDao
import com.example.arc_exapmle.note.NoteDatabase
import com.example.arc_exapmle.note.NoteEntity
import org.w3c.dom.Entity

class UserRepository(application: Application) {
    private var allUsers: LiveData<List<UserEntity>>
    private var userDao: UserDao
    private lateinit var foundedUsers: List<UserEntity>

    init {
        val database: NoteDatabase =
            NoteDatabase.getInstance(application)

        userDao = database.userDao()
        allUsers = userDao.getAllUsers()
    }

    fun newUser(user: UserUI) {

        NewUserAsyncTask(userDao).execute(user)

    }

    fun deleteUser(user: UserUI) {

        DeleteUserAsyncTask(userDao).execute(user)

    }

    fun deleteAllUsers() {

        DeleteAllUsersAsyncTask(userDao).execute()

    }

    fun getAllUsers(): LiveData<List<UserEntity>> {
        return allUsers
    }

    fun findUserById(numericId: Int): List<UserEntity> {


        return FindUserById(userDao).execute(numericId).get()
    }


    private class NewUserAsyncTask(userDaoImpl: UserDao) : AsyncTask<UserUI, Unit, Unit>() {

        private var userDao = userDaoImpl

        override fun doInBackground(vararg params: UserUI): Unit? {

            val userEntity = UserEntity(params[0].username, params[0].user_id)
            userDao.insert(userEntity)

            return null

        }

    }

    private class DeleteUserAsyncTask(userDaoImpl: UserDao) : AsyncTask<UserUI, Unit, Unit>() {

        private var userDao = userDaoImpl

        override fun doInBackground(vararg params: UserUI): Unit? {

            val userEntity = UserEntity(params[0].username, params[0].user_id)
            userDao.delete(userEntity)

            return null

        }

    }

    private class DeleteAllUsersAsyncTask(userDaoImpl: UserDao) : AsyncTask<Unit, Unit, Unit>() {

        private var userDao = userDaoImpl

        override fun doInBackground(vararg params: Unit): Unit? {

            userDao.deleteAll()

            return null

        }

    }

    private class FindUserById(userDaoImpl: UserDao) : AsyncTask<Int , Unit , List<UserEntity>>(){

        private var userDao = userDaoImpl

        override fun doInBackground(vararg params: Int?): List<UserEntity>? {

            return userDao.findUserById(params[0].toString().toInt())


        }

    }



}