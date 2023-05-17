package com.example.bottomnavigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomnavigation.databinding.CartBinding;

public class CartFragment extends Fragment {
    private CartBinding binding;
    private View root;

    // This function is called when this fragment's view is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CartBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        DataClass dataClass = DataClass.get_instance();
        dataClass.NumTokens.setValue(dataClass.NumTokens.getValue()+1);

        return root;
    }

    // This function is called when this fragment's view is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        root = null;
    }
}
