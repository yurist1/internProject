package com.example.neozensoft.internproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.neozensoft.internproject.ExcuteCommunication.EmbodyWebService;
import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import com.example.neozensoft.internproject.model.InfoDetailInfo;
import com.example.neozensoft.internproject.model.InfoHospital;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by neozensoft on 2017-09-21.
 */

public class DetailInfo extends AppCompatActivity {
    private DBHelper dbHelper;
    Context context;
    public MyApplication myApp;
    public static List<InfoDetailInfo> detailInfo;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.detail_info);

        //global variable
        myApp = (MyApplication) getApplication();
        String id = myApp.getGlobalArrayList().get(0);
        final String user = myApp.getGlobalArrayList().get(1);

        //TextView 선언
        TextView equip_nm = (TextView) findViewById(R.id.detail_info_equip_nm);
        TextView device_model = (TextView) findViewById(R.id.detail_info_device_model);
        TextView as_is = (TextView) findViewById(R.id.detail_as_is);
        TextView dept_user = (TextView) findViewById(R.id.detail_dept_user);
        TextView equip_pos = (TextView) findViewById(R.id.detail_equip_pos);
        TextView TO_BE = (TextView) findViewById(R.id.detail_to_be);
        TextView WD_AS_IS = (TextView) findViewById(R.id.detail_wd_as_is);
        TextView wd_result = (TextView) findViewById(R.id.detail_wd_result);
        TextView equip_type = (TextView) findViewById(R.id.detail_equip_type);
        TextView equip_option = (TextView) findViewById(R.id.detail_equip_option);
        TextView prod_mgr_nm = (TextView) findViewById(R.id.detail_prod_mgr_nm);
        TextView prod_mgr_phoneno = (TextView) findViewById(R.id.detail_prod_mgr_phoneno);
        TextView dept_mgr_nm = (TextView) findViewById(R.id.detail_dept_mgr_nm);
        TextView dept_mgr_phoneno = (TextView) findViewById(R.id.detail_dept_mgr_phoneno);
        TextView remark = (TextView) findViewById(R.id.detail_remark);
        TextView producer = (TextView) findViewById(R.id.detail_producer);

        //Button 선언
        Button update = (Button) findViewById(R.id.updateBtn);
        Button delete = (Button) findViewById(R.id.deleteBtn);
        Button close = (Button) findViewById(R.id.closeBtn);

        //장비코드 가지고 오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("Object");

        //서비스 타기(해당 모델 값 가지고 오기)
        detailInfo = getListData(id,data);

        equip_nm.setText(detailInfo.get(0).getEquip_nm());
        device_model.setText(detailInfo.get(0).getEquip_model());
        dept_user.setText(detailInfo.get(0).getDept_user());
        equip_pos.setText(detailInfo.get(0).getEqui_pos());
        as_is.setText(detailInfo.get(0).getAs_is());
        TO_BE.setText(detailInfo.get(0).getTo_be());
        WD_AS_IS.setText(detailInfo.get(0).getWd_as_is());
        wd_result.setText(detailInfo.get(0).getWd_result());
        equip_type.setText(detailInfo.get(0).getEquip_type());
        equip_option.setText(detailInfo.get(0).getEquip_option());
        prod_mgr_nm.setText(detailInfo.get(0).getProd_mgr_nm());
        prod_mgr_phoneno.setText(detailInfo.get(0).getProd_mgr_phone());
        dept_mgr_nm.setText(detailInfo.get(0).getDept_mgr_nm());
        dept_mgr_phoneno.setText(detailInfo.get(0).getDept_mgr_phone());
        remark.setText(detailInfo.get(0).getRemark());
        producer.setText(detailInfo.get(0).getProducer());

        //update_detail_info로 넘길 데이터 Array 만들기
        final ArrayList<String> detail_info = new ArrayList<>();
        detail_info.add(detailInfo.get(0).getEquip_nm());
        detail_info.add(detailInfo.get(0).getEquip_model());
        detail_info.add(detailInfo.get(0).getDept_user());
        detail_info.add(detailInfo.get(0).getEqui_pos());
        detail_info.add(detailInfo.get(0).getAs_is());
        detail_info.add(detailInfo.get(0).getTo_be());
        detail_info.add(detailInfo.get(0).getWd_as_is());
        detail_info.add(detailInfo.get(0).getWd_result());
        detail_info.add(detailInfo.get(0).getRemark());
        detail_info.add(detailInfo.get(0).getEquip_type());
        detail_info.add(detailInfo.get(0).getEquip_option());
        detail_info.add(detailInfo.get(0).getProd_mgr_nm());
        detail_info.add(detailInfo.get(0).getProd_mgr_phone());
        detail_info.add(detailInfo.get(0).getDept_mgr_nm());
        detail_info.add(detailInfo.get(0).getDept_mgr_phone());
        detail_info.add(detailInfo.get(0).getProducer());
        detail_info.add(detailInfo.get(0).getEquip_code());

        ///수정하기 버튼 누를시
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Update_detail_info.class);
                intent.putExtra("detail_info", detail_info);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //팝업 다이얼로그(삭제하기)
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailInfo.this);
                builder.setMessage(detailInfo.get(0).getEquip_model() + "에 대한 데이터를 삭제하시겠습니까?");
                builder.setTitle("데이터 삭제하기")
                        .setCancelable(false)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //삭제 사유
                                AlertDialog.Builder builder = new AlertDialog.Builder(DetailInfo.this);
                                builder.setMessage(detailInfo.get(0).getEquip_model() + "에 대한 데이터를 초기화 하는 사유를 적으시오");
                                final EditText et = new EditText(DetailInfo.this);
                                builder.setView(et);

                                builder.setTitle("데이터 삭제 사유")
                                        .setCancelable(false)
                                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                String reson = et.getText().toString();
                                                dbHelper.delete_detail_info(detail_info);//데이터 삭제하기
                                                dbHelper.delete_reason(reson, user, detailInfo.get(0).getEquip_code());//사유

                                                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                                                startActivity(intent);

                                            }


                                        })
                                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                dialog.cancel();
                                            }

                                        });
                                AlertDialog alert = builder.create();
                                alert.setTitle("데이터 삭제하기");
                                alert.show();
//                                dbHelper.delete_detail_info(detail_info);//데이터 삭제하기

//                                Intent intent=new Intent(getApplicationContext(),SubActivity.class);
//                                startActivity(intent);

                            }


                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }

                        });
                AlertDialog alert = builder.create();
                alert.setTitle("데이터 삭제하기");
                alert.show();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private List<InfoDetailInfo> getListData(String id, String data) {
        ArrayList<String[]> arrResult;
        List<InfoDetailInfo> list = new ArrayList<InfoDetailInfo>();
        dbHelper=new DBHelper(DetailInfo.this,id,null,1);

        arrResult = dbHelper.detail_info(data);

        for(int i=0; i<arrResult.size(); i++) {
            InfoDetailInfo hey = new InfoDetailInfo(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString()
                    , arrResult.get(i)[7].toString(), arrResult.get(i)[8].toString(), arrResult.get(i)[9].toString(), arrResult.get(i)[10].toString(), arrResult.get(i)[11].toString(), arrResult.get(i)[12].toString(), arrResult.get(i)[13].toString(), arrResult.get(i)[14].toString()
                    , arrResult.get(i)[15].toString(), arrResult.get(i)[16].toString());
            list.add(hey);
        }

        return list;
    }
}