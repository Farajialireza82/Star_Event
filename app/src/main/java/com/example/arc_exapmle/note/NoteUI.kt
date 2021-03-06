package com.example.arc_exapmle.note

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteUI(var id: Int, var title: String , var description: String , var priority: Int , var userID: Int) :
    Parcelable