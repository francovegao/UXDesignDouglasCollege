package com.example.datedemo;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datedemo.databinding.LayoutDogitemBinding;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {
    List<Dog> AdapterDogList;
    Context context;

    public DogAdapter(List<Dog> adapterDogList) {
        AdapterDogList = adapterDogList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        context = parent.getContext();
        //Inflate the binding
        LayoutDogitemBinding binding = LayoutDogitemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        //using binding create holder object
        DogViewHolder holder = new DogViewHolder(binding.getRoot(),binding);
        //return holder object
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        //integer needs to be converted to string before setText
        holder.dogBinding.txtViewId
                .setText(String.valueOf(AdapterDogList.get(position).getId()));
        holder.dogBinding.txtViewName.setText(AdapterDogList.get(position).getDogName());
        holder.dogBinding.imgViewDogPic.setImageResource(AdapterDogList.get(position).getDogPicDrawable());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        holder.dogBinding.txtViewDOB.setText(formatter.format(AdapterDogList.get(position).getDogDob()));
    }

    @Override
    public int getItemCount() {
        return AdapterDogList.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        LayoutDogitemBinding dogBinding;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public DogViewHolder(@NonNull View itemView, LayoutDogitemBinding dogBinding) {
            super(itemView);
            this.dogBinding = dogBinding;
            this.dogBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Dog Name: " + AdapterDogList.get(getAdapterPosition()).getDogName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
