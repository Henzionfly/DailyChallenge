package com.kariol.dailychallenge;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavController;

import java.util.Random;


public class UserModel extends AndroidViewModel {

    SavedStateHandle handle;
    private SharedPreferences shp;

    private final String USERNAME = "username";
    private final static String GOLD = "gold";
    private final static String TARGET_SCORE = "target_score";
    private final static String SCORE = "score";
    private final static String ANSWER = "answer";
    private final static String QUESTION = "question";
    private final static String SAVA_DATA = "sava_data";
    private final static String GAME_LEVEL = "game_level";
    private final static String GOT_GOLD = "got_gold";
    private final static String LOSE_GOLD = "lose_gold";
    private final static String COUNT = "count";
    private final static String MAX_COUNT = "max_count";
    private final static String MAX_NUM = "max_num";

    public UserModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(GOLD)) {
            shp = getApplication().getSharedPreferences(SAVA_DATA, Context.MODE_PRIVATE);
            handle.set(GOLD, shp.getInt(GOLD, 0));
            handle.set(TARGET_SCORE, 0);
            handle.set(SCORE, 0);
            handle.set(ANSWER, 0);
            handle.set(QUESTION, "1 + 1 = ?");
            handle.set(GOT_GOLD, 0);
            handle.set(LOSE_GOLD, 0);
            handle.set(COUNT, 0);
            handle.set(MAX_COUNT, 10);
            handle.set(GAME_LEVEL, 1);
            handle.set(MAX_NUM, 10);
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getGold() {
        return handle.getLiveData(GOLD);
    }

    public MutableLiveData<String> getUsername() {
        return handle.getLiveData(USERNAME);
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

    public MutableLiveData<Integer> getGameLevel() {
        return handle.getLiveData(GAME_LEVEL);
    }

    public MutableLiveData<Integer> getMaxNum() {
        return handle.getLiveData(MAX_NUM);
    }

    public MutableLiveData<Integer> getGotGold() {
        return handle.getLiveData(GOT_GOLD);
    }

    public MutableLiveData<Integer> getLoseGold() {
        return handle.getLiveData(LOSE_GOLD);
    }

    public MutableLiveData<Integer> getCount() {
        return handle.getLiveData(COUNT);
    }

    public MutableLiveData<Integer> getMaxCount() {
        return handle.getLiveData(MAX_COUNT);
    }

    public Integer ranInt(Integer num) {
        return new Random().nextInt(num) + 1;
    }

    public String question(Integer x, Integer y, Integer m) {
        String que = "";
        switch (m) {
            case 1:
                que = String.format("%s ➕ %s = ?", x, y);
                break;
            case 2:
                que = x > y ? String.format("%s ➖ %s = ?", x, y) : String.format("%s ➖ %s = ?", y, x);
                break;
            case 3:
                que = String.format("%s ✖ %s = ?", x, y);
                break;
            case 4:
                que = String.format("%s ➗ %s = ?", y, x);
                break;
        }
        return que;
    }

    @SuppressWarnings({"ConstantConditions", "DuplicateExpressions"})
    public void generator() {
        int x = ranInt(getMaxNum().getValue());
        int y = ranInt(getMaxNum().getValue());
        switch (getGameLevel().getValue()) {
            case 1:
                if (x % 2 == 0) {
                    getQuestion().setValue(question(x, y, 1));
                    getAnswer().setValue(x + y);
                } else {
                    getQuestion().setValue(question(x, y, 2));
                    getAnswer().setValue(x > y ? x - y : y - x);
                }
                break;
            case 2:
                if (x % 4 == 0) {
                    getQuestion().setValue(question(x, y, 1));
                    getAnswer().setValue(x + y);
                } else if (x % 4 == 1) {
                    getQuestion().setValue(question(x, y, 2));
                    getAnswer().setValue(x > y ? x - y : y - x);
                } else if (x % 4 == 2) {
                    getQuestion().setValue(question(x, y, 3));
                    getAnswer().setValue(x * y);
                } else {
                    y = x * ranInt(getMaxNum().getValue());
                    getQuestion().setValue(question(x, y, 4));
                    getAnswer().setValue(y / x);
                }
                break;
            case 3:
                if (x % 4 == 1) {
                    getQuestion().setValue(question(x, y, 1));
                    getAnswer().setValue(x + y);
                } else if (x % 4 == 2) {
                    getQuestion().setValue(question(x, y, 2));
                    getAnswer().setValue(x > y ? x - y : y - x);
                } else if (x % 4 == 3) {
                    getQuestion().setValue(question(x, y, 3));
                    getAnswer().setValue(x * y);
                } else {
                    y = x * ranInt(getMaxNum().getValue());
                    getQuestion().setValue(question(x, y, 4));
                    getAnswer().setValue(y / x);
                }
                break;
        }
        getCount().setValue(getCount().getValue() + 1);
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("CommitPrefEdits")
    public void save() {
        SharedPreferences.Editor editor;
        editor = shp.edit();
        editor.putString(USERNAME, getUsername().getValue());
        editor.putInt(GOLD, getGold().getValue());
        editor.apply();
    }

    @SuppressWarnings("ConstantConditions")
    public void answerCheck(NavController controller) {
        getScore().setValue(getScore().getValue() + 100 / getMaxCount().getValue());
        if (getCount().getValue() >= getMaxCount().getValue()) {
            goldCheck(controller);
        } else {
            generator();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void goldCheck(NavController controller) {
        if (getScore().getValue() >= getTargetScore().getValue()) {
            getGotGold().setValue(getGameLevel().getValue() * getCount().getValue());
            getGold().setValue(getGold().getValue() + getGotGold().getValue());
            save();
            controller.navigate(R.id.math_to_win);
        } else {
            getLoseGold().setValue(getGameLevel().getValue() * getMaxCount().getValue());
            int gold = getGold().getValue() - getLoseGold().getValue();
            getGold().setValue(Math.max(gold, 0));
            save();
            controller.navigate(R.id.math_to_lose);
        }
    }
}
