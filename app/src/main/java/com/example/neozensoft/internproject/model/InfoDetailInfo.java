package com.example.neozensoft.internproject.model;

/**
 * Created by neozensoft on 2017-09-25.
 */

public class InfoDetailInfo {
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

    public String getDept_user() {
        return dept_user;
    }

    public void setDept_user(String dept_user) {
        this.dept_user = dept_user;
    }

    public String getEqui_pos() {
        return equi_pos;
    }

    public void setEqui_pos(String equi_pos) {
        this.equi_pos = equi_pos;
    }

    public String getAs_is() {
        return as_is;
    }

    public void setAs_is(String as_is) {
        this.as_is = as_is;
    }

    public String getTo_be() {
        return to_be;
    }

    public void setTo_be(String to_be) {
        this.to_be = to_be;
    }

    public String getWd_as_is() {
        return wd_as_is;
    }

    public void setWd_as_is(String wd_as_is) {
        this.wd_as_is = wd_as_is;
    }

    public String getWd_result() {
        return wd_result;
    }

    public void setWd_result(String wd_result) {
        this.wd_result = wd_result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getProd_mgr_phone() {
        return prod_mgr_phone;
    }

    public void setProd_mgr_phone(String prod_mgr_phone) {
        this.prod_mgr_phone = prod_mgr_phone;
    }

    public String getDept_mgr_nm() {
        return dept_mgr_nm;
    }

    public void setDept_mgr_nm(String dept_mgr_nm) {
        this.dept_mgr_nm = dept_mgr_nm;
    }

    public String getDept_mgr_phone() {
        return dept_mgr_phone;
    }

    public void setDept_mgr_phone(String dept_mgr_phone) {
        this.dept_mgr_phone = dept_mgr_phone;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public InfoDetailInfo(String equip_nm, String equip_model, String dept_user, String equi_pos, String as_is, String to_be, String wd_as_is, String wd_result, String remark, String equip_type, String equip_option, String prod_mgr_nm, String prod_mgr_phone, String dept_mgr_nm, String dept_mgr_phone, String producer, String equip_code) {
        this.equip_nm = equip_nm;
        this.equip_model = equip_model;
        this.dept_user = dept_user;
        this.equi_pos = equi_pos;
        this.as_is = as_is;
        this.to_be = to_be;
        this.wd_as_is = wd_as_is;
        this.wd_result = wd_result;
        this.remark = remark;
        this.equip_type = equip_type;
        this.equip_option = equip_option;
        this.prod_mgr_nm = prod_mgr_nm;
        this.prod_mgr_phone = prod_mgr_phone;
        this.dept_mgr_nm = dept_mgr_nm;
        this.dept_mgr_phone = dept_mgr_phone;
        this.producer = producer;
        this.equip_code = equip_code;
    }

    private String equip_nm;
    private String equip_model;
    private String dept_user;
    private String equi_pos;
    private String as_is;
    private String to_be;
    private String wd_as_is;
    private String wd_result;
    private String remark;
    private String equip_type;
    private String equip_option;
    private String prod_mgr_nm;
    private String prod_mgr_phone;
    private String dept_mgr_nm;
    private String dept_mgr_phone;
    private String producer;
    private String equip_code;

    public String getEquip_code() {
        return equip_code;
    }

    public void setEquip_code(String equip_code) {
        this.equip_code = equip_code;
    }
}
