package com.example.neozensoft.internproject;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by neozensoft on 2017-10-10.
 */


public class MyApplication extends Application
{

//    //전역변수가 1개일때때
//   private String mGlobalString;
//
//    public String getGlobalString()
//    {
//        return mGlobalString;
//    }
//
//    public void setGlobalString(String globalString)
//    {
//        this.mGlobalString = globalString;
//    }


    private String globalVariableOne;
    private ArrayList<String> globalArrayList;

    public String getGlobalVariableOne() {
        return globalVariableOne;
    }

    public void setGlobalVariableOne(String globalVariableOne) {
        this.globalVariableOne = globalVariableOne;
    }

    public ArrayList<String> getGlobalArrayList() {
        return globalArrayList;
    }

    public void setGlobalArrayList(ArrayList<String> globalArrayList) {
        this.globalArrayList = globalArrayList;
    }

}