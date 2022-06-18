package com.salman.moterspart.other;

import android.content.Context;
import android.provider.Settings;

public class UniqeDeviceID {
    Context context;
    public UniqeDeviceID(Context context){
        this.context = context;
    }

    public String getUID(){
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }
}
