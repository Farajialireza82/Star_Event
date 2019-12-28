package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.arc_exapmle.R
import com.example.arc_exapmle.StarDatabase
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.viewModel.CreateAccountActivityViewModel
import com.example.arc_exapmle.factory.CreateAccountActivityViewModelFactory

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var numericIdEditText: EditText
    private lateinit var createButton: Button

    private lateinit var createAccountActivityViewModel: CreateAccountActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val userRepository = UserRepository(StarDatabase.getInstance(this).userDao())

        createAccountActivityViewModel =
            ViewModelProviders.of(
                this,
                CreateAccountActivityViewModelFactory(userRepository)
            ).get(CreateAccountActivityViewModel::class.java)


        usernameEditText = findViewById(R.id.createNameEditText)
        numericIdEditText = findViewById(R.id.createIdEditText)
        createButton = findViewById(R.id.createButton)



        createButton.setOnClickListener {


            createAccountActivityViewModel.createNewAccount(
                usernameEditText.text.toString(), numericIdEditText.text.toString()
            )
        }


    }

    override fun onResume() {
        super.onResume()

        createAccountActivityViewModel.usernameEditTextMutableLiveData.observe(this, Observer {

            usernameEditText.error = it

        })

        createAccountActivityViewModel.idEditTextMutableLiveData.observe(this, Observer {

            numericIdEditText.error = it

        })

        createAccountActivityViewModel.toastMutableLiveData.observe(this, Observer {

            Toast.makeText(
                this,
                it.username,
                Toast.LENGTH_LONG
            ).show()

            if (it.userId == "intent") {


                finish()
            }
        })


    }


}
