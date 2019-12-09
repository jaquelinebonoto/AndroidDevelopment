package com.ssi.entrega3.ui.pedidos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PedidosViewModel {

    private MutableLiveData<String> mText;

    public PedidosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is pedidos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
