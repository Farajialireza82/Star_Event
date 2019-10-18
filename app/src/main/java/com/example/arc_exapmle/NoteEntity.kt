package com.example.arc_exapmle

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class NoteEntity(
     var title: String?,
     var description: String?,
     var priority: Int?
) :  Parcelable {

    @PrimaryKey(autoGenerate = true)
    private var id = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
        id = parcel.readInt()
    }

    fun setId(id : Int){
        this.id = id
    }


    fun getId(): Int {
        return id
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeValue(priority)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteEntity> {
        override fun createFromParcel(parcel: Parcel): NoteEntity {
            return NoteEntity(parcel)
        }

        override fun newArray(size: Int): Array<NoteEntity?> {
            return arrayOfNulls(size)
        }
    }


}