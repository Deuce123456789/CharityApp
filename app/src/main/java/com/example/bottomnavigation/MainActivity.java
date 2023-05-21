package com.example.bottomnavigation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bottomnavigation.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    static FragmentContainerView cart, nav;

    // This function is created when this screen (MainActivity) is first created and started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupActionBar();
        setupBottomNav();

        cart = findViewById(R.id.cart_page);
        nav = findViewById(R.id.nav_host_fragment_activity_main);

        BottomNavigationView bnv = findViewById(R.id.nav_view);
        bnv.setOnItemSelectedListener(item -> {
            Navigation.findNavController(nav).navigate(item.getItemId());
            setCartVisible(false);
            return true;
        });
    }

    void setupBottomNav() {
        // This is the nav_view "container" as laid out in the XML file
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // This sets up the items in the bottom navigation bar. In this case, we have four
        // items, as indicated by the four menu IDs (R.id.navigation_first, etc.)
        // On startup, the first menu
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_first, R.id.navigation_second, R.id.navigation_third)
                .build();

        // This is the fragment as specified in the XML file
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        // These two lines sets up the navigation controller using the above components
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


    void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        View abView = getLayoutInflater().inflate(R.layout.general_top_header, null);
        TextView tokens = abView.findViewById(R.id.token_counter);
        DataClass.get_instance().NumTokens.observe(this,
                i -> tokens.setText("tokens: "+Integer.toString(i)));
        ImageView cart_icon = abView.findViewById(R.id.cart_icon);
        cart_icon.setOnClickListener(v->setCartVisible(true));


        actionBar.setCustomView(abView, layoutParams);
    }

    void setCartVisible(boolean visible) {
        if (visible) {
            cart.setVisibility(View.VISIBLE);
            nav.setVisibility(View.GONE);
            ((CartFragment)cart.getFragment()).onCartUpdate();
        } else {
            cart.setVisibility(View.GONE);
            nav.setVisibility(View.VISIBLE);
        }
    }
}