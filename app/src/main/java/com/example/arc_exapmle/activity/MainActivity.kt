package com.example.arc_exapmle.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
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
import com.example.arc_exapmle.factory.MainActivityViewModelFactory
import com.example.arc_exapmle.note.*
import com.example.arc_exapmle.user.UserUI
import com.example.arc_exapmle.viewModel.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter

    private lateinit var welcomeTextView: TextView
    private lateinit var buttonAddNote: FloatingActionButton

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var user: UserUI


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeTextView = findViewById(R.id.welcomeTextView)
        recyclerView = findViewById(R.id.recycler_view)
        buttonAddNote = findViewById(R.id.button_add_note)

        adapter = NoteAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



        user = intent.getParcelableExtra(LoginActivity.loginValue)

        val noteRepository = NoteRepository(application, user.user_id)

        mainActivityViewModel =
            ViewModelProviders.of(this, MainActivityViewModelFactory(noteRepository))
                .get(MainActivityViewModel::class.java)





        welcomeTextView.text = "Welcome ${user.username}"

        buttonAddNote.setOnClickListener {

            val addIntent = Intent(this, AddNoteKtActivity::class.java)

            addIntent.putExtra(LoginActivity.loginValue, user)

            startActivity(addIntent)

        }




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


                    mainActivityViewModel.deleteNote(it)


                }

            }
        }).attachToRecyclerView(recyclerView)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.delete_all_notes -> {

                CoroutineScope(Default).launch {

                    mainActivityViewModel.deleteAllNotes()

                }


            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()


        mainActivityViewModel.toastMutableLiveData.observe(this, Observer {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


        })

        mainActivityViewModel.getAllNotes().observe(this, Observer {

            val noteUIList: MutableList<NoteUI> = ArrayList()



            for (i in it!!.indices) {

                val noteEntity = it[i]

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


        })


    }

    override fun onBackPressed() {
        super.onBackPressed()

        Toast.makeText(this, "To go back , press the back button twice", Toast.LENGTH_SHORT).show()

    }


}
