package com.example.example5;

import android.net.Uri;

public class Models {
    Uri img;

    public Models(Uri img) {
        this.img = img;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }
}
