package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.arc_exapmle.R
import com.example.arc_exapmle.user.UserEntity
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.user.UserViewModel
import java.util.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var numericIdEditText: EditText
    private lateinit var createButton: Button

    private lateinit var database: UserViewModel

    private lateinit var allUsers: List<UserEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        database = ViewModelProviders.of(this).get(UserViewModel::class.java)


        usernameEditText = findViewById(R.id.createNameEditText)
        numericIdEditText = findViewById(R.id.createIdEditText)
        createButton = findViewById(R.id.createButton)



        createButton.setOnClickListener {

            val userName: String = usernameEditText.text.toString()

            val numericId: Int = numericIdEditText.text.toString().toInt()


            if (usernameEditText.text.trim() == "") {

                usernameEditText.error = "This field cannot remain empty"

            } else if (numericIdEditText.text.trim() == "") {

                usernameEditText.error = "This field cannot remain empty"

            } else {

               val foundedUsers = database.findUserById(numericId)

                if(foundedUsers.isEmpty()){

                    val newUserEntity = UserEntity(userName , numericId)

                    database.insert(newUserEntity)



                    val mainIntent = Intent(this , MainActivity::class.java)

                    mainIntent.putExtra(LoginActivity.loginValue , UserUI(newUserEntity.username , newUserEntity.user_id))

                    startActivity(mainIntent)


                }else if(foundedUsers.isNotEmpty()){

                    Log.i("CreateAccountActivity:onCreate()" , "foundedUsers is not empty")

                    numericIdEditText.error = "UserId Already exits"

                }


            }

        }


    }

}
