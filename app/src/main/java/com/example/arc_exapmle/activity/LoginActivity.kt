package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.arc_exapmle.R
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.viewModel.LoginActivityViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var idEditText: EditText

    lateinit var createAccountTextView: TextView


    lateinit var loginButton: Button

    private lateinit var userRepository: UserRepository

    private lateinit var loginActivityViewModel: LoginActivityViewModel



    companion object {
        const val loginValue = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userRepository = UserRepository(application)

        loginActivityViewModel = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)

        loginActivityViewModel.setUserRepo(userRepository)

        idEditText = findViewById(R.id.idEditText)
        loginButton = findViewById(R.id.enterButton)
        createAccountTextView = findViewById(R.id.createAccountTextView)

        loginButton.setOnClickListener {

            val loginID = idEditText.text.toString()

            loginActivityViewModel.userEntry(loginID).observe(this , Observer {

                if(it.errorTag == "ID"){

                    idEditText.error = it.errorText

                }else{

                    val foundedUserUI = UserUI(it.errorText , it.errorTag.toInt())

                    val mainIntent = Intent(this , MainActivity::class.java)

                    mainIntent.putExtra(loginValue , foundedUserUI)

                    startActivity(mainIntent)

                    finish()



                }

            })

        }
        createAccountTextView.setOnClickListener {

            val createAccountIntent = Intent(this@LoginActivity, CreateAccountActivity::class.java)

            startActivity(createAccountIntent)

            finish()


        }

    }
}
