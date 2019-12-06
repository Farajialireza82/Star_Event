package com.example.arc_exapmle.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.ViewModelDelivery
import com.example.arc_exapmle.user.UserEntity
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.user.UserUI

class CreateAccountActivityViewModel : ViewModel() {

    private lateinit var repository: UserRepository

    private lateinit var mutableLiveData: MutableLiveData<ViewModelDelivery>


    fun setUserRepo(userRepo: UserRepository) {

        repository = userRepo

    }


    fun createNewAccount(username: String, numericId: String): LiveData<ViewModelDelivery> {



        var viewModelDelivery: ViewModelDelivery

        when {

            username.trim() == "" -> viewModelDelivery =
                ViewModelDelivery("this Field cannot remain empty", "username")

            numericId.trim() == "" -> viewModelDelivery =
                ViewModelDelivery("This field cannot remain empty", "id")

            else -> {

                val foundedUsers = repository.findUserById(numericId.toInt())

                when {
                    foundedUsers.isEmpty() -> {

                        val newUserEntity =
                            UserEntity(username, numericId.toInt())

                        repository.newUser(UserUI(newUserEntity.username, newUserEntity.user_id))



                        viewModelDelivery =
                            ViewModelDelivery("User Created Successfully . Log in again", "")


                    }
                    foundedUsers.isNotEmpty() -> {

                        Log.i("CreateAccountActivityViewModel", "foundedUsers is not empty")


                        viewModelDelivery = ViewModelDelivery("userId already exits", "username")


                    }
                    else -> viewModelDelivery = ViewModelDelivery("this is odd", "username")
                }


            }

        }
        mutableLiveData = MutableLiveData()

        mutableLiveData.value = viewModelDelivery

        return mutableLiveData
    }
}