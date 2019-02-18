package com.example.seeme.enums;

import android.content.Context;

import com.example.seeme.R;

public enum TakePictureMenu {
    TAKE_PHOTO(0, R.string.text_take_photo), CHOOSE_PHOTO(1, R.string.text_pick_photo);

    int status;
    int titleId;

    TakePictureMenu(int status, int titleId) {
        this.status = status;
        this.titleId = titleId;
    }

    public int getTitleId() {
        return titleId;
    }

    public static String[] getTitles(Context context) {
        String [] titles = new String[TakePictureMenu.values().length];
        for(int i = 0; i < TakePictureMenu.values().length; i++) {
            titles[i] = context.getString(TakePictureMenu.values()[i].getTitleId());
        }
        return titles;
    }
}