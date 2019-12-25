package com.example.arc_exapmle.user

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.arc_exapmle.StarDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch

class UserRepository(application: Application) {
    private var allUsers: LiveData<List<UserEntity>>
    private var userDao: UserDao

    init {
        val database: StarDatabase =
            StarDatabase.getInstance(application)

        userDao = database.userDao()
        allUsers = userDao.getAllUsers()
    }

    suspend fun newUser(user: UserUI) {

        val userEntity = UserEntity(user.username, user.user_id)



        userDao.insert(userEntity)

    }

    fun deleteUser(user: UserUI) {

        val userEntity = UserEntity(user.username, user.user_id)

        CoroutineScope(Default).launch {

            userDao.delete(userEntity)

        }

    }

    suspend fun deleteAllUsers() {

        userDao.deleteAll()

    }

    fun getAllUsers(): LiveData<List<UserEntity>> {
        return allUsers
    }

    fun findUserById(numericId: Int): List<UserEntity> {


        return FindUserById(userDao).execute(numericId).get()
    }


/*    private class NewUserAsyncTask(userDaoImpl: UserDao) : AsyncTask<UserUI, Unit, Unit>() {

        private var userDao = userDaoImpl

        override fun doInBackground(vararg params: UserUI): Unit? {

            val userEntity = UserEntity(params[0].username, params[0].user_id)
            userDao.insert(userEntity)

            return null

        }

    }*/



    private class FindUserById(userDaoImpl: UserDao) : AsyncTask<Int , Unit , List<UserEntity>>(){

        private var userDao = userDaoImpl

        override fun doInBackground(vararg params: Int?): List<UserEntity>? {

            return userDao.findUserById(params[0].toString().toInt())


        }

    }



}