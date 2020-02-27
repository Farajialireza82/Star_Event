package com.example.arc_exapmle.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.example.arc_exapmle.R

class HomeActivity : AppCompatActivity() {
    lateinit var login_button: RelativeLayout
    lateinit var register_text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setType()
        loginOnClick()
        registerOnClick()
    }

    private fun setType() {
        login_button = findViewById(R.id.login_button)
        register_text_view = findViewById(R.id.register_text_view)
    }

    private fun registerOnClick() {
        register_text_view.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CreateAccountActivity::class.java
                )
            )
        }
    }

    private fun loginOnClick() {
        login_button.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity2nd::class.java
                )
            )
        }
    }
}
