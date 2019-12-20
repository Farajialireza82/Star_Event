package com.example.arc_exapmle.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserViewModel(application: Application) :
    AndroidViewModel(application) {


    private var repository = UserRepository(application)
    private val allNotes: LiveData<List<UserEntity>>

    init {
        allNotes = repository.getAllUsers()
    }

    fun insert(user: UserEntity) {

        val userUI = UserUI(
            user.username, user.user_id
        )

        repository.newUser(userUI)
    }

    fun delete(user: UserEntity) {

        val userUI = UserUI(
            user.username, user.user_id
        )

        repository.deleteUser(userUI)
    }


    fun deleteAllNotes() {
        repository.deleteAllUsers()
    }

    fun getAllUsers(): LiveData<List<UserEntity>> {
        return repository.getAllUsers()
    }

    fun findUserById(numericId: Int): List<UserEntity> {
        return repository.findUserById(numericId)
    }

}