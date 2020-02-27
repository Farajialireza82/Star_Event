package com.example.arc_exapmle.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
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
    private lateinit var createButton: RelativeLayout
    private lateinit var create_button_card_view: CardView

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
        createButton = findViewById(R.id.createAccountButtonFinal)
        create_button_card_view = findViewById(R.id.login_button_card_view)



        createButton.setOnClickListener {


            createAccountActivityViewModel.createNewAccount(
                usernameEditText.text.toString(), numericIdEditText.text.toString()
            )
        }


    }

    override fun onResume() {
        super.onResume()

        inputChange()

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

    @SuppressLint("ResourceType")
    private fun loginButtonStyle() {
        if (numericIdEditText.text.isNotEmpty() && usernameEditText.text.isNotEmpty()) {
            if (!createButton.isFocusable) {
                createButton.isFocusable = true
                createButton.isClickable = true
                create_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)))
                val outValue = TypedValue()
                theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
                createButton.setBackgroundResource(outValue.resourceId)
            }
        } else {
            if (createButton.isFocusable) {
                createButton.isFocusable = false
                createButton.isClickable = false
                create_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)))
                createButton.setBackgroundResource(0)
            }
        }
    }


    private fun inputChange() {
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                loginButtonStyle()
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        numericIdEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                loginButtonStyle()
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }


}
