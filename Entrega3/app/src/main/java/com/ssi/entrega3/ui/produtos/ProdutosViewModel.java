package com.ssi.entrega3.ui.produtos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ProdutosViewModel {

    private MutableLiveData<String> mText;

    public ProdutosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is produtos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
