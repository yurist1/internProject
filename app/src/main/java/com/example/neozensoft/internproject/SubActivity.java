package com.example.neozensoft.internproject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.neozensoft.internproject.ExcuteCommunication.EmbodyWebService;
import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import com.example.neozensoft.internproject.model.InfoHospital;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neozensoft on 2017-09-19.
 */

public class SubActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public BackPressCloseHandler backPressCloseHandler;
    private DrawerLayout mDrawer;
    private android.support.v7.app.ActionBarDrawerToggle drawerToggle;
    protected EmbodyWebService ews;
    public static List<InfoHospital> image_details;
    private DBHelper dbHelper;
    //뷰 선언
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    //전역변수 사용
    public MyApplication myApp;
     private TextView nv_idText;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.subactivity);

        myApp=(MyApplication)getApplication();
        final String id=myApp.getGlobalArrayList().get(0);

        // 가로모드
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.canScrollVertically();

        //RecycleView
        image_details = getListData(id);
        recyclerView = (RecyclerView) findViewById(R.id.listAll_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        final ListAllAdapter list_all_adapter=new ListAllAdapter(getApplicationContext(),image_details);
        recyclerView.setAdapter(list_all_adapter);

        //툴바
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //네비게이션 드로어
        mDrawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=(NavigationView)findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);

        //로그인 아이디
        View headerView = navigationView.getHeaderView(0);
        nv_idText=(TextView)headerView.findViewById(R.id.idText1);

        Intent intent1=getIntent();
        String loginID=myApp.getGlobalArrayList().get(1);

        nv_idText.setText(loginID+"님 환영합니다.");

        //데이터 정렬하기(부서명, 모델명, 실사 유무)
        final TextView dept_pos=(TextView)findViewById(R.id.order_dept_pos);
        final TextView equip_model=(TextView)findViewById(R.id.order_equip_model);
        final TextView wd_as_is=(TextView)findViewById(R.id.order_wd_as_is);
        dept_pos.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(dept_pos.getText().equals("부서위치")) {
                    dept_pos.setText("부서위치↑");
                    image_details = getListData_dept_asc(id);
                }
                else if(dept_pos.getText().equals("부서위치↑")){
                    dept_pos.setText("부서위치↓");
                    image_details = getListData_dept_desc(id);
                }else if(dept_pos.getText().equals("부서위치↓")){
                    dept_pos.setText("부서위치↑");
                    image_details = getListData_dept_asc(id);
                }
                recyclerView = (RecyclerView) findViewById(R.id.listAll_recycler);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                ListAllAdapter list_all_adapter = new ListAllAdapter(getApplicationContext(), image_details);
                recyclerView.setAdapter(list_all_adapter);


            }

        });
        equip_model.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(equip_model.getText().equals("모델명")) {
                    equip_model.setText("모델명↑");
                    image_details = getListData_model_asc(id);
                }
                else if(equip_model.getText().equals("모델명↑")){
                    equip_model.setText("모델명↓");
                    image_details = getListData_model_desc(id);
                }else if(equip_model.getText().equals("모델명↓")){
                    equip_model.setText("모델명↑");
                    image_details = getListData_dept_asc(id);
                }
                recyclerView = (RecyclerView) findViewById(R.id.listAll_recycler);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                ListAllAdapter list_all_adapter = new ListAllAdapter(getApplicationContext(), image_details);
                recyclerView.setAdapter(list_all_adapter);
            }

        });
        wd_as_is.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wd_as_is.getText().equals("실사유무")) {
                    wd_as_is.setText("실사유무↑");
                    image_details = getListData_wdAsIs_asc(id);
                }
                else if(wd_as_is.getText().equals("실사유무↑")){
                    wd_as_is.setText("실사유무↓");
                    image_details = getListData_wdAsIs_desc(id);
                }else if(wd_as_is.getText().equals("실사유무↓")){
                    wd_as_is.setText("실사유무↑");
                    image_details = getListData_wdAsIs_asc(id);
                }
                recyclerView = (RecyclerView) findViewById(R.id.listAll_recycler);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                ListAllAdapter list_all_adapter = new ListAllAdapter(getApplicationContext(), image_details);
                recyclerView.setAdapter(list_all_adapter);
            }

        });
    }


    //recycleView get data
    private List<InfoHospital> getListData(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }
    private List<InfoHospital> getListData_dept_asc(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll_orderByDept_asc();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }
    private List<InfoHospital> getListData_dept_desc(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll_orderByDept_desc();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }
    private List<InfoHospital> getListData_model_asc(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll_orderByModel_asc();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }
    private List<InfoHospital> getListData_model_desc(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll_orderByModel_desc();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }
    private List<InfoHospital> getListData_wdAsIs_asc(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll_orderByWdAsIs_asc();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }
    private List<InfoHospital> getListData_wdAsIs_desc(String id) {


        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(SubActivity.this,id,null,1);

        arrResult=dbHelper.selectAll_orderByWdAsIs_desc();

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(), arrResult.get(i)[6].toString());
            list.add(hey);
        }

        return list;
    }

    //navigation menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            backPressCloseHandler=new BackPressCloseHandler(this);
            backPressCloseHandler.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        android.app.FragmentManager manager=getFragmentManager();

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_menu1) {
            // Handle the camera action
//            Intent intent1=getIntent();
//            String HSP_ID= intent1.getExtras().getString("HSP_ID");

            Intent intent=new Intent(getApplicationContext(), SubActivity.class);
//            intent.putExtra("HSP_ID",HSP_ID);
            startActivity(intent);

        } else if (id == R.id.nav_menu2) {
            transaction.replace(R.id.maincontainer, new SearchFragment()).commit();
//             manager.beginTransaction().replace(R.id.maincontainer, new SearchFragment()).commit();
        } else if (id == R.id.nav_menu3) {
//            getFragmentManager().beginTransaction().replace(R.id.maincontainer, new InsertDevice()).commit();
            Intent intent=new Intent(getApplicationContext(), InsertDevice.class);
            startActivity(intent);
        } else if (id == R.id.nav_menu4) {
            Intent intent=new Intent(getApplicationContext(), SendData.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;





    }


}
