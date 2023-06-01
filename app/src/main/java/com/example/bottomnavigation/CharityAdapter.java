package com.example.bottomnavigation;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentContainerView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



// Nice basic adapter that just adds a button along with base SimpleAdapter functionality
public class CharityAdapter extends SimpleAdapter {
    static DataClass dataClass = DataClass.get_instance();

    static FragmentContainerView donatepopup;



    public CharityAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    // Adds a view with functionality for a single charity
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = super.getView(position, convertView, parent);
        donatepopup = ((View) parent.getParent()).findViewById(R.id.donate_page);
        // Find the button and the ID
        Button buy_button = root.findViewById(R.id.donate_button);
        TextView id_text = root.findViewById(R.id.charity_id);
        String string_id = (String) id_text.getText();
        int id = Integer.valueOf(string_id);

        // Find the item that this view refers to
        Charity itm;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itm = dataClass.CharityItems.stream()
                .filter((Charity i) -> i.id == id)
                .collect(Collectors.toList())
                .get(0);
        } else itm = null;

        // Set a listener that adds the item to the cart and says so
        buy_button.setOnClickListener((v)->{
            setPopupVisible(true);
            ((PopupFragment)donatepopup.getFragment()).setCharity(itm);
        });

        // Required for parity with base SimpleAdapter
        return root;
    }

    // Function for toggling the visibility of the cart (and conversely, the nav items)
    void setPopupVisible(boolean visible) {
        if (visible) {
            donatepopup.setVisibility(View.VISIBLE);
        } else {
            donatepopup.setVisibility(View.GONE);
        }
    }
}
