package com.ssi.entrega3.ui.busca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ssi.entrega3.R;
import com.ssi.entrega3.ui.home.HomeViewModel;

public class BuscaFragment extends Fragment {

    private BuscaViewModel buscaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscaViewModel =
                ViewModelProviders.of(this).get(BuscaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_busca, container, false);
        final TextView textView = root.findViewById(R.id.text_busca);
        buscaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}