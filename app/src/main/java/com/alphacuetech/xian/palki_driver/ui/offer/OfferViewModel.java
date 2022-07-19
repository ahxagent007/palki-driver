package com.alphacuetech.xian.palki_driver.ui.offer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfferViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OfferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is OfferViewModel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}