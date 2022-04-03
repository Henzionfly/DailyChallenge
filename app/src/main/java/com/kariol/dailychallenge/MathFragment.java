package com.kariol.dailychallenge;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kariol.dailychallenge.databinding.FragmentMathBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MathFragment extends Fragment {
    private UserModel userModel;
    FragmentMathBinding binding;
    private NavController controller;

    public MathFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(UserModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_math, container, false);
        binding.setUser(userModel);
        binding.setLifecycleOwner(requireActivity());
        userModel.generator();
        final StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.num0:
                        builder.append(0);
                        break;
                    case R.id.num1:
                        builder.append(1);
                        break;
                    case R.id.num2:
                        builder.append(2);
                        break;
                    case R.id.num3:
                        builder.append(3);
                        break;
                    case R.id.num4:
                        builder.append(4);
                        break;
                    case R.id.num5:
                        builder.append(5);
                        break;
                    case R.id.num6:
                        builder.append(6);
                        break;
                    case R.id.num7:
                        builder.append(7);
                        break;
                    case R.id.num8:
                        builder.append(8);
                        break;
                    case R.id.num9:
                        builder.append(9);
                        break;
                    case R.id.clear:
                        builder.setLength(0);
                        break;
                }
                if (builder.length() == 0) {
                    binding.ansText.setText(getString(R.string.ansText));
                } else {
                    binding.ansText.setText(builder.toString());
                }
            }
        };

        binding.num0.setOnClickListener(listener);
        binding.num1.setOnClickListener(listener);
        binding.num2.setOnClickListener(listener);
        binding.num3.setOnClickListener(listener);
        binding.num4.setOnClickListener(listener);
        binding.num5.setOnClickListener(listener);
        binding.num6.setOnClickListener(listener);
        binding.num7.setOnClickListener(listener);
        binding.num8.setOnClickListener(listener);
        binding.num9.setOnClickListener(listener);
        binding.clear.setOnClickListener(listener);

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View view) {
                controller = Navigation.findNavController(view);
                if (builder.length() == 0) {
                    builder.append(-1);
                }
                if (Integer.valueOf(builder.toString()).intValue() == userModel.getAnswer().getValue()) {
                    builder.setLength(0);
                    userModel.answerCheck(controller);
                    binding.ansText.setText(R.string.check_message);
                } else {
                    userModel.goldCheck(controller);
                }
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_math_challenge, container, false);
        return binding.getRoot();
    }
}