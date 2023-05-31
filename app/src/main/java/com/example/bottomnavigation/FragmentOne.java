package com.example.bottomnavigation;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentOneBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FragmentOne extends Fragment {
    private FragmentOneBinding binding;
    private View root;
    private DataClass dataClass;
    private ItemAdapter itemAdapter;
    private ArrayList<HashMap<String,?>> items;

    // This function is called when this fragment's view is first created
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOneBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        dataClass = DataClass.get_instance();

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


    // Repeats a given string a given number of times. Used to generate the stars for the rating.
    String repeat(String base, int reps) {
        return new String(new char[reps]).replace("\0",base);
    }

    // Generates a single product to be passed into the adapter and rendered.
    HashMap<String, Object> genProduct(int img, String name, double price, int rating, int id) {
        String stars = repeat("★", rating) + repeat("☆", 5-rating);
        return new HashMap<String, Object>() {{
            put("img", img);
            put("name", name);
            put("price", String.format("$%.2f", price));
            put("stars", stars);
            put("id", id);
        }};
    }

    // Generates a list of products given a list of items to base them on.
    ArrayList<HashMap<String,?>> genProducts(List<Item> itms) {
        return new ArrayList<HashMap<String,?>>() {{
            for (Item itm : itms) {
                add(genProduct(R.drawable.ic_star_1, itm.name, itm.price, itm.rating, itm.id));
            }
        }};
    }

    // Sets the list of currently displayed items to the results of a search
    void filterItems(CharSequence query) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String qlower = query.toString().toLowerCase(Locale.ROOT);
            List<Item> results = dataClass.StoreItems.stream()
                .filter(i->i.name.toLowerCase(Locale.ROOT).contains(qlower))
                .collect(Collectors.toList());
            items.clear();
            items.addAll(genProducts(results));
            itemAdapter.notifyDataSetChanged();
        }
    }

    // Initializes the list of products and the searchbar
    void setupScreen() {
        GridView products = root.findViewById(R.id.store_product_grid);
        EditText searchbar = root.findViewById(R.id.store_search);

        // one hashmap for each product to display, all in a list
        items = genProducts(dataClass.StoreItems);
        itemAdapter = new ItemAdapter(
            root.getContext(), items,
            R.layout.main_store_item,
            new String[]{"img", "name", "price", "stars", "id"},
            new int[]{R.id.product_image, R.id.product_name, R.id.product_price, R.id.product_rating, R.id.product_id}
        );
        products.setAdapter(itemAdapter);

        searchbar.addTextChangedListener(new TextWatcher() {
            // Irrelevant unused functions
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            // The real one, filter items on change of text
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems(s);
            }
        });
    }
}