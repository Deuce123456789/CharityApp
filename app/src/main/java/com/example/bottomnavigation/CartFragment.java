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

// The fragment used to display the cart as well as the buy button.
public class CartFragment extends Fragment {
    private CartBinding binding;
    private View root;
    private DataClass dataClass;
    public CartAdapter cartAdapter;
    private ArrayList<HashMap<String,?>> items;

    // This function is called when this fragment's view is first created
    // and it initializes everything that it needs to.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CartBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        dataClass = DataClass.get_instance();

        setupGrid();

        // Set up the buy button to add tokens to your balance, then clear the cart.
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

    // Repeats a given string a given number of times. Used to generate the stars for the rating.
    String repeat(String base, int reps) {
        return new String(new char[reps]).replace("\0",base);
    }

    // Generates a single product to be passed into the adapter and rendered.
    HashMap<String, Object> genProduct(int img, String name, double price, int rating, int id, int tokens) {
        String stars = repeat("★", rating) + repeat("☆", 5-rating);
        return new HashMap<String, Object>() {{
            // An entry for each view we want to populate with data
            put("img", img);
            put("name", name);
            put("price", String.format("$%.2f", price));
            put("stars", stars);
            put("id", id);
            put("tokens", "Tokens: "+ tokens);
        }};
    }

    // Generates a list of products given a list of items to base them on.
    ArrayList<HashMap<String,?>> genProducts(List<Item> itms) {
        return new ArrayList<HashMap<String,?>>() {{
            for (Item itm : itms) {
                add(genProduct(itm.image_id, itm.name, itm.price, itm.rating, itm.id, itm.tokens));
            }
        }};
    }

    // Initializes the GridView with its adapter and data.
    public void setupGrid() {
        GridView cart = root.findViewById(R.id.cart_grid);

        // one hashmap for each product to display
        items = genProducts(dataClass.cart);
        cartAdapter = new CartAdapter(
                root.getContext(),
                // A function for getting updated data
                ()->genProducts(dataClass.cart),
                // The initial data, pre-updates
                items,
                updateTokenText,
                R.layout.cart_item,
                new String[]{"img", "name", "price", "stars", "id", "tokens"},
                new int[]{R.id.product_image, R.id.product_name, R.id.product_price, R.id.product_rating, R.id.product_id, R.id.product_tokens}
        );
        cart.setAdapter(cartAdapter);
    }

    // Finds the number of tokens you should gain, given the current cart.
    int calcTokenGain() {
        int newTokens = 0;
        for (Item i : dataClass.cart) newTokens += i.tokens;
        return newTokens;
    }

    // Updates the adapter to reflect changes in the cart's data
    void onCartUpdate() {
        cartAdapter.updateData();
    }

    // Updates the token text (as a Runnable so it can be passed into the CartAdapter constructor.
    Runnable updateTokenText = ()->
        ((TextView)root.findViewById(R.id.tokens_gained))
            .setText("+"+calcTokenGain()+" Tokens");
}