package com.example.arc_exapmle.factory;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arc_exapmle.user.UserRepository;
import com.example.arc_exapmle.viewModel.CreateAccountActivityViewModel;

public class CreateAccountActivityViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository repository;


    public CreateAccountActivityViewModelFactory(UserRepository userRepository) {
        repository = userRepository;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CreateAccountActivityViewModel(repository);
    }
}