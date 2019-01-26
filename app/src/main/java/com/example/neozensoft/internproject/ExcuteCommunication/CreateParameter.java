package com.example.neozensoft.internproject.ExcuteCommunication;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * @author Woong-Jae
 *
 */
public class CreateParameter extends CommJSONCall
{

//    private ArrayList<Object> totalReturn;

//    private ArrayList<String[]> totalReturn2;

    private JSONObject obj;

    private String totalObj;

    public CreateParameter(Context activity)
    {
        super(activity);
    }

    /**
     * com.neozensoft.neoconsent 작성자 : wjKim
     * * 수정자: yrchoi
     * 수정 일자 : 2017. 11. 03.오전 11:19:44
     * 명 : GetParamReturn
     * 리턴 타입 : JSONObject
     * 설명 : get쿼리 파라미터 생성
     *
     * @param arrParams
     * @param QID
     * @param QTYPE
     * @param curser
     * @return
     * @throws JSONException
     */
    public JSONObject GetParamReturn(ArrayList<String> arrParams, String QID, String QTYPE, String  USERID, int curser) throws JSONException
    {
        int i = 0;
        obj = new JSONObject();

        obj.put("QID", QID);
        obj.put("QTYPE", QTYPE);
        obj.put("USERID", USERID);
        obj.put("EXECTYPE", "FILL");
        obj.put("TABLENAME", "");

        if (arrParams != null)
        {
            for (i = 0; i < arrParams.size(); i++)
            {
                if (i > 9)
                {
                    obj.put("P" + i, arrParams.get(i));
                }
                else
                {
                    obj.put("P0" + i, arrParams.get(i));
                }

            }
        }
        if (curser == 1) // out 1개 일때
        {

            if (i > 9)
            {
                obj.put("P" + i, "");

            }
            else
            {
                obj.put("P0" + i, "");
            }
        }
        else if (curser == 2) // out 2개 일때 ??언제 2개 쓰지???

        {

            if (i > 9)
            {
                obj.put("P" + i, "");
            }
            else
            {
                obj.put("P0" + i, "");
            }
            i++;
            if (i > 9)
            {
                obj.put("P" + i, "");
            }
            else
            {
                obj.put("P0" + i, "");
            }
        }

        return obj;
    }

}
