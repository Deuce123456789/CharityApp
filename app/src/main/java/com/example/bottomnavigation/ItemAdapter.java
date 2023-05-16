package com.example.bottomnavigation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.MainStoreItemBinding;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemAdapter extends SimpleAdapter {
    static DataClass dataClass = DataClass.get_instance();

    public ItemAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = super.getView(position, convertView, parent);

        // Find the button and the ID
        Button buy_button = root.findViewById(R.id.product_buy_button);
        TextView id_text = root.findViewById(R.id.product_id);
        String string_id = (String) id_text.getText();
        int id = Integer.valueOf(string_id);

        // Find the item that this view refers to
        Item itm;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itm = dataClass.StoreItems.stream()
                .filter((Item i) -> i.id == id)
                .collect(Collectors.toList())
                .get(0);
        } else itm = null;

        // Set a listener that adds the item to the cart and says so
        buy_button.setOnClickListener((v)->{
            dataClass.cart.add(itm);
            Toast.makeText(root.getContext(), "Item '"+itm.name+"' added to cart.", Toast.LENGTH_LONG).show();
        });

        // Required for parity with base SimpleAdapter
        return root;
    }
}
