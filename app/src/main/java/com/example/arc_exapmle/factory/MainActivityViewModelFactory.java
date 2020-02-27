package com.example.arc_exapmle.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arc_exapmle.StarDatabase;
import com.example.arc_exapmle.note.NoteRepository;
import com.example.arc_exapmle.user.UserRepository;
import com.example.arc_exapmle.viewModel.MainActivityViewModel;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    private NoteRepository repository;


    public MainActivityViewModelFactory(NoteRepository noteRepository) {
        repository = noteRepository;

    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainActivityViewModel(repository);
    }
}