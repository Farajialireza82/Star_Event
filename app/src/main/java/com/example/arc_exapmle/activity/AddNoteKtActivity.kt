package com.example.arc_exapmle.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.arc_exapmle.R
import com.example.arc_exapmle.factory.AddNoteKtActivityViewModelFactory
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.viewModel.AddNoteKtActivityViewModel


class AddNoteKtActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private var numberPickerPriority: NumberPicker? = null
    private lateinit var user: UserUI

    private lateinit var addNoteKtActivityViewModel: AddNoteKtActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val intent = intent

        user = intent.getParcelableExtra(LoginActivity.loginValue)

        editTextTitle = findViewById(R.id.edit_text_Title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)

        addNoteKtActivityViewModel = ViewModelProviders.of(
            this,
            AddNoteKtActivityViewModelFactory(NoteRepository(application, user.user_id))
        ).get(AddNoteKtActivityViewModel::class.java)

        numberPickerPriority!!.minValue = 1
        numberPickerPriority!!.maxValue = 10

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)

        title = "Add Note"


    }

    private fun saveNote() {


            val title = editTextTitle.text.toString()

            val description = editTextDescription.text.toString()

            val priority = numberPickerPriority!!.value

            addNoteKtActivityViewModel.addNote(title , description , priority)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater

        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        addNoteKtActivityViewModel.titleMutableLiveData.observe(this , Observer {

            editTextTitle.error = it

        })

        addNoteKtActivityViewModel.descriptionMutableLiveData.observe(this , Observer {

            editTextDescription.error = it

        })

        addNoteKtActivityViewModel.toastMutableLiveData.observe(this , Observer {

            Toast.makeText(this , it , Toast.LENGTH_SHORT).show()

        })

        addNoteKtActivityViewModel.onSuccessMutableLiveData.observe(this , Observer {

            if(it){
                finish()
            }

        })




    }

}