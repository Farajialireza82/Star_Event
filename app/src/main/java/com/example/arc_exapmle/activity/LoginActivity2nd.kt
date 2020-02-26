package com.example.arc_exapmle.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.arc_exapmle.R
import com.example.arc_exapmle.StarDatabase
import com.example.arc_exapmle.factory.LoginActivityViewModelFactory
import com.example.arc_exapmle.user.UserRepository
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.viewModel.LoginActivityViewModel
import com.example.arc_exapmle.viewModel.ViewModelDelivery

class LoginActivity2nd : AppCompatActivity() {
    lateinit var idEditText: EditText
    lateinit var login_button: RelativeLayout
    lateinit var login_button_card_view: CardView
    lateinit var loginActivityViewModel: LoginActivityViewModel

    companion object {
        const val loginValue = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_two)

        loginActivityViewModel = ViewModelProviders.of(
            this,
            LoginActivityViewModelFactory(UserRepository(StarDatabase.getInstance(this).userDao()))
        )
            .get(LoginActivityViewModel::class.java)

        setType()
        loginOnClick()
        inputChange()
    }

    private fun setType() {
        idEditText = findViewById(R.id.email)
        login_button = findViewById(R.id.login_button)
        login_button_card_view = findViewById(R.id.login_button_card_view)
    }

    @SuppressLint("ResourceType")
    private fun loginButtonStyle() {
        if (idEditText.text.isNotEmpty()) {
            if (!login_button.isFocusable) {
                login_button.isFocusable = true
                login_button.isClickable = true
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)))
                val outValue = TypedValue()
                theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
                login_button.setBackgroundResource(outValue.resourceId)
            }
        } else {
            if (login_button.isFocusable) {
                login_button.isFocusable = false
                login_button.isClickable = false
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)))
                login_button.setBackgroundResource(0)
            }
        }
    }

    private fun loginOnClick() {
        login_button.setOnClickListener { view ->
            if (idEditText.text.isNotEmpty()) {

                loginActivityViewModel.userEntry(idEditText.text.toString())

            }
        }
    }

    private fun inputChange() {
        idEditText.addTextChangedListener(object : TextWatcher {
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

    override fun onResume() {
        super.onResume()

        loginActivityViewModel.onSuccessMutableLiveData.observe(this , Observer {

            val mainIntent = Intent(this, MainActivity::class.java)

            mainIntent.putExtra(
                loginValue,
                UserUI(it.username, Integer.parseInt(it.userId))
            )

            startActivity(mainIntent)

        })

        loginActivityViewModel.idEditTextMutableLiveData.observe(this , Observer {

            idEditText.error = it


        })


    }

}
