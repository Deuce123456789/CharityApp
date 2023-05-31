package com.example.bottomnavigation;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

// The adapter class that displays all items in the cart
public class CartAdapter extends SimpleAdapter {
    // Custom class for a function that returns something and takes no arguments
    // This is used as the type for the data refresher
    public interface Getter<T> {
        T get();
    }

    // Reference to the dataclass
    static DataClass dataClass = DataClass.get_instance();

    // Variables (the last two are functions of various types)
    private final ArrayList<HashMap<String, ?>> data;
    private final Getter<ArrayList<? extends HashMap<String, ?>>> dataFromCart;
    private final Runnable onUpdateData;

    public CartAdapter(
            Context context,
            Getter<ArrayList<? extends HashMap<String, ?>>> dataGetter,
            ArrayList<HashMap<String, ?>> firstData,
            Runnable onUpdateData,
            int resource, String[] from, int[] to
    ) {
        super(context, firstData, resource, from, to);
        data = firstData;
        dataFromCart = dataGetter;
        this.onUpdateData = onUpdateData;
    }

    // This function is called for each item that the cart displays.
    // The superclass creates the view and the rest of the function
    // adds functionality to it.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = super.getView(position, convertView, parent);

        onUpdateData.run();

        // Find the button and the ID
        Button remove_button = root.findViewById(R.id.product_cart_remove_button);
        TextView id_text = root.findViewById(R.id.product_id);
        String string_id = (String) id_text.getText();
        int id = Integer.parseInt(string_id);

        // Find the item that this view refers to
        Item itm;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itm = dataClass.StoreItems.stream()
                .filter((Item i) -> i.id == id)
                .collect(Collectors.toList())
                .get(0);
        } else itm = null;

        // Set a listener that removes the item from the cart and says so
        remove_button.setOnClickListener((v)->{
            dataClass.cart.remove(itm);
            updateData();
            onUpdateData.run(); //the external one
            Toast.makeText(root.getContext(),
                    "Item '"+itm.name+"' removed from cart.", Toast.LENGTH_LONG
            ).show();
        });

        // Required for parity with base SimpleAdapter
        return root;
    }

    // Called to notify the class that its data has changed and that it should update accordingly.
    void updateData() {
        data.clear();
        data.addAll(dataFromCart.get());
        notifyDataSetChanged();
    }
}
