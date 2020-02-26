package com.example.arc_exapmle.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arc_exapmle.R

class NoteAdapter() :
    ListAdapter<NoteUI, NoteAdapter.NoteHolder>(DIFF_CALLBACK) {


companion object {
    val DIFF_CALLBACK: DiffUtil.ItemCallback<NoteUI> =
        object : DiffUtil.ItemCallback<NoteUI>() {
            override fun areItemsTheSame(oldItem: NoteUI, newItem: NoteUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteUI, newItem: NoteUI): Boolean {
                return oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.priority == newItem.priority
            }
        }
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        val itemView: View? = LayoutInflater.from(parent.context)
            .inflate(R.layout.updated_viewholder, parent, false)

        return NoteHolder(itemView)
    }


    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        val currentNote: NoteUI? = getItem(position)

        holder.textViewTitle.text = currentNote?.title

        holder.textViewDescription.text = currentNote?.description

        holder.textViewPriority.text = currentNote?.priority.toString()


    }


    fun getNoteAt(position: Int) : NoteUI?{

        return getItem(position)

    }


    class NoteHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        internal var textViewTitle: TextView = itemView!!.findViewById(R.id.text_view_title)
        internal var textViewDescription: TextView =
            itemView!!.findViewById(R.id.text_view_description)
        internal var textViewPriority: TextView = itemView!!.findViewById(R.id.text_view_priority)


    }
}