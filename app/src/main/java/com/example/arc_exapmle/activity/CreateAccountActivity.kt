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
import com.example.arc_exapmle.ViewModelDelivery
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.viewModel.CreateAccountActivityViewModel

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var numericIdEditText: EditText
    private lateinit var createButton: Button

    private lateinit var createAccountActivityViewModel: CreateAccountActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val repository = UserRepository(application)

        createAccountActivityViewModel =
            ViewModelProviders.of(this).get(CreateAccountActivityViewModel::class.java)

        createAccountActivityViewModel.setUserRepo(repository)

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

        createAccountActivityViewModel.usernameEditTextMutableLiveData.observe(this , Observer {

            usernameEditText.error = it

        })

        createAccountActivityViewModel.idEditTextMutableLiveData.observe(this , Observer {

            numericIdEditText.error = it

        })

        createAccountActivityViewModel.toastMutableLiveData.observe(this , Observer {

            Toast.makeText(
                this,
                it.errorText,
                Toast.LENGTH_LONG
            ).show()

            if(it.errorTag == "intent") {

                val mainIntent = Intent(this, LoginActivity::class.java)

                startActivity(mainIntent)

                finish()
            }
        })


    }


}
