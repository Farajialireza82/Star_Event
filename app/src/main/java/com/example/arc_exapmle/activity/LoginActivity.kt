package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.arc_exapmle.R
import com.example.arc_exapmle.user.UserUI

class LoginActivity : AppCompatActivity() {

    lateinit var idEditText: EditText
    lateinit var loginButton: Button



    companion object {
        const val loginValue = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        idEditText = findViewById(R.id.idEditText)
        loginButton = findViewById(R.id.enterButton)

        loginButton.setOnClickListener {

            val loginID = idEditText.text.toString()

            if (loginID.trim() == "") {

                idEditText.error = "this field cannot remain empty"

            } else {

                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                val user = UserUI(loginID)

                intent.putExtra(loginValue, user)

                startActivity(intent)


            }


        }

    }
}
