package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentTwoBinding;

public class FragmentTwo extends Fragment {

    private FragmentTwoBinding binding;
    private View root;

    // This function is called when this fragment's view is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTwoBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        setupScreen();
        return root;
    }

    // This function is called when this fragment's view is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        root = null;
    }

    void setupScreen() {
        // Since we're now in a Fragment and not an Activity, we can't just call findViewById()
        // directly. Instead, need to call root.findViewById() to to the connect XML views.
        TextView displayedText = root.findViewById(R.id.text_two);
        displayedText.setText("This is the second fragment.");
    }
}