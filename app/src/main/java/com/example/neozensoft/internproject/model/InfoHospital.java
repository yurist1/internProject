package com.example.neozensoft.internproject.model;

import android.icu.text.IDNA;

/**
 * Created by neozensoft on 2017-09-21.
 */

public class InfoHospital {


    private String equip_nm;
    private String equip_model;
    private String equip_type;
    private String AS_IS;
    private String TO_BE;
    private String WD_AS_IS;
    private String depart_center;

    public String getEquip_code() {
        return equip_code;
    }

    public void setEquip_code(String equip_code) {
        this.equip_code = equip_code;
    }

    private String equip_code;






    public String getDepart_center() {
        return depart_center;
    }

    public void setDepart_center(String depart_center) {
        this.depart_center = depart_center;
    }

    public InfoHospital(){
        this.depart_center = "";
        this.equip_nm = "";
        this.equip_model = "";
        this.equip_type = "";
        this.AS_IS = "";
        this.TO_BE = "";
        this.WD_AS_IS = "";

    }

    public InfoHospital(String depart_center, String equip_nm, String equip_model, String AS_IS, String TO_BE, String WD_AS_IS, String equip_code) {
        this.depart_center = depart_center;
        this.equip_nm = equip_nm;
        this.equip_model = equip_model;
        this.AS_IS = AS_IS;
        this.TO_BE = TO_BE;
        this.WD_AS_IS = WD_AS_IS;
        this.equip_code=equip_code;

    }

    public String getEquip_nm() {
        return equip_nm;
    }

    public void setEquip_nm(String equip_nm) {
        this.equip_nm = equip_nm;
    }

    public String getEquip_model() {
        return equip_model;
    }

    public void setEquip_model(String equip_model) {
        this.equip_model = equip_model;
    }

    public String getEquip_type() {
        return equip_type;
    }

    public void setEquip_type(String equip_type) {
        this.equip_type = equip_type;
    }

    public String getAS_IS() {
        return AS_IS;
    }

    public void setAS_IS(String AS_IS) {
        this.AS_IS = AS_IS;
    }

    public String getTO_BE() {
        return TO_BE;
    }

    public void setTO_BE(String TO_BE) {
        this.TO_BE = TO_BE;
    }

    public String getWD_AS_IS() {
        return WD_AS_IS;
    }

    public void setWD_AS_IS(String WD_AS_IS) {
        this.WD_AS_IS = WD_AS_IS;
    }
}
