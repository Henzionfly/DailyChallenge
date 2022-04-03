package com.kariol.dailychallenge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kariol.dailychallenge.databinding.FragmentLevelBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFragment extends Fragment {
    private UserModel userModel;
    private NavController controller;
    FragmentLevelBinding binding;

    public LevelFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(UserModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_level, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.level_check);
        binding.simpleIcon.setOnClickListener(view -> {
            builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> {
                userModel.getGameLevel().setValue(1);
                userModel.getTargetScore().setValue(50 + userModel.getGameLevel().getValue() * 10);
                userModel.getMaxCount().setValue(10);
                userModel.getMaxNum().setValue(10);
                controller = Navigation.findNavController(view);
                controller.navigate(R.id.level_to_math);
            });
            builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        binding.mediumIcon.setOnClickListener(view -> {
            builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> {
                userModel.getGameLevel().setValue(2);
                userModel.getTargetScore().setValue(50 + userModel.getGameLevel().getValue() * 10);
                userModel.getMaxCount().setValue(20);
                userModel.getMaxNum().setValue(10);
                controller = Navigation.findNavController(view);
                controller.navigate(R.id.level_to_math);
            });
            builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        binding.highIcon.setOnClickListener(view -> {
            builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> {
                userModel.getGameLevel().setValue(3);
                userModel.getTargetScore().setValue(50 + userModel.getGameLevel().getValue() * 10);
                userModel.getMaxCount().setValue(50);
                userModel.getMaxNum().setValue(20);
                controller = Navigation.findNavController(view);
                controller.navigate(R.id.level_to_math);
            });
            builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}