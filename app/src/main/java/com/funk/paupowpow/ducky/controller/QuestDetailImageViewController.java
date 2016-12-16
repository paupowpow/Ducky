package com.funk.paupowpow.ducky.controller;

import android.graphics.Bitmap;

/**
 * Created by paulahaertel on 16.12.16.
 */

public class QuestDetailImageViewController {

    private Bitmap image;

    public QuestDetailImageViewController(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
