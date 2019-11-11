package com.example.arc_exapmle.note;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NoteViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int id;


    public NoteViewModelFactory(Application application, int mId) {
        mApplication = application;
        id = mId;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NoteViewModel(mApplication, id);
    }
}