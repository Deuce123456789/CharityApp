package com.example.bottomnavigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentFourBinding;

public class FragmentFour extends Fragment {

    private FragmentFourBinding binding;
    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFourBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        setupScreen();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        root = null;
    }

    void setupScreen() {
        // Since we're now in a Fragment and not an Activity, we can't just call findViewById()
        // directly. Instead, need to call root.findViewById() to to the connect XML views.
        TextView displayedText = root.findViewById(R.id.text_four);
        displayedText.setText("This is the fourth fragment.");

        Button button = root.findViewById(R.id.button_four);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
                Toast.makeText(context, "Button 4 tapped", Toast.LENGTH_SHORT).show();
            }
        });
    }
}