package com.example.nov13demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nov13demo.databinding.LayoutColoritemBinding;

import java.util.List;

public class ColorSpecAdapter extends BaseAdapter {
    List<ColorSpec> AdapterColors;
    LayoutColoritemBinding binding;

    public ColorSpecAdapter(List<ColorSpec> adapterColors) {
        AdapterColors = adapterColors;
    }

    @Override
    public int getCount() {
        return AdapterColors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            binding = LayoutColoritemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        }

        binding.txtViewColorItem.setText(AdapterColors.get(i).getColorDesc());
        binding.txtViewColorItem.setTextColor(AdapterColors.get(i).getColorVal());

        return binding.getRoot();
    }
}
