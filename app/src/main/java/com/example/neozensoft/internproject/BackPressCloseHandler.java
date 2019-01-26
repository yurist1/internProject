package com.example.neozensoft.internproject;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by neozensoft on 2017-11-01.
 */

public class BackPressCloseHandler {
    private long backKeyPressedTime=0;
    private Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity context){
        this.activity=context;
    }
    public void onBackPressed(){
        if(System.currentTimeMillis()>backKeyPressedTime+2000){
            backKeyPressedTime=System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis()<=backKeyPressedTime+2000){


//            ActivityCompat.finishAffinity(this.activity);
//            System.exit(0);
//            activity.finish();
            toast.cancel();
        }
    }
    public void showGuide(){
        Toast.makeText(activity,"\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show();
        ActivityCompat.finishAffinity(this.activity);
        System.exit(0);
    }
}
