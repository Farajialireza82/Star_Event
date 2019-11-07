package com.example.arc_exapmle.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.arc_exapmle.R
import com.example.arc_exapmle.note.NoteAdapter
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteUI
import com.example.arc_exapmle.note.NoteViewModel
import com.example.arc_exapmle.user.UserUI
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent: Intent = intent

        val user : UserUI = intent.getParcelableExtra(LoginActivity.loginValue)


        val buttonAddNote: FloatingActionButton = findViewById(R.id.button_add_note)
        buttonAddNote.setOnClickListener {

            val addIntent = Intent(this, AddNoteKtActivity::class.java)

            addIntent.putExtra(LoginActivity.loginValue , user)

            startActivityForResult(addIntent, 1)

        }



        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NoteAdapter()

        recyclerView.adapter = adapter


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes().observe(
            this,
            Observer { t: List<NoteEntity>? ->

                val noteUIList: MutableList<NoteUI> = ArrayList()



                for (i in t!!.indices) {

                    val noteEntity = t[i]

                    noteUIList.add(
                        NoteUI(
                            noteEntity.noteId,
                            noteEntity.title,
                            noteEntity.description,
                            noteEntity.priority,
                            user.user_id
                        )
                    )

                }

                adapter.setNote(noteUIList)


            }

        )


        ItemTouchHelper(object :
            SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: ViewHolder,
                direction: Int
            ) {
                adapter.getNoteAt(viewHolder.adapterPosition)?.let {

                    val noteEntity = NoteEntity(
                        it.title,
                        it.description,
                        it.priority,
                        it.userID
                        ,it.id
                    )

                    noteViewModel.delete(
                        noteEntity
                    )
                }
                Toast.makeText(applicationContext, "Note deleted ", Toast.LENGTH_SHORT).show()

            }
        }).attachToRecyclerView(recyclerView)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == 1 && resultCode == Activity.RESULT_OK -> // noteViewModel.insert(mNoteEntity)

                Toast.makeText(this, "NoteSaved", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
