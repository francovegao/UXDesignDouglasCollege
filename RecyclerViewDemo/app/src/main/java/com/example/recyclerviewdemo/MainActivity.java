package com.example.recyclerviewdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recyclerviewdemo.adapters.TuneAdapter;
import com.example.recyclerviewdemo.databinding.ActivityMainBinding;
import com.example.recyclerviewdemo.model.Tune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> TuneNames = new ArrayList<>(Arrays.asList("Beauty and the Beast", "Lion King", "Mary Poppins", "Game of Thrones", "Ozark"));
    List<Integer> TunePics = new ArrayList<>(Arrays.asList(R.drawable.beauty, R.drawable.lionking, R.drawable.marypoppins, R.drawable.gameofthrones, R.drawable.ozark));
    List<Tune> TuneList = new ArrayList<>();  //Initialize to empty list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LoadModel();  //Creates the List of data
        Log.d("RECYCLERVIEWDEMO", TuneList.size() + " Tunes");

        //Initialize adapter and layout manager
        TuneAdapter tuneAdapter = new TuneAdapter(TuneList);
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        //Attach layout manager and adapter
        binding.recyclerViewTunes.setLayoutManager(lm);
        binding.recyclerViewTunes.setAdapter(tuneAdapter);
    }

    private void LoadModel(){
        for(int i = 0; i<TuneNames.size(); i++){
            Tune tune = new Tune(TuneNames.get(i), TunePics.get(i));
            TuneList.add(tune);  //Add to list (cannot add unless list is initialized
        }
    }

}