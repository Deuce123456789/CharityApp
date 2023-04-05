package com.example.bottomnavigation;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bottomnavigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // This function is created when this screen (MainActivity) is first created and started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // This is the nav_view "container" as laid out in the XML file
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // This sets up the items in the bottom navigation bar. In this case, we have four
        // items, as indicated by the four menu IDs (R.id.navigation_first, etc.)
        // On startup, the first menu
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_first, R.id.navigation_second, R.id.navigation_third, R.id.navigation_fourth)
                .build();

        // This is the fragment as specified in the XML file
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        // These two lines sets up the navigation controller using the above components
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}