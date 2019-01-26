package com.example.neozensoft.internproject;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neozensoft.internproject.localDBconnection.DBHelper;

import java.util.ArrayList;


/**
 * Created by neozensoft on 2017-09-21.
 */

public class InsertDevice extends AppCompatActivity {
    private DBHelper dbHelper;
    public MyApplication myApp;
    private Context context;
    int EC_checked=0;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
//        View v;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.insert_device);

        myApp=(MyApplication)getApplication();
        final String id=myApp.getGlobalArrayList().get(0).toString();

//        v=inflater.inflate(R.layout.insert_device,container,false);


        final EditText insert_equip_code=(EditText)findViewById(R.id.insert_equip_code);
        final EditText insert_dept_user=(EditText)findViewById(R.id.insert_dept_user);
        final EditText insert_equip_pos=(EditText)findViewById(R.id.insert_equip_pos);
        final EditText insert_equip_nm=(EditText)findViewById(R.id.insert_equip_nm);
        final EditText insert_equip_model=(EditText)findViewById(R.id.insert_equip_model);
        final EditText insert_equip_kr_nm=(EditText)findViewById(R.id.insert_equip_kr_nm);
        final EditText insert_equip_producer=(EditText)findViewById(R.id.insert_equip_producer);
        final EditText insert_dept_center=(EditText)findViewById(R.id.insert_dept_center);

        Button insertBtn=(Button)findViewById(R.id.insert_btn);
        Button closeBtn=(Button)findViewById(R.id.insert_close_btn);
        Button checkEC=(Button)findViewById(R.id.insert_check_equip_code);


        insertBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //중복체크
                if(EC_checked==1) {
                    if (insert_equip_code.getText().length() != 0
                            && insert_equip_code.getText().length() != 0
                            && insert_dept_user.getText().length() != 0
                            && insert_equip_pos.getText().length() != 0
                            && insert_equip_nm.getText().length() != 0
                            && insert_equip_model.getText().length() != 0
                            && insert_equip_kr_nm.getText().length() != 0
                            && insert_equip_producer.getText().length() != 0
                            && insert_dept_center.getText().length() != 0) {

                        ArrayList<String> insert_info = new ArrayList<String>();
                        insert_info.add(insert_equip_code.getText().toString());
                        insert_info.add(insert_dept_user.getText().toString());
                        insert_info.add(insert_equip_pos.getText().toString());
                        insert_info.add(insert_equip_nm.getText().toString());
                        insert_info.add(insert_equip_model.getText().toString());
                        insert_info.add(insert_equip_kr_nm.getText().toString());
                        insert_info.add(insert_equip_producer.getText().toString());
                        insert_info.add(insert_dept_center.getText().toString());


                        try {
                            dbHelper = new DBHelper(context, id, null, 1);
                            dbHelper.insert_device(insert_info);

                            Toast.makeText(getApplicationContext(), "새 기기 등록 완료", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "새 기기 등록 실패", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(getApplicationContext(),"빈칸을 채우십시오.",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"자산코드 중복확인 하십시오",Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkEC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //중복체크
                ArrayList<String[]> arrResult=new ArrayList<String[]>();
                dbHelper=new DBHelper(context,id,null,1);
                arrResult=dbHelper.checkEquipCode(insert_equip_code.getText().toString());
                if(insert_equip_code.length()!=0) {
                    if (arrResult.get(0)[0].equals("0")) {
                        EC_checked = 1;
                        Toast.makeText(getApplicationContext(), "사용 가능한 자산코드입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "이미 존재하는 자산코드입니다.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "자산관리코드를 입력하시오", Toast.LENGTH_SHORT).show();
                }


            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}
