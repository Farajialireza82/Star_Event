package com.example.arc_exapmle.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserUI (var username: String , var user_id:Int) : Parcelable