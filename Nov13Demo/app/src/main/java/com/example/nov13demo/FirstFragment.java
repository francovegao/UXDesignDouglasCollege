package com.example.nov13demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.nov13demo.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    List<ColorSpec> FragColors;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up ViewModel and observers for the view model
        ColorSpecViewModel colorSpecViewModel = new ViewModelProvider(requireActivity()).get(ColorSpecViewModel.class);

        colorSpecViewModel.getColorList().observe(getViewLifecycleOwner(), new Observer<List<ColorSpec>>() {
            @Override
            public void onChanged(List<ColorSpec> colorSpecs) {
                Toast.makeText(getContext(),colorSpecs.size()+" Colors in the ViewModel", Toast.LENGTH_LONG).show();
                FragColors = colorSpecs;  //setting up fragment data from ViewModel
                binding.spinnerColors.setAdapter(new ColorSpecAdapter(FragColors));
            }
        });

        binding.buttonFirst.setOnClickListener(v -> {
                    int index = binding.spinnerColors.getSelectedItemPosition();
                    int colorVal = FragColors.get(index).getColorVal();
                    Bundle bundle = new Bundle();
                    bundle.putInt("COLORVAL", colorVal);

                    //Navigate to second fragment and pass bundle
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}