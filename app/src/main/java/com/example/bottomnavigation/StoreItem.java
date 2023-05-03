package com.example.bottomnavigation;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.MainStoreItemBinding;

import java.util.Optional;
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
        String name = (String) ((TextView)root.findViewById(R.id.product_name)).getText();
        buy_button.setOnClickListener((v)->{
                // compiler thing that makes the IDE stop yelling at me about SDK versions
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    // We assume that the store doesn't have more than one item
                    // with the same name, but in these test cases that should be fine.
                    // I don't really know how I would do this otherwise.
                    Item itm = dataClass.StoreItems.stream()
                            .filter((Item i)->i.name.equals(name))
                            .collect(Collectors.toList())
                            .get(0);
                    dataClass.cart.add(itm);
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
