package com.example.recyclerviewdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.databinding.LayoutTuneitemBinding;
import com.example.recyclerviewdemo.model.Tune;

import java.util.List;

public class TuneAdapter extends RecyclerView.Adapter<TuneAdapter.TuneViewHolder> {

    List<Tune> adapterTuneList;
    int SelectedInd; //To keep track of what song is playing (-1 by default)

    //Constructor for the Tune Adapter
    public TuneAdapter(List<Tune> adapterTuneList) {
        this.adapterTuneList = adapterTuneList;
        SelectedInd = -1;
    }

    @NonNull
    @Override
    public TuneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create binding object
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutTuneitemBinding binding = LayoutTuneitemBinding.inflate(inflater, parent, false);

        //create holder using binding object
        TuneViewHolder holder = new TuneViewHolder(binding.getRoot(), binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder holder, int position) {
        //populate the data
        holder.holderBinding.txtViewTune.setText(adapterTuneList.get(position).getTuneName());
        holder.holderBinding.imgViewTune.setImageResource(adapterTuneList.get(position).getTunePic());
        //Start with play or pause image
        if(position== SelectedInd)
            holder.holderBinding.imgViewPlayPause.setImageResource(R.drawable.pause);
        else
            holder.holderBinding.imgViewPlayPause.setImageResource(R.drawable.play);

    }

    @Override
    public int getItemCount() {
        //size of the data
        return adapterTuneList.size();
    }

    public class TuneViewHolder extends RecyclerView.ViewHolder{

        LayoutTuneitemBinding holderBinding;

        public TuneViewHolder(@NonNull View itemView, LayoutTuneitemBinding holderBinding) {
            super(itemView);
            this.holderBinding = holderBinding;

            //this.holderBinding.getRoot().setOnClickListener();  //Use get root to set click listener on whole item

            //Set Click listener for play pause button
            this.holderBinding.imgViewPlayPause.setOnClickListener((View view)->{
                if(SelectedInd == getAdapterPosition()){
                    SelectedInd = -1;
                }else{
                    SelectedInd = getAdapterPosition();
                }
                notifyDataSetChanged(); //Means call onBind again on the entire data
            });
        }

        public TuneViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
