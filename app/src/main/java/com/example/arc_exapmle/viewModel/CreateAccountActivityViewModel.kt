package com.example.arc_exapmle.viewModel

import android.app.Application
import android.content.Intent
import android.text.Editable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.activity.LoginActivity
import com.example.arc_exapmle.activity.MainActivity
import com.example.arc_exapmle.user.UserEntity
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.user.UserUI

class CreateAccountActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = UserRepository(application)
    private val allUsers: LiveData<List<UserEntity>>

    init {
        allUsers = repository.getAllUsers()
    }

    fun createNewAccount(username: Editable, numericId: Editable): Int {

        when {
            username.trim() == "" -> //username.error = "This field cannot remain empty"
                return 0
            numericId.trim() == "" -> //usernameEditText.error = "This field cannot remain empty"
                return 1
            else -> {

                val foundedUsers = repository.findUserById(numericId.toString().toInt())

                when {
                    foundedUsers.isEmpty() -> {

                        val newUserEntity =
                            UserEntity(username.toString(), numericId.toString().toInt())

                        repository.newUser(UserUI(newUserEntity.username, newUserEntity.user_id))



                        return 2


                    }
                    foundedUsers.isNotEmpty() -> {

                        Log.i("CreateAccountActivityViewModel", "foundedUsers is not empty")


                        return 3


                    }
                    else -> return 4
                }


            }
        }
    }
}