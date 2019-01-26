package com.example.neozensoft.internproject;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import java.util.ArrayList;

import static android.R.*;


/**
 * Created by neozensoft on 2017-09-20.
 */

public class SearchFragment extends Fragment {

    ArrayList items_dept_center;
    ArrayList items_dept_user;
    ArrayList items_equip_model;
    private DBHelper dbHelper;
    private  Context context = this.getContext();
    public MyApplication myApp;
    InputMethodManager imm;


//extends Fragment 일떄
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState){
        View v;

        myApp=(MyApplication)getActivity().getApplication();
        final String id=myApp.getGlobalArrayList().get(0);

        items_dept_center=getData_dept_center(id);
        items_equip_model=getData_equip_model(id);




        v=inflater.inflate(R.layout.search,container,false);
        final AutoCompleteTextView dept_center=(AutoCompleteTextView)v.findViewById(R.id.search_dept_center);
        final AutoCompleteTextView dept_user=(AutoCompleteTextView)v.findViewById(R.id.search_dept_user);
        final AutoCompleteTextView equip_model=(AutoCompleteTextView)v.findViewById(R.id.equip_model);
        final Button search_by_dept=(Button)v.findViewById(R.id.search_by_dept);
        final Button search_by_model=(Button)v.findViewById(R.id.search_by_model);



        //자동 완성
        dept_center.setAdapter(new ArrayAdapter<String>(this.getContext(), layout.simple_dropdown_item_1line,items_dept_center));
        equip_model.setAdapter(new ArrayAdapter<String>(this.getContext(), layout.simple_dropdown_item_1line,items_equip_model));
        dept_center.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    dept_center.showDropDown();
                }
            }


        });
        dept_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String get_dept_center_nm=dept_center.getText().toString();
                items_dept_user=getData_dept_user(get_dept_center_nm, id);
                dept_user.setAdapter(new ArrayAdapter<String>(getContext(), layout.simple_dropdown_item_1line,items_dept_user));
                if (b) {
                    dept_user.showDropDown();
                }
            }


        });
        equip_model.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    equip_model.showDropDown();
                    //키보드
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                }
            }


        });


        //검색하기 버튼 이벤트

        //부서위치와 위치부서로 검색하기

            search_by_dept.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    String get_dept_center = dept_center.getText().toString();
                    String get_dept_user = dept_user.getText().toString();

                    if(get_dept_center.length()!=0
                            && get_dept_user.length()!=0) {

                            Search_list_by_dept_fragment fragment = new Search_list_by_dept_fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("dept_center", get_dept_center);
                            bundle.putString("dept_user", get_dept_user);
                            fragment.setArguments(bundle);

                            getFragmentManager().beginTransaction().replace(R.id.maincontainer, fragment).commit();

                    }else{
                        Toast.makeText(getContext(),"빈칸을 채우시오",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        //모델명으로 검색하기
        search_by_model.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String get_equip_model=equip_model.getText().toString();

                if(get_equip_model.length()!=0) {
                    Search_list_by_model_fragment fragment = new Search_list_by_model_fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("equip_model", get_equip_model);
                    fragment.setArguments(bundle);

                    imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    getFragmentManager().beginTransaction().replace(R.id.maincontainer, fragment).commit();
                }else{
                    Toast.makeText(getContext(),"빈칸을 채우시오",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }



    private ArrayList getData_dept_center(String id){
        ArrayList<String[]> arrResult;
        ArrayList list=new ArrayList();
        dbHelper=new DBHelper(this.getContext(),id,null,1);
        arrResult=dbHelper.dept_center_items();

        for(int i=0; i<arrResult.size(); i++) {
            String hey = arrResult.get(i)[0].toString();
            list.add(hey);
        }

        return list;
    }
    private ArrayList getData_dept_user(String get_dept_center_nm, String id){
        ArrayList<String[]> arrResult;
        ArrayList list=new ArrayList();

        dbHelper=new DBHelper(this.getContext(),id,null,1);
        arrResult=dbHelper.dept_user_items(get_dept_center_nm);

        for(int i=0; i<arrResult.size(); i++) {
            String hey = arrResult.get(i)[0].toString();
            list.add(hey);
        }

        return list;
    }
    private ArrayList getData_equip_model(String id){
        ArrayList<String[]> arrResult;
        ArrayList list=new ArrayList();
        dbHelper=new DBHelper(this.getContext(),id,null,1);
        arrResult=dbHelper.dept_equip_model();

        for(int i=0; i<arrResult.size(); i++) {
            String hey = arrResult.get(i)[0].toString();
            list.add(hey);
        }

        return list;
    }

    }
