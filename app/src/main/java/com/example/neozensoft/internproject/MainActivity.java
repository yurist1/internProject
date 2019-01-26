package com.example.neozensoft.internproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Application;

import com.example.neozensoft.internproject.ExcuteCommunication.EmbodyWebService;
import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import com.example.neozensoft.internproject.model.InfoDetailInfo;
import com.example.neozensoft.internproject.model.InfoHospital;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    public MyApplication myApp;
    ArrayList items_hsp_id;
    protected EmbodyWebService ews;
    private Context context;
    String userID;
    String userPwd;
    String hspID;
    ArrayList checkID;
    ArrayList checkPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //병원 코드정보 가지고 오기
        items_hsp_id=getData_hsp_id();

        final EditText idText=(EditText)findViewById(R.id.idText);
        final EditText pwdText=(EditText)findViewById(R.id.pwdText);
        final AutoCompleteTextView hsp_id=(AutoCompleteTextView)findViewById(R.id.hsp_idText);
        final Button loginBtn=(Button)findViewById(R.id.loginBtn);
        //병원 코드 자동완성
        hsp_id.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,items_hsp_id));
        hsp_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    hsp_id.showDropDown();
                }
            }


        });

        myApp=new MyApplication();
        myApp= (MyApplication) this.getApplicationContext();

        //로그인 버튼 클릭시
        loginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //로그인
                userID = idText.getText().toString();
                checkID=getData_user_ID();
                userPwd = pwdText.getText().toString();
                checkPwd = getData_user_pwd(userID);
                hspID=hsp_id.getText().toString();

                int log=0; //로그인 확인을 위해서

                //전역변수 선언하기
                ArrayList<String> myAppList=new ArrayList<String>();
                myAppList.add(hsp_id.getText().toString());
                myAppList.add(userID);

            try{
                for(int i=0; i<checkID.size();i++){
                    if(userID.equals(checkID.get(i))){
                        if (userPwd.equals(checkPwd.get(0).toString())){
                            for(int j = 0; j<items_hsp_id.size(); j++) {
                                if (hspID.equals(items_hsp_id.get(j))) {
                                    //전역변수 한개일때
//                            myApp.setGlobalString(hsp_id.getText().toString());
                                    myApp.setGlobalArrayList(myAppList);

                                    //해당 병원 데이터만 테이블 생성하기
                                    dbHelper = new DBHelper(MainActivity.this, myApp.getGlobalArrayList().get(0), null, 1);
                                    dbHelper.getDatabaseName();
                                    dbHelper.getWritableDatabase();
                                    dbHelper.createDB();

                                    log = 1; //로그인 완료

                                    //데이터 업데이트 할지 팝업
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage(myApp.getGlobalArrayList().get(0) + "에 대한 데이터를 새로 받으시겠습니까?");
                                    builder.setTitle("데이터 새로 받기")
                                            .setCancelable(false)
                                            .setPositiveButton("예", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int i) {
                                                    dbHelper.getDB(myApp.getGlobalArrayList().get(0).toString());//데이터 새로 가지고 오기

                                                    Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                                                    intent.putExtra("idText", userID);
                                                    intent.putExtra("sysdate", Calendar.getInstance().getTime().toString());
                                                    startActivity(intent);

                                                }


                                            })
                                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int i) {
                                                    dialog.cancel();
                                                    Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                                                    intent.putExtra("idText", userID);
                                                    intent.putExtra("sysdate", Calendar.getInstance().getTime().toString());
                                                    startActivity(intent);
                                                }

                                            });
                                    AlertDialog alert = builder.create();
                                    alert.setTitle("데이터 새로 받기");
                                    alert.show();
                                }
                            }
                        }
                    }
                }
                if(log==0){
                    //로그인 실패 팝업 (아이디 및 비번 오류)
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("아이디, 비밀번호 또는 병원코드가 잘못 입력되었습니다. 다시 시도하시오. ");
                    builder.setTitle("로그인 실패")
                            .setCancelable(false)
                            .setNegativeButton("닫기", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }

                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("로그인 실패");
                    alert.show();
                }
            } catch(Exception e){
                        e.printStackTrace();
                //로그인 실패 팝업 (병원코드 공백)
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("병원코드 빈칸을 채우시오");
                builder.setTitle("로그인 오류")
                        .setCancelable(false)
                        .setNegativeButton("닫기", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }

                        });
                AlertDialog alert = builder.create();
                alert.setTitle("로그인 오류");
                alert.show();
            }
            }
        });
    }
    private ArrayList getData_hsp_id(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 웹서비스스레드
        StrictMode.setThreadPolicy(policy);
        ews= new EmbodyWebService(context);
        ArrayList<String> arrList=new ArrayList<String>();
        ArrayList<String[]> arrResult=new ArrayList<String[]>();
        ArrayList<String> list=new ArrayList<String>();
        try {
            arrResult=ews.embodyWebService(arrList,"PKG_PROCEDURE_INTERN_CYR.EIM_GET_HSP_ID", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<arrResult.size();i++){
            list.add(arrResult.get(i)[0].toString());
        }


        return list;
    }
    private ArrayList getData_user_ID(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 웹서비스스레드
        StrictMode.setThreadPolicy(policy);
        ews= new EmbodyWebService(context);
        ArrayList<String> arrList=new ArrayList<String>();
        ArrayList<String[]> arrResult=new ArrayList<String[]>();
        ArrayList<String> list=new ArrayList<String>();
        try {
            arrResult=ews.embodyWebService(arrList,"PKG_PROCEDURE_INTERN_CYR.EIM_GET_USER_ID", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<arrResult.size();i++){
            list.add(arrResult.get(i)[0].toString());
        }


        return list;
    }
    private ArrayList getData_user_pwd(String user_id){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 웹서비스스레드
        StrictMode.setThreadPolicy(policy);
        ews= new EmbodyWebService(context);
        ArrayList<String> arrList=new ArrayList<String>();
        ArrayList<String[]> arrResult=new ArrayList<String[]>();
        ArrayList<String> list=new ArrayList<String>();
        arrList.add(user_id);
        try {
            arrResult=ews.embodyWebService(arrList,"PKG_PROCEDURE_INTERN_CYR.EIM_GET_USER_PWD", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<arrResult.size();i++){
            list.add(arrResult.get(i)[0].toString());
        }


        return list;
    }


}
