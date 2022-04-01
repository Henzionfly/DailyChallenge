package com.kariol.dailychallenge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kariol.dailychallenge.databinding.FragmentLoginBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    FragmentLoginBinding loginBinding;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserModel userModel;
        userModel = ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity()).get(UserModel.class);
        FragmentLoginBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = String.valueOf(binding.accountInput.getText());
                String password = String.valueOf(binding.pwdInput.getText());
                if (Objects.equals(userModel.getUsername().getValue(), "0")){
                    userModel.getUsername().setValue(account);
                    userModel.getPassword().setValue(password);
                }
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        return null;
    }
}