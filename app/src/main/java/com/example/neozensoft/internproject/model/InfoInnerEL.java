package com.example.neozensoft.internproject.model;

import java.util.Date;

/**
 * Created by neozensoft on 2017-10-31.
 */

public class InfoInnerEL {

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getEquip_code() {
        return equip_code;
    }

    public void setEquip_code(String equip_code) {
        this.equip_code = equip_code;
    }

    public InfoInnerEL(String user_id, String reason, String update_date, String equip_code) {
        this.user_id = user_id;
        this.reason = reason;
        this.update_date = update_date;
        this.equip_code = equip_code;
    }

    private String user_id;
    private String reason;
    private String update_date;
    private String equip_code;
}
