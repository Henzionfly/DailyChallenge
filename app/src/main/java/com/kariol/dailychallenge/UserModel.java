package com.kariol.dailychallenge;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class UserModel extends AndroidViewModel {

    SavedStateHandle handle;
    SharedPreferences shp;
    Random random;

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final static String GOLD = "gold";
    private final static String TARGET_SCORE = "target_score";
    private final static String SCORE = "score";
    private final static String ANSWER = "answer";
    private final static String QUESTION = "question";
    private final static String SAVA_DATA = "sava_data";

    public UserModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(GOLD)) {
            shp = getApplication().getSharedPreferences(SAVA_DATA, Context.MODE_PRIVATE);
            handle.set(USERNAME, "0");
            handle.set(PASSWORD, "0");
            handle.set(GOLD, shp.getInt(GOLD, 0));
            handle.set(TARGET_SCORE, 0);
            handle.set(SCORE, 0);
            handle.set(ANSWER, 0);
            handle.set(QUESTION, "1 + 1 = ?");
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getGold() {
        return handle.getLiveData(GOLD);
    }

    public MutableLiveData<String> getUsername() {
        return handle.getLiveData(USERNAME);
    }

    public MutableLiveData<String> getPassword() {
        return handle.getLiveData(PASSWORD);
    }

    public MutableLiveData<Integer> getTargetScore() {
        return handle.getLiveData(TARGET_SCORE);
    }

    public MutableLiveData<Integer> getScore() {
        return handle.getLiveData(SCORE);
    }

    public MutableLiveData<String> getQuestion() {
        return handle.getLiveData(QUESTION);
    }

    public MutableLiveData<Integer> getAnswer() {
        return handle.getLiveData(ANSWER);
    }

    public void generator(Integer level) {
        int x, y;
        String mark;
        x = random.nextInt(level * 100) + 1;
        y = random.nextInt(level * 100) + 1;
        switch (level) {
            case 1:
                if (x % 2 == 0) {
                    mark = "+";
                    getQuestion().setValue(String.format("%s %s %s = ?", x, mark, y));
                    getAnswer().setValue(x + y);
                } else {
                    mark = "-";
                    getQuestion().setValue(x > y ? String.format("%s %s %s = ?", x, mark, y) : String.format("%s %s %s = ?", y, mark, x));
                    getAnswer().setValue(x > y ? x - y : y - x);
                }
                break;
            case 2:
                if (x % 4 == 1) {
                    mark = "➕";
                    getQuestion().setValue(String.format("%s %s %s = ?", x, mark, y));
                    getAnswer().setValue(x + y);
                }
                if (x % 4 == 2) {
                    mark = "➖";
                    getQuestion().setValue(x > y ? String.format("%s %s %s = ?", x, mark, y) : String.format("%s %s %s = ?", y, mark, x));
                    getAnswer().setValue(x > y ? x - y : y - x);
                }
                if (x % 4 == 3) {
                    mark = "✖";
                    getQuestion().setValue(String.format("%s %s %s = ?", x, mark, y));
                    getAnswer().setValue(x * y);
                } else {
                    mark = "➗";
                    y = 4 * random.nextInt(level);
                    getQuestion().setValue(x > y ? String.format("%s %s %s = ?", x, mark, y) : String.format("%s %s %s = ?", y, mark, x));
                    getAnswer().setValue(x > y ? x / y : y / x);
                }
                break;
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("CommitPrefEdits")
    public void save() {
        SharedPreferences.Editor editor;
        editor = shp.edit();
        editor.putString(USERNAME, getUsername().getValue());
        editor.putString(PASSWORD, getPassword().getValue());
        editor.putInt(GOLD, getGold().getValue());
        editor.apply();
    }

    @SuppressWarnings("ConstantConditions")
    public void answerCheck(Integer level) {
        getScore().setValue(getScore().getValue() + 1);
        generator(level);
    }

    @SuppressWarnings("ConstantConditions")
    public void answerWrong(Integer level) {
        if (getScore().getValue() >= getTargetScore().getValue()) {
            getGold().setValue(getGold().getValue() + level * 10);
        } else {
            getGold().setValue(getGold().getValue() - level * 5);
        }
    }

}
