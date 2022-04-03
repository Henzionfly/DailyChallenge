package com.kariol.dailychallenge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kariol.dailychallenge.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private UserModel userModel;
    FragmentHomeBinding binding;
    private NavController controller;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(UserModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setUser(userModel);
        binding.setLifecycleOwner(requireActivity());

        binding.mathIcon.setOnClickListener(view -> {
            userModel.getGameLevel().setValue(userModel.getGameLevel().getValue());
            userModel.getScore().setValue(0);
            userModel.getCount().setValue(0);
            controller = Navigation.findNavController(view);
            controller.navigate(R.id.home_to_level);
        });

        binding.yuwenIcon.setOnClickListener(view -> Toast.makeText(requireContext(), R.string.wait_message, Toast.LENGTH_SHORT).show());
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false);
        return binding.getRoot();
    }
}