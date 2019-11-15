package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.arc_exapmle.R
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.user.UserViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var idEditText: EditText

    lateinit var createAccountTextView: TextView


    lateinit var loginButton: Button

    private lateinit var database: UserViewModel


    companion object {
        const val loginValue = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = ViewModelProviders.of(this).get(UserViewModel::class.java)

        idEditText = findViewById(R.id.idEditText)
        loginButton = findViewById(R.id.enterButton)
        createAccountTextView = findViewById(R.id.createAccountTextView)

        loginButton.setOnClickListener {

            val loginID = idEditText.text.toString()

            if (loginID.trim() == "") {

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

                   mainIntent.putExtra(loginValue , foundedUserUI)

                   startActivity(mainIntent)

               }


            }


        }
        createAccountTextView.setOnClickListener {

            val createAccountIntent = Intent(this@LoginActivity, CreateAccountActivity::class.java)

            startActivity(createAccountIntent)


        }

    }
}
