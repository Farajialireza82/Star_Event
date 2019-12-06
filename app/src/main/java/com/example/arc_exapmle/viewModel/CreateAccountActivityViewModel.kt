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

    var mutableLiveData: MutableLiveData<ViewModelDelivery> = MutableLiveData()
    var toastMutableLiveData: MutableLiveData<ViewModelDelivery> = MutableLiveData()
      var usernameEditTextMutableLiveData: MutableLiveData<String> = MutableLiveData()
      var idEditTextMutableLiveData: MutableLiveData<String> = MutableLiveData()




    fun setUserRepo(userRepo: UserRepository) {

        repository = userRepo

    }


    fun createNewAccount(username: String, numericId: String){

        when {

            username.trim() == "" -> usernameEditTextMutableLiveData.value = "This field cannot remain empty"

            numericId.trim() == "" -> idEditTextMutableLiveData.value = "This field cannot remain empty"

            else -> {

                val foundedUsers = repository.findUserById(numericId.toInt())

                when {
                    foundedUsers.isEmpty() -> {

                        val newUserEntity =
                            UserEntity(username, numericId.toInt())

                        repository.newUser(UserUI(newUserEntity.username, newUserEntity.user_id))


                        toastMutableLiveData.value = ViewModelDelivery("User Created Successfully . Log in again", "intent")



                    }
                    foundedUsers.isNotEmpty() -> {

                        Log.i("CreateAccountActivityViewModel", "foundedUsers is not empty")


                        usernameEditTextMutableLiveData.value = "UserId already exits"


                    }
                    else -> toastMutableLiveData.value = ViewModelDelivery("this is odd" , "")
                }


            }

        }
    }
}