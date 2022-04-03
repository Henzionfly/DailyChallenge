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

import com.kariol.dailychallenge.databinding.FragmentLoginBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private UserModel userModel;
    private NavController controller;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(UserModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.loginButton.setOnClickListener(view -> {
            String account = String.valueOf(binding.accountInput.getText());
            controller =  Navigation.findNavController(view);
            if (userModel.getUsername().getValue() == null) {
                if (binding.accountInput.getText() == null){
                    Toast.makeText(requireContext(), R.string.account_null, Toast.LENGTH_SHORT).show();
                } else {
                    userModel.getUsername().setValue(account);
                    Toast.makeText(requireContext(), R.string.login_message_first, Toast.LENGTH_SHORT).show();
                    controller.navigate(R.id.login_to_home);
                }
            } else {
                controller.navigate(R.id.homeFragment);
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

}