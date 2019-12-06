package com.example.arc_exapmle.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.ViewModelDelivery
import com.example.arc_exapmle.user.UserRepository
import java.lang.NumberFormatException

class LoginActivityViewModel : ViewModel() {

    private lateinit var repository: UserRepository

    private lateinit var mutableLiveData: MutableLiveData<ViewModelDelivery>

    fun setUserRepo(userRepo: UserRepository) {

        repository = userRepo

    }

    fun userEntry(loginID:String): LiveData<ViewModelDelivery> {


         var delivery:ViewModelDelivery

        if (loginID.trim() == "") {

            delivery = ViewModelDelivery("this field cannot remain empty" , "ID")


        } else {

            try {
                val numericLoginId = loginID.toInt()

                val users = repository.findUserById(numericLoginId)

                Log.i("LoginActivityViewModel:userEntry", users.size.toString())


                if(users.isEmpty()){

                    delivery = ViewModelDelivery("User not found" , "ID")

                }else{

                    val foundedUser = users[0]

                    delivery = ViewModelDelivery(foundedUser.username , foundedUser.user_id.toString())

                }

            }catch (e:NumberFormatException){

                delivery = ViewModelDelivery("Invalid Id" , "ID")

            }







        }

        mutableLiveData = MutableLiveData()


        mutableLiveData.value = delivery

        return mutableLiveData

    }



}