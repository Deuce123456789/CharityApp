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

    // This function is called when this fragment's view is first created
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOneBinding.inflate(inflater, container, false);
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

    String repeat(String base, int reps) {
        return new String(new char[reps]).replace("\0",base);
    }

    HashMap<String, Object> genProduct(int img, String name, double price, int rating) {
        String stars = repeat("★", rating) + repeat("☆", 5-rating);
        Item itm = new Item(name, (float)price, rating);
        return new HashMap<String, Object>() {{
            put("img", img);
            put("itm", itm);
        }};
    }

    void setupScreen() {
        // Since we're now in a Fragment and not an Activity, we can't just call findViewById()
        // directly. Instead, need to call root.findViewById() to to the connect XML views.
        GridView products = root.findViewById(R.id.store_product_grid);

        // one list entry for each product to display
        ArrayList<HashMap<String,Object>> test = new ArrayList<HashMap<String,Object>>() {{
            for (int i=0; i<5; i++){
                add(genProduct(R.drawable.ic_star_1, "Product", 69.99, 3));
                add(genProduct(R.drawable.ic_dashboard_black_24dp, "Other Thingy", 547.28, 4));
                add(genProduct(R.drawable.ic_notifications_black_24dp, "Whee", 23048.77, 0));
            }
        }};
        BaseAdapter adapter = new SimpleAdapter(
            root.getContext(), test,
            R.layout.main_store_item,
            new String[]{"img"},
            new int[]{R.id.product_image}
        );
        products.setAdapter(adapter);
    }
}