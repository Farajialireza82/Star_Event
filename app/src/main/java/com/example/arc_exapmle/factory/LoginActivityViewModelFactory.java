package com.example.arc_exapmle.factory;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arc_exapmle.StarDatabase;
import com.example.arc_exapmle.user.UserRepository;
import com.example.arc_exapmle.viewModel.CreateAccountActivityViewModel;
import com.example.arc_exapmle.viewModel.LoginActivityViewModel;

public class LoginActivityViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository repository;


    public LoginActivityViewModelFactory(UserRepository userRepository) {
        repository = userRepository;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new LoginActivityViewModel(repository);
    }
}