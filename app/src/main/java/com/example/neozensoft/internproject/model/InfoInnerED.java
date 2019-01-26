package com.example.neozensoft.internproject.model;

/**
 * Created by neozensoft on 2017-10-30.
 */

public class InfoInnerED {
    public String getDept_loc_nm() {
        return dept_loc_nm;
    }

    public void setDept_loc_nm(String dept_loc_nm) {
        this.dept_loc_nm = dept_loc_nm;
    }

    public String getDept_center() {
        return dept_center;
    }

    public void setDept_center(String dept_center) {
        this.dept_center = dept_center;
    }

    public String getDept_mgr_nm() {
        return dept_mgr_nm;
    }

    public void setDept_mgr_nm(String dept_mgr_nm) {
        this.dept_mgr_nm = dept_mgr_nm;
    }

    public String getDept_mgr_phoneno() {
        return dept_mgr_phoneno;
    }

    public void setDept_mgr_phoneno(String dept_mgr_phoneno) {
        this.dept_mgr_phoneno = dept_mgr_phoneno;
    }

    public InfoInnerED(String dept_loc_nm, String dept_center, String dept_mgr_nm, String dept_mgr_phoneno) {
        this.dept_loc_nm = dept_loc_nm;
        this.dept_center = dept_center;
        this.dept_mgr_nm = dept_mgr_nm;
        this.dept_mgr_phoneno = dept_mgr_phoneno;
    }

    private String dept_loc_nm;
    private String dept_center;
    private String dept_mgr_nm;
    private String dept_mgr_phoneno;
}
