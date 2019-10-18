package com.example.arc_exapmle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.arc_exapmle.AddNoteActivity.EXTRA_NOTE
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonAddNote: FloatingActionButton = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(View.OnClickListener {

            val intent: Intent = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(intent, 1)

        })



        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var adapter = NoteAdapter()

        recyclerView.adapter = adapter


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes().observe(
            this,
            Observer { t -> adapter.setNote(t) }

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
                adapter.getNoteAt(viewHolder.adapterPosition)?.let { noteViewModel.delete(it) }
                Toast.makeText(applicationContext , "Note deleted " , Toast.LENGTH_SHORT).show()

            }
        }).attachToRecyclerView(recyclerView)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val mNote = data?.getParcelableExtra<Note>(EXTRA_NOTE)

            if (mNote != null) {
                noteViewModel.insert(mNote)
                Toast.makeText(this , "NoteSaved" , Toast.LENGTH_SHORT).show()

            }else{
                Log.i("MainActivity" , "onActivityResult: mNote is null")
                Toast.makeText(baseContext , "mNote is null" , Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this , "All notes deleted" , Toast.LENGTH_SHORT).show()

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
