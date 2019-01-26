package com.example.neozensoft.internproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neozensoft.internproject.ExcuteCommunication.EmbodyWebService;
import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import com.example.neozensoft.internproject.model.InfoGetServerDB;
import com.example.neozensoft.internproject.model.InfoInnerED;
import com.example.neozensoft.internproject.model.InfoInnerEE;
import com.example.neozensoft.internproject.model.InfoInnerEEI;
import com.example.neozensoft.internproject.model.InfoInnerEI;
import com.example.neozensoft.internproject.model.InfoInnerEL;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by neozensoft on 2017-10-24.
 */

public class SendData extends AppCompatActivity {
    public MyApplication myApp;
    public static List<InfoInnerEI> image_data;
    protected EmbodyWebService ews;
    private Context context;
    private DBHelper dbHelper;
    private List<InfoGetServerDB> ServerDB;
    private List<InfoInnerEI> InnerEI;
    private List<InfoInnerED> InnerED;
    private List<InfoInnerEE> InnerEE;
    private List<InfoInnerEEI> InnerEEI;
    private List<InfoInnerEL> InnerEL;
    ArrayList<String[]> inner_ed=new ArrayList<String[]>();
    ArrayList<String[]> inner_ee=new ArrayList<String[]>();
    ArrayList<String[]> inner_eei=new ArrayList<String[]>();
    ArrayList<String[]> inner_ei=new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.send_data);

        myApp=(MyApplication)getApplication();
        final String id=myApp.getGlobalArrayList().get(0).toString();
        final String user=myApp.getGlobalArrayList().get(1).toString();
        final TextView getDB=(TextView)findViewById(R.id.getDB);
        final TextView comparing=(TextView)findViewById(R.id.comparing);
        final TextView send_ed=(TextView)findViewById(R.id.send_ed);
        final TextView send_ee=(TextView)findViewById(R.id.send_ee);
        final TextView send_eei=(TextView)findViewById(R.id.send_eei);
        final TextView send_ei=(TextView)findViewById(R.id.send_ei);
        final Button server_db=(Button)findViewById(R.id.get_server_db);
        final Button update_inner_db=(Button)findViewById(R.id.update_inner_db);
        final Button close=(Button)findViewById(R.id.update_db_close);
        final boolean[] check = {false};

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //서버에서 데이터 가지고 오는 버튼
        server_db.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                ProgressDialog dialog= ProgressDialog.show(SendData.this,"", "정보를 가지고 오는 중입니다.",true);
                check[0] =true;
                getDB.setText("서버 DB 다운로드 중....");
                ServerDB=getListServerData(id);
                server_db.setText("완료");
                server_db.setEnabled(false);
                //무결성하는 방법을 생각해보자!!!!!

                InnerED=getListInnerED(id);
                InnerEE=getListInnerEE(id);
                InnerEI=getListInnerEI(id);
                InnerEEI=getListInnerEEI(id);
                getDB.setText("서버 DB 다운로드 완료");
                comparing.setText("무결성 검사 중");


                //ed 무결성, 덮어씌우기 또는 건너뛰기
                for(int i=0;i<InnerED.size();i++){
                    boolean flag = true;
                    for(int j=0;j<ServerDB.size();j++){
                        if(InnerED.get(i).getDept_loc_nm().equals(ServerDB.get(j).getED_EIM_DEPT_LOC_NM())) {


                            if (ServerDB.get(j).getED_EIM_DEPT_MGR_NM().length() != 0
                                    && ServerDB.get(j).getED_EIM_DEPT_MGR_PHONENO().length() != 0) {

                                    if (ServerDB.get(j).getED_EIM_DEPT_CENTER().equals(InnerED.get(i).getDept_center())
                                            && ServerDB.get(j).getED_EIM_DEPT_MGR_NM().equals(InnerED.get(i).getDept_mgr_nm())
                                            && ServerDB.get(j).getED_EIM_DEPT_MGR_PHONENO().equals(InnerED.get(i).getDept_mgr_phoneno())) {

                                    } else {
                                        if(flag) {
                                            flag = false;
                                            //덮어씌울지 아닐지 알림창
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SendData.this);
                                            builder.setMessage("기존의 데이터와 다른 값이 입력 되어있습니다." +
                                                    "기존의 데이터 \n" +
                                                    "    부서명 : " + ServerDB.get(j).getED_EIM_DEPT_LOC_NM() +
                                                    "\n  부서위치 : " + ServerDB.get(j).getED_EIM_DEPT_CENTER() +
                                                    "\n  부서 담당자 이름 : " + ServerDB.get(j).getED_EIM_DEPT_MGR_NM() +
                                                    "\n  부서 담당자 연락처: " + ServerDB.get(j).getED_EIM_DEPT_MGR_PHONENO() +
                                                    "\n\n  현재 입력한 데이터" +
                                                    "\n    부서명 : " + InnerED.get(i).getDept_loc_nm() +
                                                    "\n  부서위치 : " + InnerED.get(i).getDept_center() +
                                                    "\n  부서 담당자 이름 : " + InnerED.get(i).getDept_mgr_nm() +
                                                    "\n  부서 담당자 연락처: " + InnerED.get(i).getDept_mgr_phoneno() +
                                                    "\n현재 입력한 데이터로 덮어 씌우시겠습니까?");

                                            final InfoInnerED putData = new InfoInnerED(ServerDB.get(j).getED_EIM_DEPT_LOC_NM(), ServerDB.get(j).getED_EIM_DEPT_CENTER(), ServerDB.get(j).getED_EIM_DEPT_MGR_NM(), ServerDB.get(j).getED_EIM_DEPT_MGR_PHONENO());


                                            final int finalI = i;
                                            builder.setTitle("건너뛰기 or 덮어씌우기")
                                                    .setCancelable(false)
                                                    .setPositiveButton("건너뛰기", new DialogInterface.OnClickListener() {

                                                        @Override
                                                        public void onClick(DialogInterface dialog, int i) {
                                                            InnerED.set(finalI,putData);
                                                            dialog.cancel();

                                                        }


                                                    })
                                                    .setNegativeButton("덮어씌우기", new DialogInterface.OnClickListener() {

                                                        @Override
                                                        public void onClick(DialogInterface dialog, int i) {
                                                            dialog.cancel();

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
                }
                //eei 무결성, 덮어씌우기 또는 건너뛰기
                for(int i=0;i<InnerEEI.size();i++){
                    for(int j=0;j<ServerDB.size();j++){
                        if(InnerEEI.get(i).getEquip_code().equals(ServerDB.get(j).getEEI_EIM_EQUIP_CODE())) {

                            if (ServerDB.get(j).getEEI_EIM_AS_IS().length() != 0
                                    && ServerDB.get(j).getEEI_EIM_TO_BE().length() != 0) {

                                if (ServerDB.get(j).getEEI_EIM_AS_IS().equals(InnerEEI.get(i).getAs_is())
                                        && ServerDB.get(j).getEEI_EIM_TO_BE().equals(InnerEEI.get(i).getTo_be())
                                        && ServerDB.get(j).getEEI_EIM_WD_AS_IS().equals(InnerEEI.get(i).getWd_as_is())
                                        && ServerDB.get(j).getEEI_EIM_WD_RESULT().equals(InnerEEI.get(i).getWd_reault())
                                        && ServerDB.get(j).getEEI_EIM_REMARK().equals(InnerEEI.get(i).getRemark())) {

                                } else {

                                        //덮어씌울지 아닐지 알림창
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SendData.this);
                                        builder.setMessage("기존의 데이터와 다른 값이 입력 되어있습니다." +
                                                "기존의 데이터 \n" +
                                                "    모델명 : " + ServerDB.get(j).getEEI_EIM_EQUIP_MODEL() +
                                                "\n  현재연동여부 : " + ServerDB.get(j).getEEI_EIM_AS_IS() +
                                                "\n  차세대연동여부 : " + ServerDB.get(j).getEEI_EIM_TO_BE() +
                                                "\n  실사여부 : " + ServerDB.get(j).getEEI_EIM_WD_AS_IS() +
                                                "\n  실사결과 : " + ServerDB.get(j).getEEI_EIM_WD_RESULT() +
                                                "\n  비고 : " + ServerDB.get(j).getEEI_EIM_REMARK() +
                                                "\n\n   현재 입력한 데이터 \n" +
                                                "\n    모델명 : " + InnerEEI.get(i).getEquip_model() +
                                                "\n  현재연동여부 : " + InnerEEI.get(i).getAs_is() +
                                                "\n  차세대연동여부 : " + InnerEEI.get(i).getTo_be() +
                                                "\n  실사여부 : " + InnerEEI.get(i).getWd_as_is() +
                                                "\n  실사결과 : " + InnerEEI.get(i).getWd_reault() +
                                                "\n  비고 : " + InnerEEI.get(i).getRemark() +
                                                "\n\n 현재 입력한 데이터로 덮어 씌우시겠습니까?"+
                                                "\n덮어씌우기를 하는 경우, 아래에 사유를 기재해주세요.");
                                    final EditText et=new EditText(SendData.this);
                                    et.setImeOptions(EditorInfo.IME_ACTION_DONE);
                                    et.setInputType(InputType.TYPE_CLASS_TEXT);
                                    builder.setView(et);
                                        final int finalI = i;
                                        final InfoInnerEEI putData = new InfoInnerEEI(ServerDB.get(j).getEEI_EIM_EQUIP_CODE(),ServerDB.get(j).getEEI_EIM_DEPT_USER(),ServerDB.get(j).getEEI_EIM_EQUIP_POS(),ServerDB.get(j).getEEI_EIM_EQUIP_MODEL(),ServerDB.get(j).getEEI_EIM_AS_IS(), ServerDB.get(j).getEEI_EIM_TO_BE(), ServerDB.get(j).getEEI_EIM_WD_AS_IS(), ServerDB.get(j).getEEI_EIM_WD_RESULT(), ServerDB.get(j).getEEI_EIM_REMARK());


                                    final int finalJ = j;
                                    builder.setTitle("건너뛰기 or 덮어씌우기")
                                                .setCancelable(false)
                                                .setPositiveButton("건너뛰기", new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int i) {
                                                        InnerEEI.set(finalI,putData);
                                                        dialog.cancel();

                                                    }


                                                })
                                                .setNegativeButton("덮어씌우기", new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int i) {
                                                        String reson=et.getText().toString();
                                                        dbHelper.delete_reason(reson, user, InnerEEI.get(finalI).getEquip_code());//사유
                                                          dialog.cancel();


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
                //ee 무결성, 덮어씌우기 또는 건너뛰기
                for(int i=0;i<InnerEE.size();i++){
                    boolean flag = true;
                    for(int j=0;j<ServerDB.size();j++){
                        if(InnerEE.get(i).getEquip_model().equals(ServerDB.get(j).getEE_EIM_EQUIP_MODEL())) {

                            if (ServerDB.get(j).getEE_EIM_EQUIP_TYPE().length() != 0
                                    && ServerDB.get(j).getEE_EIM_EQUIP_OPTION().length() != 0) {

                                if (ServerDB.get(j).getEE_EIM_EQUIP_TYPE().equals(InnerEE.get(i).getEquip_type())
                                        && ServerDB.get(j).getEE_EIM_EQUIP_OPTION().equals(InnerEE.get(i).getEquip_option())
                                        && ServerDB.get(j).getEE_EIM_PROD_MGR_NM().equals(InnerEE.get(i).getProd_mgr_nm())
                                        && ServerDB.get(j).getEE_EIM_PROD_MGR_PHONENO().equals(InnerEE.get(i).getProd_mgr_phoneno())) {

                                } else {
                                    if(flag) {
                                        flag = false;
                                        //덮어씌울지 아닐지 알림창
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SendData.this);
                                        builder.setMessage("기존의 데이터와 다른 값이 입력 되어있습니다." +
                                                "기존의 데이터 \n" +
                                                "    기기명 : " + ServerDB.get(j).getEE_EIM_EQUIP_NM().toString() +
                                                "\n  기기 한글명 : " + ServerDB.get(j).getEE_EIM_EQUIP_KR_NM().toString() +
                                                "\n  모델명 : " + ServerDB.get(j).getEE_EIM_EQUIP_MODEL().toString() +
                                                "\n  제조원 : " + ServerDB.get(j).getEE_EIM_EQUIP_PRODUCER().toString() +
                                                "\n   타입 : " + ServerDB.get(j).getEE_EIM_EQUIP_TYPE().toString() +
                                                "\n  옵션 : " + ServerDB.get(j).getEE_EIM_EQUIP_OPTION().toString() +
                                                "\n  판매업체담당자 : " + ServerDB.get(j).getEE_EIM_PROD_MGR_NM().toString() +
                                                "\n  판매업체담당자 연락처: " + ServerDB.get(j).getEE_EIM_PROD_MGR_PHONENO().toString() +
                                                "\n\n 현재입력 데이터 " +
                                                "\n    기기명 : " + InnerEE.get(i).getEquip_nm() +
                                                "\n  기기 한글명 : " + InnerEE.get(i).getEquip_kr_nm() +
                                                "\n  모델명 : " + InnerEE.get(i).getEquip_model() +
                                                "\n  제조원 : " + InnerEE.get(i).getEquip_producer() +
                                                "\n   타입 : " + InnerEE.get(i).getEquip_option() +
                                                "\n  옵션 : " + InnerEE.get(i).getEquip_option() +
                                                "\n  판매업체담당자 : " + InnerEE.get(i).getProd_mgr_nm() +
                                                "\n  판매업체담당자 연락처: " + InnerEE.get(i).getProd_mgr_phoneno() +
                                                "\n현재 입력한 데이터로 덮어 씌우시겠습니까?");
                                        final int finalI = i;
                                        final InfoInnerEE putData = new InfoInnerEE(ServerDB.get(j).getEE_EIM_EQUIP_NM(),ServerDB.get(j).getEE_EIM_EQUIP_KR_NM(),ServerDB.get(j).getEE_EIM_EQUIP_MODEL(),ServerDB.get(j).getEE_EIM_EQUIP_PRODUCER(),ServerDB.get(j).getEE_EIM_EQUIP_TYPE(), ServerDB.get(j).getEE_EIM_EQUIP_OPTION(), ServerDB.get(j).getEE_EIM_PROD_MGR_NM(), ServerDB.get(j).getEE_EIM_PROD_MGR_PHONENO());


                                        final int finalJ = j;
                                        builder.setTitle("건너뛰기 or 덮어씌우기")
                                                .setCancelable(false)
                                                .setPositiveButton("건너뛰기", new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int i) {
                                                        InnerEE.set(finalI,putData);
                                                        dialog.cancel();
                                                    }


                                                })
                                                .setNegativeButton("덮어씌우기", new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int i) {
                                                        dialog.cancel();

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
                }
                comparing.setText("무결성 검사 완료");

            }


        });
        update_inner_db.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (check[0]) {
                    update_inner_db.setText("완료");
                    update_inner_db.setEnabled(false);

                    //ed 업데이트
                    send_ed.setText("EIM_DEPARTMENT 전송 중...");
                    for (int i = 0; i < InnerED.size(); i++) {
                        ArrayList<String> update_ed = new ArrayList<String>();
                        update_ed.add(id);
                        update_ed.add(InnerED.get(i).getDept_loc_nm());
                        update_ed.add(InnerED.get(i).getDept_center());
                        update_ed.add(InnerED.get(i).getDept_mgr_nm());
                        update_ed.add(InnerED.get(i).getDept_mgr_phoneno());
                        try {
                            ews.embodyWebService(update_ed, "PKG_PROCEDURE_INTERN_CYR.EIM_UPDATE_INNER_DB_ED", "Package", "EMR", 0);
                            send_ed.setText("EIM_DEPARTMENT 전송 중...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    send_ed.setText("EIM_DEPARTMENT 전송 완료");
                    //eei 업데이트
                    send_eei.setText("EIM_EQUIP_INFO 전송 중...");
                    for (int i = 0; i < InnerEEI.size(); i++) {
                        ArrayList<String> update_eei = new ArrayList<String>();
                        update_eei.add(InnerEEI.get(i).getEquip_code());
                        update_eei.add(InnerEEI.get(i).getAs_is());
                        update_eei.add(InnerEEI.get(i).getTo_be());
                        update_eei.add(InnerEEI.get(i).getWd_as_is());
                        update_eei.add(InnerEEI.get(i).getWd_reault());
                        update_eei.add(InnerEEI.get(i).getRemark());
                        update_eei.add(InnerEEI.get(i).getDept_user());
                        update_eei.add(InnerEEI.get(i).getEquip_pos());
                        update_eei.add(InnerEEI.get(i).getEquip_model());
                        update_eei.add(id);
                        try {
                            ews.embodyWebService(update_eei, "PKG_PROCEDURE_INTERN_CYR.EIM_UPDATE_INNER_DB_EEI", "Package", "EMR", 0);
                            send_eei.setText("EIM_EQUIP_INFO 전송 중...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    send_eei.setText("EIM_EQUIP_INFO 전송 완료");
                    //ee 업데이트
                    send_ee.setText("EIM_EQUIPMENT 전송 중...");
                    for (int i = 0; i < InnerEE.size(); i++) {
                        ArrayList<String> update_ee = new ArrayList<String>();
                        update_ee.add(InnerEE.get(i).getEquip_model());
                        update_ee.add(InnerEE.get(i).getEquip_type());
                        update_ee.add(InnerEE.get(i).getEquip_option());
                        update_ee.add(InnerEE.get(i).getProd_mgr_nm());
                        update_ee.add(InnerEE.get(i).getProd_mgr_phoneno());
                        update_ee.add(InnerEE.get(i).getEquip_nm());
                        update_ee.add(InnerEE.get(i).getEquip_kr_nm());
                        update_ee.add(InnerEE.get(i).getEquip_producer());
                        try {
                            ews.embodyWebService(update_ee, "PKG_PROCEDURE_INTERN_CYR.EIM_UPDATE_INNER_DB_EE", "Package", "EMR", 0);
                            send_ee.setText("EIM_EQUIPMENT 전송 중...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    send_ee.setText("EIM_EQUIPMENT 전송 완료");
                    //ei 업데이트
                    send_ei.setText("EIM_IMAGE 전송 중...");
                    for (int i = 0; i < InnerEI.size(); i++) {
                        ArrayList<String> update_ei = new ArrayList<String>();
                        update_ei.add(InnerEI.get(i).getEquip_code());
                        update_ei.add(InnerEI.get(i).getEquip_model());
                        update_ei.add(InnerEI.get(i).getImg_1());
                        update_ei.add(InnerEI.get(i).getImg_2());
                        update_ei.add(InnerEI.get(i).getImg_3());
                        update_ei.add(InnerEI.get(i).getImg_4());
                        update_ei.add(InnerEI.get(i).getImg_5());
                        update_ei.add(InnerEI.get(i).getImg_6());
                        try {
                            ews.embodyWebService(update_ei, "PKG_PROCEDURE_INTERN_CYR.EIM_UPDATE_INNER_DB_EI", "Package", "EMR", 0);
                            send_ei.setText("EIM_IMAGE 전송 중...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    send_ei.setText("EIM_IMAGE 전송 완료");
                    //el 업데이트
                    InnerEL = getListInnerEL(id);
                    for (int i = 0; i < InnerEL.size(); i++) {
                        ArrayList<String> update_el = new ArrayList<String>();
                        update_el.add(InnerEL.get(i).getUser_id());
                        update_el.add(InnerEL.get(i).getReason());
                        update_el.add(InnerEL.get(i).getUpdate_date());
                        update_el.add(InnerEL.get(i).getEquip_code());
                        try {
                            ews.embodyWebService(update_el, "PKG_PROCEDURE_INTERN_CYR.EIM_UPDATE_INNER_DB_EL", "Package", "EMR", 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"데이터 받기를 먼저 실행하시오",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private List<InfoInnerED> getListInnerED(String id) {
        dbHelper = new DBHelper(SendData.this, id, null, 1);
        ArrayList<String[]> inner_ed=new ArrayList<String[]>();
        List<InfoInnerED> list=new ArrayList<InfoInnerED>();
        inner_ed=dbHelper.get_inner_DB_ed();
        for(int i=0;i<inner_ed.size();i++){
            InfoInnerED hey=new InfoInnerED(inner_ed.get(i)[0],inner_ed.get(i)[1],inner_ed.get(i)[2],inner_ed.get(i)[3]);
            list.add(hey);
        }
        return list;
    }
    private List<InfoInnerEE> getListInnerEE(String id) {
        dbHelper = new DBHelper(SendData.this, id, null, 1);
        ArrayList<String[]> inner_ee=new ArrayList<String[]>();
        List<InfoInnerEE> list=new ArrayList<InfoInnerEE>();
        inner_ee=dbHelper.get_inner_DB_ee();
        for(int i=0;i<inner_ee.size();i++){
            InfoInnerEE hey=new InfoInnerEE(inner_ee.get(i)[0],inner_ee.get(i)[1],inner_ee.get(i)[2],inner_ee.get(i)[3],inner_ee.get(i)[4],inner_ee.get(i)[5],inner_ee.get(i)[6],inner_ee.get(i)[7]);
            list.add(hey);
        }
        return list;
    }
    private List<InfoInnerEEI> getListInnerEEI(String id) {
        dbHelper = new DBHelper(SendData.this, id, null, 1);
        ArrayList<String[]> inner_eei=new ArrayList<String[]>();
        List<InfoInnerEEI> list=new ArrayList<InfoInnerEEI>();
        inner_eei=dbHelper.get_inner_DB_eei();
        for(int i=0;i<inner_eei.size();i++){
            InfoInnerEEI hey=new InfoInnerEEI(inner_eei.get(i)[0],inner_eei.get(i)[1],inner_eei.get(i)[2],inner_eei.get(i)[3],inner_eei.get(i)[4],inner_eei.get(i)[5],inner_eei.get(i)[6],inner_eei.get(i)[7],inner_eei.get(i)[8]);
            list.add(hey);
        }
        return list;
    }
    private List<InfoInnerEI> getListInnerEI(String id) {
        dbHelper = new DBHelper(SendData.this, id, null, 1);
        ArrayList<String[]> inner_ei=new ArrayList<String[]>();
        List<InfoInnerEI> list=new ArrayList<InfoInnerEI>();
        inner_ei=dbHelper.get_inner_DB_ei();
        for(int i=0;i<inner_ei.size();i++){
            InfoInnerEI hey=new InfoInnerEI(inner_ei.get(i)[0],inner_ei.get(i)[1],inner_ei.get(i)[2],inner_ei.get(i)[3],inner_ei.get(i)[4],inner_ei.get(i)[5],inner_ei.get(i)[6],inner_ei.get(i)[7]);
            list.add(hey);
        }
        return list;
    }
    private List<InfoInnerEL> getListInnerEL(String id) {
        dbHelper = new DBHelper(SendData.this, id, null, 1);
        ArrayList<String[]> inner_el=new ArrayList<String[]>();
        List<InfoInnerEL> list=new ArrayList<InfoInnerEL>();
        inner_el=dbHelper.get_inner_DB_el();
        for(int i=0;i<inner_el.size();i++){
            InfoInnerEL hey=new InfoInnerEL(inner_el.get(i)[0],inner_el.get(i)[1],inner_el.get(i)[2],inner_el.get(i)[3]);
            list.add(hey);
        }
        return list;
    }

    private List<InfoGetServerDB> getListServerData(String id) {
        ArrayList<String[]> arrResult = new ArrayList<String[]>();
        ArrayList<String> arrList = new ArrayList<String>();
        List<InfoGetServerDB> list=new ArrayList<InfoGetServerDB>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 웹서비스스레드
        StrictMode.setThreadPolicy(policy);
        ews = new EmbodyWebService(context);
        arrList.add(id);
        try {
            arrResult = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_GET_SERVER_DATA", "Package", "EMR", 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<arrResult.size();i++){
            InfoGetServerDB hey=new InfoGetServerDB(arrResult.get(i)[0].toString(),arrResult.get(i)[1].toString(),arrResult.get(i)[2].toString(),arrResult.get(i)[3].toString(),arrResult.get(i)[4].toString(),arrResult.get(i)[5].toString(),
                    arrResult.get(i)[6].toString(),arrResult.get(i)[7].toString(),arrResult.get(i)[8].toString(),arrResult.get(i)[9].toString(),arrResult.get(i)[10].toString(),arrResult.get(i)[11].toString(),arrResult.get(i)[12].toString(),
                    arrResult.get(i)[13].toString(),arrResult.get(i)[14].toString(),arrResult.get(i)[15].toString(),arrResult.get(i)[16].toString(),arrResult.get(i)[17].toString(),arrResult.get(i)[18].toString(),arrResult.get(i)[19].toString(),
                    arrResult.get(i)[20].toString(),arrResult.get(i)[21].toString(),arrResult.get(i)[22].toString(),arrResult.get(i)[23].toString(),arrResult.get(i)[24].toString(),arrResult.get(i)[25].toString(),arrResult.get(i)[26].toString(),
                    arrResult.get(i)[27].toString(),arrResult.get(i)[28].toString());

            list.add(hey);
        }
        return list;
    }
}
