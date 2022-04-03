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

import com.kariol.dailychallenge.databinding.FragmentWinBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends Fragment {

    UserModel userModel;
    NavController controller;
    FragmentWinBinding binding;

    public WinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(UserModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false);
        binding.setUser(userModel);
        binding.setLifecycleOwner(requireActivity());
        binding.backButton.setOnClickListener(view -> {
            controller = Navigation.findNavController(view);
            controller.navigate(R.id.win_to_home);
        });
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_win, container, false);
        return binding.getRoot();
    }
}