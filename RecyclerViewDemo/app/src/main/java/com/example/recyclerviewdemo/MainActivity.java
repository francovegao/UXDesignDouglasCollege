package com.example.recyclerviewdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        //Initialize adapter
        TuneAdapter tuneAdapter = new TuneAdapter(TuneList);

        //Initialize grid layout manager and bind it to the recycler view
        GridLayoutManager gm = new GridLayoutManager(MainActivity.this, 2);
        //binding.recyclerViewTunes.setLayoutManager(gm);

        //Initialize layout manager and Attach layout manager
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        binding.recyclerViewTunes.setLayoutManager(lm);

        //Bind the adapter
        binding.recyclerViewTunes.setAdapter(tuneAdapter);

        //Add item touch listener callback for swipe or move functions
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START | ItemTouchHelper.END) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //get the index of the item
                int position = viewHolder.getAdapterPosition();

                //Left(START) SWIPE - Alert Dialog Builder
                //Right(END) SWIPE - Delete Item
                if(direction == ItemTouchHelper.START){
                    //Left swipe
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Enter new track name");
                    EditText editTextInputName = new EditText(MainActivity.this);
                    builder.setView(editTextInputName);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TuneList.get(position).setTuneName(editTextInputName.getText().toString());
                            //Update adapter
                            tuneAdapter.notifyItemChanged(position);
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tuneAdapter.notifyItemChanged(position);
                        }
                    });

                    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            tuneAdapter.notifyItemChanged(position);
                        }
                    });

                    builder.show();
                }else{
                    //Right swipe
                    TuneList.remove(position);
                    tuneAdapter.notifyItemRemoved(position);
                }
            }
        };

        //Attach callback to itemtouch helper and then attach helper to recycler view
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(binding.recyclerViewTunes);

    }

    private void LoadModel(){
        for(int i = 0; i<TuneNames.size(); i++){
            Tune tune = new Tune(TuneNames.get(i), TunePics.get(i));
            TuneList.add(tune);  //Add to list (cannot add unless list is initialized
        }
    }

}