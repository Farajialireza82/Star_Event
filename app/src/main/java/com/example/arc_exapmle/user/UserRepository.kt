package com.example.arc_exapmle.user

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.arc_exapmle.StarDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

class UserRepository(cUserDao: UserDao) {
    private var userDao = cUserDao
    private var allUsers = userDao.getAllUsers()


    suspend fun newUser(user: UserUI) {

        val userEntity = UserEntity(user.username, user.user_id)

        userDao.insert(userEntity)

    }

    suspend fun deleteUser(user: UserUI) {

        val userEntity = UserEntity(user.username, user.user_id)

        userDao.delete(userEntity)

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


    private class FindUserById(userDao: UserDao) : AsyncTask<Int, Unit, List<UserEntity>>() {

        private var userDao = userDao

        override fun doInBackground(vararg params: Int?): List<UserEntity>? {

            return userDao.findUserById(params[0].toString().toInt())


        }

    }


}