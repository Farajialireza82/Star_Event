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

class LoginActivity : AppCompatActivity() {

    lateinit var idEditText: EditText
    lateinit var usernameEditText: EditText

    lateinit var loginButton: Button

    val database = ViewModelProviders.of(this).get(UserViewModel::class.java)


    companion object {
        const val loginValue = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        idEditText = findViewById(R.id.idEditText)
        usernameEditText = findViewById(R.id.nameEditText)
        loginButton = findViewById(R.id.enterButton)

        loginButton.setOnClickListener {

            val loginID = idEditText.text.toString()
            val username = usernameEditText.text.toString()

            if (loginID.trim() == "") {

                idEditText.error = "this field cannot remain empty"

            } else if (username.trim() == "") {

                usernameEditText.error = "this field can not remain empty"
            } else {

                val numericLoginId = loginID.toInt()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                val user = UserUI(username, numericLoginId)

                val allUsers = database.getAllNotes().value

                if (allUsers!!.contains(UserEntity(username, numericLoginId))) {

                    Log.i("LoginActivity", " user exists ")

                } else {

                    Log.i("LoginActivity", " user does not exist ")


                    database.insert(UserEntity(username, numericLoginId))


                }

                intent.putExtra(loginValue, user)

                startActivity(intent)


            }


        }

    }
}
