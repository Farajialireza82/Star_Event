package com.example.arc_exapmle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notes: List<Note>? = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        var itemView: View? = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return NoteHolder(itemView)
    }


    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        val currentNote: Note? = notes?.get(position)

        holder.textViewTitle.text = currentNote?.getTitle()

        holder.textViewDescription.text = currentNote?.getDescription()

        holder.textViewPriority.text = currentNote?.getPriority().toString()


    }

    override fun getItemCount(): Int {

        return notes!!.size

    }

    fun setNote(newNotes: List<Note>?) {

        notes = newNotes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int) : Note?{

        return notes?.get(position)

    }


    class NoteHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        internal var textViewTitle: TextView = itemView!!.findViewById(R.id.text_view_title)
        internal var textViewDescription: TextView =
            itemView!!.findViewById(R.id.text_view_description)
        internal var textViewPriority: TextView = itemView!!.findViewById(R.id.text_view_priority)


    }
}