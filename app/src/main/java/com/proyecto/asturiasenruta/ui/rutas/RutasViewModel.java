package com.proyecto.asturiasenruta.ui.rutas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RutasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RutasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }
}