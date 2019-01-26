package com.example.neozensoft.internproject.ExcuteCommunication;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class CommJSONCall
{
    protected static final String DB_ERROR_ACTION = "SERVICE_CALL_ERROR";
    private static final String NAMESPACE = "http://tempuri.org/";
    public static String URL = "http://192.168.100.224:8080/intern_cyr/intern_cyr.svc"; //  서비스 타기
//    public static String URL = "http://192.168.100.204:8080/WCFService_Template/WCFService_Template.svc"; //  네오젠 LOCAL DB
    private static final String METHOD_NAME = "ServiceReturnCustomType";
    private static final String SOAP_ACTION = "http://tempuri.org/IWCFService/ServiceReturnCustomType"; // 네오젠 local


    private String strReturn;

    private final int REQEUST_TIMEOUT = 60000;

    private SoapObject request;

    private SoapSerializationEnvelope envelope;

    private PropertyInfo propInfo;

    private String sReqParamReal;

    private JSONArray jsonArr;

    private ArrayList<String[]> resultJson;

    boolean flag = true;

    public static Context mContext;

    public CommJSONCall(Context activity)
    {
        mContext=activity;
    }

    /*
     * @author wjkim
     * * 수정자: yrchoi
     * 수정 일자 : 2017. 11. 03.오전 11:19:44
     * @see CommJSONCall#JSONCall(JSONObject)
     * @see 웹 서비스 호출
     * @param obj
     * @return
     * @throws Exception
     */

    public ArrayList<String[]> JSONCall(JSONObject obj) throws Exception
    {
        try
        {
            // wifiCheckDialog(isWifi2);

            request = new SoapObject(NAMESPACE, METHOD_NAME);
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            // SETQUERY OR GETQUERY
            propInfo = new PropertyInfo();
            propInfo.name = "sType";
            propInfo.setValue("GETQUERY");
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty(propInfo);
            // JSON, XML, SPLIT
            // INPUT PARAMETER TPYE
            propInfo = new PropertyInfo();
            propInfo.name = "sInputType";
            propInfo.setValue("JSON");
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty(propInfo);

            // JSON, XML, SPLIT
            // OUTPUT DATA TYPE
            propInfo = new PropertyInfo();
            propInfo.name = "sOutputType";
            propInfo.setValue("JSON");
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty(propInfo);

            // 암호화 여부 Y OR N
            propInfo = new PropertyInfo();
            propInfo.name = "sEncYn";
            propInfo.setValue("N"); //Y로 하면 앱을 시작하지 않음. 왜 ???
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty(propInfo);


            // PARAMETER
            propInfo = new PropertyInfo();
            propInfo.name = "sParam";
            propInfo.setValue(obj.toString());
            propInfo.type = PropertyInfo.STRING_CLASS;
            request.addProperty(propInfo);

            Log.e("request-", request.toString());
            Log.e("SOAP_ACTION- ", SOAP_ACTION.toString());

            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(URL, REQEUST_TIMEOUT);

            httpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive resultRequestSOAP = (SoapPrimitive) envelope.getResponse();
            if (resultRequestSOAP == null)
            {
                return null;
            }

            try
            {
//                jsonArr = new JSONObject(resultRequestSOAP.toString()).getJSONArray("Table00"); // OUTCURSOR 없이 Varchar로 받을때
                jsonArr = new JSONObject(resultRequestSOAP.toString()).getJSONArray("Table0");

                if (jsonArr == null)
                {
                    jsonArr = new JSONObject(resultRequestSOAP.toString()).getJSONArray("Table");
                }
            }
            catch (Exception ex1)
            {
                Log.e("ty.park", "ty.park >>> CommJSONCall DB Error Detail: " + resultRequestSOAP.toString());
            }
            if (jsonArr == null)
                return null;

            resultJson = new ArrayList<String[]>();

            for (int i = 0; i < jsonArr.length(); i++)
            {
                ArrayList<String> itemArr = new ArrayList<String>();

                for (int j = 0; j < jsonArr.getJSONArray(i).length(); j++)
                {
                    itemArr.add(jsonArr.getJSONArray(i).get(j).toString());
                }

                String[] itemResult = itemArr.toArray(new String[itemArr.size()]);

                for (int k = 0; k < itemArr.size(); k++)
                {
                    if (itemArr.get(k).equals("null"))
                    {
                        itemResult[k] = "";
                    }
                    else
                    {
                        itemResult[k] = itemArr.get(k);
                    }
                }
                resultJson.add(itemResult);
            }

            if (resultJson.size() == 0)
            {
                return resultJson;
            }

            for (int i = 0; i < resultJson.get(0).length; i++)
            {
//                Log.e("resultJson-----------------" + i, resultJson.get(0)[i].toString());
                // logger.error(resultJson.get(0)[i].toString());
            }
            // if(resultJson==null)
            // {
            // return null;
            // }
            if (resultJson.get(0).length == 0)
            {
                return resultJson = new ArrayList<String[]>();
            }
            return resultJson; // 웹서비스 Result
        }
        catch (Exception e)
        {
            resultJson = new ArrayList<String[]>();
            throw e;
        }
    }

//    public ArrayList<String[]> JSONCall2(JSONObject obj)
//    {
//        try
//        {
//            // if (isSign) { //전자서명 값에 따라 메소드 달라짐
//            // request = new SoapObject(NAMESPACE, METHOD_NAME);
//            // } else if (!isSign) {
//            request = new SoapObject(NAMESPACE, METHOD_NAME);
//            // }
//            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//
//            // SETQUERY OR GETQUERY
//            propInfo = new PropertyInfo();
//            propInfo.name = "sType";
//            propInfo.setValue("SETQUERY");
//            propInfo.type = PropertyInfo.STRING_CLASS;
//            request.addProperty(propInfo);
//
//            // JSON, XML, SPLIT
//            // INPUT PARAMETER TPYE
//            propInfo = new PropertyInfo();
//            propInfo.name = "sInputType";
//            propInfo.setValue("JSON");
//            propInfo.type = PropertyInfo.STRING_CLASS;
//            request.addProperty(propInfo);
//
//            // JSON, XML, SPLIT
//            // OUTPUT DATA TYPE
//            propInfo = new PropertyInfo();
//            propInfo.name = "sOutputType";
//            propInfo.setValue("JSON");
//            propInfo.type = PropertyInfo.STRING_CLASS;
//            request.addProperty(propInfo);
//
//            // 암호화 여부 Y OR N
//            propInfo = new PropertyInfo();
//            propInfo.name = "sEncYn";
//            propInfo.setValue("N");
//            propInfo.type = PropertyInfo.STRING_CLASS;
//            request.addProperty(propInfo);
//
//            // sReqParamReal = Enc.encrypt(obj.toString());
//
//            // PARAMETER
//            propInfo = new PropertyInfo();
//            propInfo.name = "sParam";
//            propInfo.setValue(obj.toString());
//            propInfo.type = PropertyInfo.STRING_CLASS;
//            request.addProperty(propInfo);
//
////            Log.e("request-----------------", request.toString());
////            Log.e("SOAP_ACTION-----------------", SOAP_ACTION.toString());
//
//            envelope.dotNet = true;
//            envelope.setOutputSoapObject(request);
//
//            HttpTransportSE httpTransport = new HttpTransportSE(URL, REQEUST_TIMEOUT);
//
//            httpTransport.call(SOAP_ACTION, envelope);
//
//            SoapPrimitive resultRequestSOAP = (SoapPrimitive) envelope.getResponse();
//            if (resultRequestSOAP == null)
//            {
//                return null;
//            }
//            try
//            {
//                for (int i = 0; i < resultRequestSOAP.getAttributeCount(); i++)
//                {
//                    jsonArr.put(new JSONObject(resultRequestSOAP.toString()).getJSONArray("Table" + i));
//                }
//
//                if (jsonArr == null)
//                {
//                    jsonArr = new JSONObject(resultRequestSOAP.toString()).getJSONArray("Table");
//                }
//            }
//            catch (Exception ex1)
//            {
//                try
//                {
//                    // jsonArr = new
//                    // JSONObject(Enc.decrypt(receivedEnc)).getJSONArray("T_MESSAGE");
//                }
//                catch (Exception ex2)
//                {
//                    // jsonArr = new
//                    // JSONObject(Enc.decrypt(receivedEnc)).getJSONArray("F_MESSAGE");
//                }
//            }
//
//            if (jsonArr == null)
//                return null;
//
//            resultJson = new ArrayList<String[]>();
//
//            for (int i = 0; i < jsonArr.length(); i++)
//            {
//                ArrayList<String> itemArr = new ArrayList<String>();
//
//                for (int j = 0; j < jsonArr.getJSONArray(i).length(); j++)
//                {
//                    itemArr.add(jsonArr.getJSONArray(i).get(j).toString());
//                }
//
//                String[] itemResult = itemArr.toArray(new String[itemArr.size()]);
//
//                for (int k = 0; k < itemArr.size(); k++)
//                {
//                    if (itemArr.get(k).equals("null"))
//                    {
//                        itemResult[k] = "";
//                    }
//                    else
//                    {
//                        itemResult[k] = itemArr.get(k);
//                    }
//                }
//                resultJson.add(itemResult);
//            }
//
//            return resultJson;
//        }
//        catch (Exception e)
//        {
//            resultJson = new ArrayList<String[]>();
//        }
//
//        return resultJson;
//    }
}
