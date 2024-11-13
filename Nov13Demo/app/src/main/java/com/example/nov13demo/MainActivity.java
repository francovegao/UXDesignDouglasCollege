package com.example.nov13demo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nov13demo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.mainContent.mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));

        binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ColorDrawable colorDrawable = (ColorDrawable) binding.mainContent.mainLayout.getBackground();

                int colorId = colorDrawable.getColor();

                if(colorId!=Color.LTGRAY){
                    binding.mainContent.mainLayout.setBackgroundColor(Color.LTGRAY);
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
                }else{
                    binding.mainContent.mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                }

                Snackbar.make(view, "Don't like the new color?", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(colorId==Color.LTGRAY){
                                    binding.mainContent.mainLayout.setBackgroundColor(Color.LTGRAY);
                                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
                                }else{
                                    binding.mainContent.mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                                }
                            }
                        }).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"Clicked on settings",Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_userprofile) {
            Toast.makeText(this,"Clicked on user account",Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(this,"Clicked on search",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}