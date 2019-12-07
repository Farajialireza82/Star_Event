package com.example.arc_exapmle.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.note.NoteUI

class MainActivityViewModel : ViewModel() {

    val notesMutableLiveData :MutableLiveData<List<NoteUI>> = MutableLiveData()


}