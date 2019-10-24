package com.example.arc_exapmle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity


class AddNoteKtActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_NOTE = "com.example.arc_exapmle.EXTRA_NOTE"


    }

    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var numberPickerPriority: NumberPicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.edit_text_Title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)

        numberPickerPriority!!.minValue = 1
        numberPickerPriority!!.maxValue = 10

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)

        title = "Add Note"


    }

    private fun saveNote() {
        val title = editTextTitle!!.text.toString()

        val description = editTextDescription!!.text.toString()

        val priority = numberPickerPriority!!.value

        if (title.trim().isEmpty()) {

            editTextTitle!!.error = "This field can not remain empty"
            return

        } else if (description.trim().isEmpty()) {

            editTextDescription!!.error = "This field can not remain empty"
            return

        }

        val note = NoteEntity(title, description, priority)


        val data = Intent()

        data.putExtra(EXTRA_NOTE, NoteUI(note.getId(), note.title, note.description, note.priority))
        setResult(Activity.RESULT_OK, data)
        finish()
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

}