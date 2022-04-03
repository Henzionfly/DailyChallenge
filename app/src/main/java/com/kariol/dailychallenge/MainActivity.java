package com.kariol.dailychallenge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, this.controller);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.mathFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.back_challenge_message));
            builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> controller.navigate(R.id.math_to_home));
            builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (controller.getCurrentDestination().getId() == R.id.homeFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.back_login_message));
            builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> controller.navigate(R.id.home_to_login));
            builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            controller.navigate(R.id.homeFragment);
        }
        return super.onSupportNavigateUp();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (controller.getCurrentDestination().getId() == R.id.mathFragment) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.back_challenge_message));
                builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> controller.navigate(R.id.math_to_home));
                builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (controller.getCurrentDestination().getId() == R.id.homeFragment) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.back_login_message));
                builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> controller.navigate(R.id.home_to_login));
                builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (controller.getCurrentDestination().getId() == R.id.loginFragment) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.quit_fragment));
                builder.setPositiveButton(R.string.back_yes_message, (dialogInterface, i) -> finish());
                builder.setNegativeButton(R.string.back_cancel_message, (dialogInterface, i) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                controller.navigate(R.id.homeFragment);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return false;
    }
}