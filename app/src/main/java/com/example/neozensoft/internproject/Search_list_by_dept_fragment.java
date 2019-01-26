package com.example.neozensoft.internproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neozensoft.internproject.localDBconnection.DBHelper;
import com.example.neozensoft.internproject.model.InfoHospital;

import java.util.ArrayList;
import java.util.List;

import static android.R.*;

/**
 * Created by neozensoft on 2017-10-10.
 */

public class Search_list_by_dept_fragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public static List<InfoHospital> data_by_dept;
    private DBHelper dbHelper;
    public MyApplication myApp;

//    Bundle bundle=this.getArguments();
//    String dept_center=bundle.getString("dept_center");
//    String dept_user=bundle.getString("dept_user");


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View v;
        Bundle bundle=this.getArguments();
        final String dept_center=bundle.getString("dept_center");
        final String dept_user=bundle.getString("dept_user");
        v=inflater.inflate(R.layout.search_list_by_dept,container,false);

        //global variable
        myApp=(MyApplication)this.getActivity().getApplication();
        String id=myApp.getGlobalArrayList().get(0);

        //가로모드
        linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.canScrollVertically();


        //ListView
        data_by_dept = getListData(dept_center,dept_user,id);
        recyclerView = (RecyclerView) v.findViewById(R.id.search_dept_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListAllAdapter list_all_adapter=new ListAllAdapter(this.getContext(),data_by_dept);
        recyclerView.setAdapter(list_all_adapter);




        return v;
    }
    private List<InfoHospital> getListData(String dept_center,String dept_user,String id){
        ArrayList<String[]> arrResult;
        List<InfoHospital> list = new ArrayList<InfoHospital>();
        dbHelper=new DBHelper(this.getContext(),id,null,1);

        arrResult=dbHelper.select_by_dept(dept_center,dept_user);

        for(int i=0; i<arrResult.size(); i++) {
            InfoHospital hey = new InfoHospital(arrResult.get(i)[0].toString(), arrResult.get(i)[1].toString(), arrResult.get(i)[2].toString(), arrResult.get(i)[3].toString(), arrResult.get(i)[4].toString(), arrResult.get(i)[5].toString(),arrResult.get(i)[7].toString());
            list.add(hey);
        }
        return list;
    }
}
