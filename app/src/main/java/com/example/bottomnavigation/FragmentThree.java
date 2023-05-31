package com.example.bottomnavigation;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentThreeBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FragmentThree extends Fragment {

    private FragmentThreeBinding binding;
    private View root;
    private CharityAdapter itemAdapter;
    private DataClass dataClass;
    private ArrayList<HashMap<String,?>> items;

    // This function is called when this fragment's view is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThreeBinding.inflate(inflater, container, false);
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

    // Generates a single product to be passed into the adapter and rendered.
    HashMap<String, Object> genProduct(String name, String desc, int id) {
        Charity chari = new Charity(name, desc);
        return new HashMap<String, Object>() {{
            put("chari", chari);
            put("name", chari.name);
            put("id", id);
        }};
    }

    // Generates a single product to be passed into the adapter and rendered.
    ArrayList<HashMap<String,?>> genProducts(List<Charity> itms) {
        return new ArrayList<HashMap<String,?>>() {{
            for (Charity cha : itms) {
                add(genProduct(cha.name, cha.desc, cha.id));
            }
        }};
    }

    // Sets the list of currently displayed items to the results of a search
    void filterItems(CharSequence query) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String qlower = query.toString().toLowerCase(Locale.ROOT);
            List<Charity> results = dataClass.CharityItems.stream()
                    .filter(i->i.name.toLowerCase(Locale.ROOT).contains(qlower))
                    .collect(Collectors.toList());
            items.clear();
            items.addAll(genProducts(results));
            itemAdapter.notifyDataSetChanged();
        }
    }

    // Initializes the list of charities and the searchbar
    void setupScreen() {
        GridView charities = root.findViewById(R.id.charity_grid);
        EditText searchbar = root.findViewById(R.id.store_search);

        items = genProducts(dataClass.CharityItems);
        itemAdapter = new CharityAdapter(
                root.getContext(), items,
                R.layout.charity_item,
                new String[]{"name", "id"},
                new int[]{R.id.charity_name, R.id.charity_id}
        );
        charities.setAdapter(itemAdapter);

        searchbar.addTextChangedListener(new TextWatcher() {
            // Irrelevant unused functions
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            // The real one
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems(s);
            }
        });
    }
}
