package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.arc_exapmle.R
import com.example.arc_exapmle.user.UserEntity
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.user.UserViewModel
import com.example.arc_exapmle.viewModel.CreateAccountActivityViewModel
import java.util.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var numericIdEditText: EditText
    private lateinit var createButton: Button

    private lateinit var createAccountActivityViewModel: CreateAccountActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAccountActivityViewModel =
            ViewModelProviders.of(this).get(CreateAccountActivityViewModel::class.java)


        usernameEditText = findViewById(R.id.createNameEditText)
        numericIdEditText = findViewById(R.id.createIdEditText)
        createButton = findViewById(R.id.createButton)



        createButton.setOnClickListener {

            /*val userName = usernameEditText.text.toString()

            val numericId = numericIdEditText.text.toString().toInt()*/

            val newUserResult = createAccountActivityViewModel.createNewAccount(
                usernameEditText.text,
                numericIdEditText.text
            )

            when (newUserResult) {
                0 -> usernameEditText.error = "This field cannot remain empty"
                1 -> numericIdEditText.error = "This field cannot remain empty"
                2 -> {

                    val mainIntent = Intent(this, LoginActivity::class.java)

                    Toast.makeText(this, "User Created Successfully . Log in again", Toast.LENGTH_LONG).show()


                    startActivity(mainIntent)

                }
                3 -> numericIdEditText.error = "UserId Already exits"
                4 -> Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
            }


        }


    }

}
