package com.example.bottomnavigation;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.CartBinding;
import com.example.bottomnavigation.databinding.DonatepopupBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// The fragment used to display the cart as well as the buy button.
public class PopupFragment extends Fragment {
    private DonatepopupBinding binding;
    private View root;
    private DataClass dataClass;
    private String name;

    // This function is called when this fragment's view is first created
    // and it initializes everything that it needs to.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DonatepopupBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        dataClass = DataClass.get_instance();

        View popupcontainer = container.getRootView().findViewById(R.id.donate_page);


        Button donate_btn = root.findViewById(R.id.donate_btn);
        Button cancel_btn = root.findViewById(R.id.cancel_btn);
        EditText donation_inp = root.findViewById(R.id.donation_inp);



        donate_btn.setOnClickListener(v -> {

            String tokenstr = donation_inp.getText().toString();
            if (tokenstr.isEmpty()) return;
            int spentTokens = Integer.parseInt(tokenstr);
            if (dataClass.NumTokens.getValue() >= spentTokens) {
                dataClass.NumTokens.setValue(dataClass.NumTokens.getValue() - spentTokens);
                popupcontainer.setVisibility(View.GONE);
                hideKeyboardFrom(getContext(), getView());
                Toast.makeText(root.getContext(), "You just donated to '"+name+"'", Toast.LENGTH_LONG).show();
            }

        });

        cancel_btn.setOnClickListener(v -> {
            popupcontainer.setVisibility(View.GONE);
        });

        return root;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    // This function is called when this fragment's view is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        root = null;
    }

    public void setCharity(Charity charity) {
        TextView cname = root.findViewById(R.id.popup_charity_name);
        cname.setText(charity.name);
        name = charity.name;
    }
}