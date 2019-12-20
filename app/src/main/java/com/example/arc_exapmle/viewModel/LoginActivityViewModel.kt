package com.example.arc_exapmle.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.user.UserRepository
import java.lang.NumberFormatException

class LoginActivityViewModel(userRepository: UserRepository) : ViewModel() {

    private var repository: UserRepository = userRepository

    val idEditTextMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val onSuccessMutableLiveData: SingleLiveEvent<ViewModelDelivery> = SingleLiveEvent()


    fun userEntry(loginID: String) {

        if (loginID.trim() == "") {

            idEditTextMutableLiveData.value = "This field cannot remain empty"

        } else {

            try {
                val numericLoginId = loginID.toInt()

                val users = repository.findUserById(numericLoginId)

                Log.i(" LoginActivityViewModel : userEntry() ", users.size.toString())


                if (users.isEmpty()) {

                    idEditTextMutableLiveData.value = "User Not Found"

                } else {

                    val foundedUser = users[0]

                    val delivery = ViewModelDelivery(
                        foundedUser.username,
                        foundedUser.user_id.toString()
                    )
                    delivery.condition = true

                    onSuccessMutableLiveData.value =
                        delivery

                }

            } catch (e: NumberFormatException) {

                idEditTextMutableLiveData.value = "Invalid Input"

            }


        }

    }




}