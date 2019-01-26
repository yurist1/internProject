package com.example.neozensoft.internproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neozensoft.internproject.ExcuteCommunication.EmbodyWebService;
import com.example.neozensoft.internproject.model.InfoHospital;

import java.util.List;

/**
 * Created by neozensoft on 2017-09-21.
 */

public class ListAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InfoHospital> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private Button btn;




    protected EmbodyWebService ews; // 웹서비스

    public ListAllAdapter(Context aContext,  List<InfoHospital> listData){
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final InfoHospital item = listData.get(position);

        final RecyclerViewHolder recyclerViewHolder = ((RecyclerViewHolder)holder);
        recyclerViewHolder.dept_center.setText(item.getDepart_center());
        recyclerViewHolder.equip_nm.setText(item.getEquip_nm());
        recyclerViewHolder.equip_model.setText(item.getEquip_model());
        recyclerViewHolder.AS_IS.setText(item.getAS_IS());
        recyclerViewHolder.TO_BE.setText(item.getTO_BE());
        recyclerViewHolder.WD_AS_IS.setText(item.getWD_AS_IS());
        recyclerViewHolder.EQUIP_CODE.setText(item.getEquip_code());



        recyclerViewHolder.equip_model.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailInfo.class);
                intent.putExtra("Object",recyclerViewHolder.EQUIP_CODE.getText());
//                intent.putExtra("Object1",recyclerViewHolder.HSP_ID.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listData.size();
    }
    private InfoHospital getItem(int position){
        return listData.get(position);
    }



    // 커스텀 뷰 홀더
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView dept_center;
        TextView equip_nm;
        TextView equip_model;
        TextView AS_IS;
        TextView TO_BE;
        TextView WD_AS_IS;
        TextView EQUIP_CODE;


        public RecyclerViewHolder(View itemView) {
            super(itemView);


            dept_center = (TextView) itemView.findViewById(R.id.dept_center);
            equip_nm = (TextView) itemView.findViewById(R.id.equip_nm);
            equip_model = (TextView) itemView.findViewById(R.id.equip_model);
            AS_IS = (TextView) itemView.findViewById(R.id.AS_IS);
            TO_BE = (TextView) itemView.findViewById(R.id.TO_BE);
            WD_AS_IS = (TextView) itemView.findViewById(R.id.WD_AS_IS);
            EQUIP_CODE=(TextView) itemView.findViewById(R.id.EQUIP_CODE);

        }
    }

}
