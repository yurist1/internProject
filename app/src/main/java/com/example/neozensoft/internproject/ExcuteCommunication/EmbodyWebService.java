package com.example.neozensoft.internproject.ExcuteCommunication;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class EmbodyWebService extends CreateParameter// extends Application
{
    public EmbodyWebService(Context activity)
    {
        super(activity);
    }

    /**
     * com.neozensoft.neoconsent 작성자 : wjKim
     * 수정자: yrchoi
     * 수정 일자 : 2017. 11. 03.오전 11:19:44
     * 메소드 명 : embodyWebService
     * 리턴 타입 : ArrayList<String[]>
     * 설명 : CreateParameter와 CommonJsonCall을 묶어 쿼리를 호출하는 함수
     *
     * @param arrParams 파라미터 리스트
     * @param QID 쿼리 아이디
     * @param QTYPE 쿼리 타입(select, package 등)
     * @param curser 커서 유무(0:없음, 1:있음, 2:2개있음)
     * @return
     * @throws Exception
     */
    public ArrayList<String[]> embodyWebService(ArrayList<String> arrParams, String QID, String QTYPE, String USERID, int curser)
            throws Exception
    {
        Log.e("---------호출------------", "embodyWebService");
        return JSONCall(GetParamReturn(arrParams, QID, QTYPE, USERID, curser));
    }

}
