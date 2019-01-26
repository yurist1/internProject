package com.example.neozensoft.internproject.model;

/**
 * Created by neozensoft on 2017-10-30.
 */

public class InfoInnerEEI {
    public String getEquip_code() {
        return equip_code;
    }

    public void setEquip_code(String equip_code) {
        this.equip_code = equip_code;
    }

    public String getDept_user() {
        return dept_user;
    }

    public void setDept_user(String dept_user) {
        this.dept_user = dept_user;
    }

    public String getEquip_pos() {
        return equip_pos;
    }

    public void setEquip_pos(String equip_pos) {
        this.equip_pos = equip_pos;
    }

    public String getEquip_model() {
        return equip_model;
    }

    public void setEquip_model(String equip_model) {
        this.equip_model = equip_model;
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

    public String getWd_reault() {
        return wd_reault;
    }

    public void setWd_reault(String wd_reault) {
        this.wd_reault = wd_reault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public InfoInnerEEI(String equip_code, String dept_user, String equip_pos, String equip_model, String as_is, String to_be, String wd_as_is, String wd_reault, String remark) {
        this.equip_code = equip_code;
        this.dept_user = dept_user;
        this.equip_pos = equip_pos;
        this.equip_model = equip_model;
        this.as_is = as_is;
        this.to_be = to_be;
        this.wd_as_is = wd_as_is;
        this.wd_reault = wd_reault;
        this.remark = remark;
    }

    private String equip_code;
    private String dept_user;
    private String equip_pos;
    private String equip_model;
    private String as_is;
    private String to_be;
    private String wd_as_is;
    private String wd_reault;
    private String remark;
}
