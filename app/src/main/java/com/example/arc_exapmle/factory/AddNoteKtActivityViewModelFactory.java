package com.example.arc_exapmle.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arc_exapmle.note.NoteRepository;
import com.example.arc_exapmle.viewModel.AddNoteKtActivityViewModel;
import com.example.arc_exapmle.viewModel.MainActivityViewModel;

public class AddNoteKtActivityViewModelFactory implements ViewModelProvider.Factory {
    private NoteRepository repository;


    public AddNoteKtActivityViewModelFactory(NoteRepository noteRepository) {
        repository = noteRepository;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AddNoteKtActivityViewModel(repository);
    }
}