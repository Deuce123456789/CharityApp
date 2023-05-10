package com.example.bottomnavigation;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentOneBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentOne extends Fragment {
    //This is a test
    private FragmentOneBinding binding;
    private View root;
    private DataClass dataClass;

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

    String repeat(String base, int reps) {
        return new String(new char[reps]).replace("\0",base);
    }

    HashMap<String, Object> genProduct(int img, String name, double price, int rating, int id) {
        String stars = repeat("★", rating) + repeat("☆", 5-rating);
        return new HashMap<String, Object>() {{
            put("img", img);
            put("name", name);
            put("price", String.format("%.2f", price));
            put("stars", stars);
            put("id", id);
        }};
    }

    void setupScreen() {
        // Since we're now in a Fragment and not an Activity, we can't just call findViewById()
        // directly. Instead, need to call root.findViewById() to to the connect XML views.
        GridView products = root.findViewById(R.id.store_product_grid);

        // one list entry for each product to display
        ArrayList<HashMap<String,Object>> test = new ArrayList<HashMap<String,Object>>() {{
            for (Item itm : dataClass.StoreItems) {
                add(genProduct(R.drawable.ic_star_1, itm.name, itm.price, itm.rating, itm.id));
            }
        }};
        BaseAdapter adapter = new SimpleAdapter(
            root.getContext(), test,
            R.layout.main_store_item,
            new String[]{"img", "name", "price", "stars", "id"},
            new int[]{R.id.product_image, R.id.product_name, R.id.product_price, R.id.product_rating, R.id.product_id}
        );
        products.setAdapter(adapter);
        adapter.
    }
}