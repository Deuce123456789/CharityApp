package com.example.bottomnavigation;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.MainStoreItemBinding;

import java.util.stream.Collectors;

public class StoreItem extends Fragment {
    private MainStoreItemBinding binding;
    private View root;
    private DataClass dataClass;

    // This function is called when this fragment's view is first created
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = MainStoreItemBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        // creation logic here
        dataClass = DataClass.get_instance();
        Button buy_button = root.findViewById(R.id.product_buy_button);
        String string_id = (String) ((TextView)root.findViewById(R.id.product_id)).getText();
        int id = Integer.getInteger(string_id);
        buy_button.setOnClickListener((v)->{
            System.out.println("memes");
            // compiler thing that makes the IDE stop yelling at me about SDK versions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // We assume that the store doesn't have more than one item
                // with the same name, but in these test cases that should be fine.
                // I don't really know how I would do this otherwise.
                Item itm = dataClass.StoreItems.stream()
                        .filter((Item i)->i.id == id)
                        .collect(Collectors.toList())
                        .get(0);
                dataClass.cart.add(itm);
                System.out.println(dataClass.cart);
            }
        });

        return root;
    }

    // This function is called when this fragment's view is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        root = null;
    }
}
