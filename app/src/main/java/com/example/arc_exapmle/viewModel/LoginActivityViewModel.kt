package com.example.arc_exapmle.viewModel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.ViewModelDelivery
import com.example.arc_exapmle.activity.LoginActivity
import com.example.arc_exapmle.activity.MainActivity
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.user.UserUI

class LoginActivityViewModel : ViewModel() {

    private lateinit var repository: UserRepository

    private lateinit var mutableLiveData: MutableLiveData<ViewModelDelivery>

    fun setUserRepo(userRepo: UserRepository) {

        repository = userRepo

    }

    fun userEntery(loginID:String): MutableLiveData<ViewModelDelivery>{

        var delivery:ViewModelDelivery

        if (loginID.trim() == "") {

            delivery = ViewModelDelivery("this field cannot remain empty" , "ID")

            idEditText.error = "this field cannot remain empty"

        } else {

            val numericLoginId = loginID.toInt()

            val users = database.findUserById(numericLoginId)

            Log.i("LoginActivity:OnCreate", users.size.toString())


            if(users.isEmpty()){
                idEditText.error = "User not found"
            }else{

                val foundedUser = users[0]

                val foundedUserUI = UserUI(foundedUser.username , foundedUser.user_id)

                val mainIntent = Intent(this , MainActivity::class.java)

                mainIntent.putExtra(LoginActivity.loginValue, foundedUserUI)

                startActivity(mainIntent)

            }


        }

    }



}