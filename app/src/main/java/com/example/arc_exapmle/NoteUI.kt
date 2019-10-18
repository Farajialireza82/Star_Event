package com.example.arc_exapmle

class NoteUI(noteEntity: NoteEntity?) {
    internal var title: String? = null
    internal var description: String? = null
    internal var priority: Int? = null

    init {
        title = noteEntity?.title
        description = noteEntity?.description
        priority = noteEntity?.priority
    }



}