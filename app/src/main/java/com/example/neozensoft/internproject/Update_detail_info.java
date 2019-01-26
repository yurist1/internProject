package com.example.neozensoft.internproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import com.example.neozensoft.internproject.model.InfoDetailInfo;
import com.example.neozensoft.internproject.model.InfoHospital;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neozensoft on 2017-09-28.
 */

public class Update_detail_info extends AppCompatActivity {
    private DBHelper dbHelper;
    public MyApplication myApp;
    public static List<InfoDetailInfo> update_data;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.update_detail_info);

        myApp=(MyApplication)getApplication();
        final String id=myApp.getGlobalArrayList().get(0);

        //데이터 가지고 오기
        update_data=getListData();

        //editView
        final TextView equip_nm=(TextView)findViewById(R.id.detail_info_equip_nm);
        final TextView device_model=(TextView)findViewById(R.id.detail_info_device_model);
        final EditText as_is=(EditText)findViewById(R.id.update_detail_as_is);
        final TextView dept_user=(TextView)findViewById(R.id.update_detail_dept_user);
        final TextView equip_pos=(TextView)findViewById(R.id.update_detail_equip_pos);
        final EditText TO_BE=(EditText)findViewById(R.id.update_detail_to_be);
        final EditText WD_AS_IS=(EditText)findViewById(R.id.update_detail_wd_as_is);
        final EditText wd_result=(EditText)findViewById(R.id.update_detail_wd_result);
        final EditText equip_type=(EditText)findViewById(R.id.update_detail_equip_type);
        final EditText equip_option=(EditText)findViewById(R.id.update_detail_equip_option);
        final EditText prod_mgr_nm=(EditText)findViewById(R.id.update_detail_prod_mgr_nm);
        final EditText prod_mgr_phoneno=(EditText)findViewById(R.id.update_detail_prod_mgr_phoneno);
        final EditText dept_mgr_nm=(EditText)findViewById(R.id.update_detail_dept_mgr_nm);
        final EditText dept_mgr_phoneno=(EditText)findViewById(R.id.update_detail_dept_mgr_phoneno);
        final EditText remark=(EditText)findViewById(R.id.update_detail_remark);
        final EditText producer=(EditText)findViewById(R.id.update_detail_producer);

        //button
        Button update=(Button)findViewById(R.id.update_new_info_Btn);
        Button close=(Button)findViewById(R.id.update_closeBtn);

        //radioButton
        final RadioGroup radioGroup_as_is=(RadioGroup)findViewById(R.id.radioGroup_as_is);
        final RadioGroup radioGroup_to_be=(RadioGroup)findViewById(R.id.radioGroup_to_be);
        final RadioGroup radioGroup_wd_as_is=(RadioGroup)findViewById(R.id.radioGroup_wd_as_is);
        final RadioGroup radioGroup_option=(RadioGroup)findViewById(R.id.radioGroup_option);

        //type spinner
        final Spinner spinner_type=(Spinner)findViewById(R.id.spinner_type);
        final ArrayAdapter type_adapter=ArrayAdapter.createFromResource(this,R.array.equip_type,R.layout.support_simple_spinner_dropdown_item);
        spinner_type.setAdapter(type_adapter);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                equip_type.setText(type_adapter.getItem(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //wd_result spinner
        final Spinner spinner_wd_result=(Spinner)findViewById(R.id.spinner_wd_result);
        final ArrayAdapter wd_result_adapter=ArrayAdapter.createFromResource(this,R.array.wd_result,R.layout.support_simple_spinner_dropdown_item);
        spinner_wd_result.setAdapter(wd_result_adapter);
        spinner_wd_result.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                wd_result.setText(wd_result_adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //값 넣기
        equip_nm.setText(update_data.get(0).getEquip_nm());
        device_model.setText(update_data.get(0).getEquip_model());
        dept_user.setText(update_data.get(0).getDept_user());
        equip_pos.setText(update_data.get(0).getEqui_pos());
        as_is.setText(update_data.get(0).getAs_is());
        TO_BE.setText(update_data.get(0).getTo_be());
        WD_AS_IS.setText(update_data.get(0).getWd_as_is());
        wd_result.setText(update_data.get(0).getWd_result());
        remark.setText(update_data.get(0).getRemark());
        equip_type.setText(update_data.get(0).getEquip_type());
        equip_option.setText(update_data.get(0).getEquip_option());
        prod_mgr_nm.setText(update_data.get(0).getProd_mgr_nm());
        prod_mgr_phoneno.setText(update_data.get(0).getProd_mgr_phone());
        dept_mgr_nm.setText(update_data.get(0).getDept_mgr_nm());
        dept_mgr_phoneno.setText(update_data.get(0).getDept_mgr_phone());
        producer.setText(update_data.get(0).getProducer());


        //버튼 이벤트

        update.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                //radio Button
                int as_is_id = radioGroup_as_is.getCheckedRadioButtonId();
                RadioButton as_is_radioButton = (RadioButton) findViewById(as_is_id);
                int to_be_id = radioGroup_to_be.getCheckedRadioButtonId();
                RadioButton to_be_radioButton = (RadioButton) findViewById(to_be_id);
                int wd_as_is_id = radioGroup_wd_as_is.getCheckedRadioButtonId();
                RadioButton wd_as_is_radioButton = (RadioButton) findViewById(wd_as_is_id);
                int option_id = radioGroup_option.getCheckedRadioButtonId();
                RadioButton option_radioButton = (RadioButton) findViewById(option_id);

                if (as_is_id != -1
                        && to_be_id != -1
                        && wd_as_is_id != -1
                        && option_id != -1) {


                    //값 넘길 Array 만들기
                    ArrayList<String> new_info = new ArrayList<String>();
                    new_info.add(equip_nm.getText().toString());
                    new_info.add(device_model.getText().toString());
                    new_info.add(dept_user.getText().toString());
                    new_info.add(equip_pos.getText().toString());
                    new_info.add(as_is_radioButton.getText().toString());
                    new_info.add(to_be_radioButton.getText().toString());
                    new_info.add(wd_as_is_radioButton.getText().toString());
                    new_info.add(wd_result.getText().toString());
                    new_info.add(remark.getText().toString());
                    new_info.add(equip_type.getText().toString());
                    new_info.add(option_radioButton.getText().toString());
                    new_info.add(prod_mgr_nm.getText().toString());
                    new_info.add(prod_mgr_phoneno.getText().toString());
                    new_info.add(dept_mgr_nm.getText().toString());
                    new_info.add(dept_mgr_phoneno.getText().toString());
                    new_info.add(producer.getText().toString());
                    new_info.add(update_data.get(0).getEquip_code());

                    dbHelper = new DBHelper(Update_detail_info.this, id, null, 1);

                    try {
                        dbHelper.update_detail_info(new_info);
                        Toast.makeText(getApplicationContext(), "수정 완료되었습니다.", Toast.LENGTH_SHORT).show();

                        //사진 기록 팝업
                        AlertDialog.Builder builder = new AlertDialog.Builder(Update_detail_info.this);
                        builder.setMessage("사진 기록을 실행하시겠습니까?");
                        builder.setTitle("사진 기록")
                                .setCancelable(false)
                                .setPositiveButton("예", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Intent intent = new Intent(getApplicationContext(), Camera_function.class);
                                        intent.putExtra("equip_model", device_model.getText().toString());
                                        intent.putExtra("equip_code", update_data.get(0).getEquip_code());
                                        startActivity(intent);

                                    }


                                })
                                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                        Intent intent = new Intent(Update_detail_info.this, SubActivity.class);
                                        startActivity(intent);
                                    }

                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("사진 기록");
                        alert.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "수정 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "모든 라디오 버튼을 채오시오", Toast.LENGTH_SHORT).show();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private List<InfoDetailInfo> getListData(){
        final ArrayList<String> detail_info;
        List<InfoDetailInfo> list = new ArrayList<InfoDetailInfo>();
        Intent intent=getIntent();
        detail_info= (ArrayList<String>) intent.getExtras().get("detail_info");

            InfoDetailInfo hey = new InfoDetailInfo(detail_info.get(0).toString(), detail_info.get(1).toString(), detail_info.get(2).toString(), detail_info.get(3).toString(), detail_info.get(4).toString(), detail_info.get(5).toString(), detail_info.get(6).toString()
                    , detail_info.get(7).toString(), detail_info.get(8).toString(), detail_info.get(9).toString(), detail_info.get(10).toString(), detail_info.get(11).toString(), detail_info.get(12).toString(), detail_info.get(13).toString(), detail_info.get(14).toString()
                    , detail_info.get(15).toString(), detail_info.get(16).toString());
            list.add(hey);

        return list;
    }
}
