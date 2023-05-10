package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentThreeBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentThree extends Fragment {

    private FragmentThreeBinding binding;
    private View root;

    // This function is called when this fragment's view is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThreeBinding.inflate(inflater, container, false);
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


    HashMap<String, Object> genProduct(String name, String desc) {
        Charity chari = new Charity(name, desc);
        return new HashMap<String, Object>() {{
            put("chari", chari);
            put("name", chari.name);
        }};
    }

    void setupScreen() {
        // Since we're now in a Fragment and not an Activity, we can't just call findViewById()
        // directly. Instead, need to call root.findViewById() to to the connect XML views.


        GridView charities = root.findViewById(R.id.charity_grid);



        ArrayList<HashMap<String,Object>> test = new ArrayList<HashMap<String,Object>>() {{
            for (int i=0; i<5; i++){
                add(genProduct("Charity 1", "This is a charity"));
                add(genProduct("Charity 2", "This is a charity"));
                add(genProduct("Charity 3", "This is a charity"));
                add(genProduct("Charity 4", "This is a charity"));
            }
        }};
        BaseAdapter adapter = new SimpleAdapter(
                root.getContext(), test,
                R.layout.charity_item,
                new String[]{"name"},
                new int[]{R.id.charity_name}
        );
        charities.setAdapter(adapter);



    }
}
