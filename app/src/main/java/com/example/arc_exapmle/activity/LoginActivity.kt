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
import com.example.arc_exapmle.factory.LoginActivityViewModelFactory

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

        loginActivityViewModel = ViewModelProviders.of(
            this,
            LoginActivityViewModelFactory(userRepository)
        ).get(LoginActivityViewModel::class.java)


        idEditText = findViewById(R.id.idEditText)
        loginButton = findViewById(R.id.enterButton)
        createAccountTextView = findViewById(R.id.createAccountTextView)

        loginButton.setOnClickListener {

            val loginID = idEditText.text.toString()

            loginActivityViewModel.userEntry(loginID)


        }
        createAccountTextView.setOnClickListener {

            val createAccountIntent = Intent(this@LoginActivity, CreateAccountActivity::class.java)

            startActivity(createAccountIntent)

            finish()


        }

    }

    override fun onResume() {
        super.onResume()

        loginActivityViewModel.idEditTextMutableLiveData.observe(this, Observer {

            idEditText.error = it

        })

        loginActivityViewModel.onSuccessMutableLiveData.observe(this, Observer {

            val mainIntent = Intent(this, MainActivity::class.java)

            mainIntent.putExtra(loginValue, UserUI(it.errorText, it.errorTag.toInt()))

            startActivity(mainIntent)

        })


    }

}
