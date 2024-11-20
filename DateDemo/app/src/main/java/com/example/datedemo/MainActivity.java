package com.example.datedemo;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.datedemo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Dog> DogList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ReadFromFile();

        binding.recyclerViewDogItems
                .setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.recyclerViewDogItems.setAdapter(new DogAdapter(DogList));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ReadFromFile(){
        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String dogLine;

            //Reader has header line
            if((dogLine=reader.readLine())!=null){
                //dogLine contains header
            }
            while((dogLine= reader.readLine())!= null){
                String[] dogFields = dogLine.split(",");
                int dogId = Integer.parseInt(dogFields[0]);
                int dogDrawable = getResources().getIdentifier(dogFields[1],"drawable",getPackageName());
                String dogName = dogFields[2];

                //date pattern
                //d - one or more digits for date
                //dd- two digits for date
                //MM - two digit month number
                //MMM - 3 digit month letter code
                //yy - 2 digit year code
                //yyyy - 4 digit year code
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dogDob = LocalDate.parse(dogFields[3], formatter);

                Dog dog = new Dog(dogId,dogName,dogDrawable,dogDob);
                DogList.add(dog);
            }
        }catch(Exception ex){

        }

    }

}