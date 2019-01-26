package com.example.neozensoft.internproject.model;

/**
 * Created by neozensoft on 2017-10-30.
 */

public class InfoInnerEE {
    public String getEquip_nm() {
        return equip_nm;
    }

    public void setEquip_nm(String equip_nm) {
        this.equip_nm = equip_nm;
    }

    public String getEquip_kr_nm() {
        return equip_kr_nm;
    }

    public void setEquip_kr_nm(String equip_kr_nm) {
        this.equip_kr_nm = equip_kr_nm;
    }

    public String getEquip_model() {
        return equip_model;
    }

    public void setEquip_model(String equip_model) {
        this.equip_model = equip_model;
    }

    public String getEquip_producer() {
        return equip_producer;
    }

    public void setEquip_producer(String equip_producer) {
        this.equip_producer = equip_producer;
    }

    public String getEquip_type() {
        return equip_type;
    }

    public void setEquip_type(String equip_type) {
        this.equip_type = equip_type;
    }

    public String getEquip_option() {
        return equip_option;
    }

    public void setEquip_option(String equip_option) {
        this.equip_option = equip_option;
    }

    public String getProd_mgr_nm() {
        return prod_mgr_nm;
    }

    public void setProd_mgr_nm(String prod_mgr_nm) {
        this.prod_mgr_nm = prod_mgr_nm;
    }

    public String getProd_mgr_phoneno() {
        return prod_mgr_phoneno;
    }

    public void setProd_mgr_phoneno(String prod_mgr_phoneno) {
        this.prod_mgr_phoneno = prod_mgr_phoneno;
    }

    public InfoInnerEE(String equip_nm, String equip_kr_nm, String equip_model, String equip_producer, String equip_type, String equip_option, String prod_mgr_nm, String prod_mgr_phoneno) {
        this.equip_nm = equip_nm;
        this.equip_kr_nm = equip_kr_nm;
        this.equip_model = equip_model;
        this.equip_producer = equip_producer;
        this.equip_type = equip_type;
        this.equip_option = equip_option;
        this.prod_mgr_nm = prod_mgr_nm;
        this.prod_mgr_phoneno = prod_mgr_phoneno;
    }

    private String equip_nm;
    private String equip_kr_nm;
    private String equip_model;
    private String equip_producer;
    private String equip_type;
    private String equip_option;
    private String prod_mgr_nm;
    private String prod_mgr_phoneno;
}
