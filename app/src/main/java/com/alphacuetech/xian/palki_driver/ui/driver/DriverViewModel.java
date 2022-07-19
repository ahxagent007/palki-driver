package com.alphacuetech.xian.palki_driver.ui.driver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriverViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DriverViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is DriverViewModel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}