package com.example.bottomnavigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomnavigation.databinding.CartBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartFragment extends Fragment {
    private CartBinding binding;
    private View root;
    private DataClass dataClass;
    public CartAdapter cartAdapter;
    private ArrayList<HashMap<String,?>> items;

    // This function is called when this fragment's view is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CartBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        dataClass = DataClass.get_instance();

        setupGrid();

        Button buy_button = root.findViewById(R.id.buy_button);
        buy_button.setOnClickListener(v -> {
            dataClass.NumTokens.setValue(dataClass.NumTokens.getValue()+calcTokenGain());
            dataClass.cart.clear();
            onCartUpdate();
        });

        return root;
    }

    // This function is called when this fragment's view is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        root = null;
    }

    String repeat(String base, int reps) {
        return new String(new char[reps]).replace("\0",base);
    }

    HashMap<String, Object> genProduct(int img, String name, double price, int rating, int id, int tokens) {
        String stars = repeat("★", rating) + repeat("☆", 5-rating);
        return new HashMap<String, Object>() {{
            put("img", img);
            put("name", name);
            put("price", String.format("$%.2f", price));
            put("stars", stars);
            put("id", id);
            put("tokens", "Tokens: "+ tokens);
        }};
    }

    ArrayList<HashMap<String,?>> genProducts(List<Item> itms) {
        return new ArrayList<HashMap<String,?>>() {{
            for (Item itm : itms) {
                add(genProduct(R.drawable.ic_star_1, itm.name, itm.price, itm.rating, itm.id, itm.tokens));
            }
        }};
    }

    public void setupGrid() {
        GridView cart = root.findViewById(R.id.cart_grid);

        // one hashmap for each product to display
        items = genProducts(dataClass.cart);
        cartAdapter = new CartAdapter(
                root.getContext(),
                //method of getting new data, then the initial data.
                ()->genProducts(dataClass.cart), items,
                updateTokenText,
                R.layout.cart_item,
                new String[]{"img", "name", "price", "stars", "id", "tokens"},
                new int[]{R.id.product_image, R.id.product_name, R.id.product_price, R.id.product_rating, R.id.product_id, R.id.product_tokens}
        );
        cart.setAdapter(cartAdapter);
    }

    int calcTokenGain() {
        int newTokens = 0;
        for (Item i : dataClass.cart) newTokens += i.tokens;
        return newTokens;
    }

    void onCartUpdate() {
        cartAdapter.updateData();
        updateTokenText.run();
    }

    Runnable updateTokenText = ()->
        ((TextView)root.findViewById(R.id.tokens_gained))
            .setText("+"+calcTokenGain()+" Tokens");

}
