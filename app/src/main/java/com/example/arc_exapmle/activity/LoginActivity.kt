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
import com.example.arc_exapmle.StarDatabase
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.viewModel.LoginActivityViewModel
import com.example.arc_exapmle.factory.LoginActivityViewModelFactory

class LoginActivity : AppCompatActivity() {

    lateinit var idEditText: EditText

    lateinit var createAccountTextView: TextView


    lateinit var loginButton: Button


    private lateinit var loginActivityViewModel: LoginActivityViewModel


    companion object {
        const val loginValue = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginActivityViewModel = ViewModelProviders.of(
            this,
            LoginActivityViewModelFactory(UserRepository(StarDatabase.getInstance(this)))
        ).get(LoginActivityViewModel::class.java)


        idEditText = findViewById(R.id.idEditText)
        loginButton = findViewById(R.id.enterButton)
        createAccountTextView = findViewById(R.id.createAccountTextView)

        loginButton.setOnClickListener {

            val loginID = idEditText.text.toString()

            loginActivityViewModel.userEntry(loginID)


        }
        createAccountTextView.setOnClickListener {

            val createAccountIntent = Intent(this, CreateAccountActivity::class.java)

            startActivity(createAccountIntent)


        }

    }

    override fun onResume() {
        super.onResume()

        loginActivityViewModel.idEditTextMutableLiveData.observe(this, Observer {

            idEditText.error = it

        })


        loginActivityViewModel.onSuccessMutableLiveData.observe(this, Observer {

            val mainIntent = Intent(this, MainActivity::class.java)

            mainIntent.putExtra(loginValue, UserUI(it.username, it.userId.toInt()))

            startActivity(mainIntent)

        })

    }

    override fun onStop() {
        super.onStop()

        loginActivityViewModel.onSuccessMutableLiveData.removeObservers(this)

        loginActivityViewModel.idEditTextMutableLiveData.removeObservers(this)

    }

}
