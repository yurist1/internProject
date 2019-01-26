package com.example.neozensoft.internproject.localDBconnection;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.widget.Toast;
import com.example.neozensoft.internproject.ExcuteCommunication.EmbodyWebService;
import com.example.neozensoft.internproject.MainActivity;
import com.example.neozensoft.internproject.model.InfoDetailInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DBHelper extends SQLiteOpenHelper {
    protected EmbodyWebService ews;
    SQLiteDatabase db;
    MainActivity mainActivity = new MainActivity();
    String test = "";
    private static String DB_PATH = "/data/data/com.example.neozensoft.internproject/databases/";


    private static String DB_NAME = "cd ";

    private Context context;
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        test = name;
    }
    /**
     * Database가 존재하지 않을 때, 딱 한번 실행된다.
     * * DB를 만드는 역할을 한다.
     * * @param db */

    @Override public void onCreate(SQLiteDatabase db) {

    }


    /** * Application의 버전이 올라가서
     * * Table 구조가 변경되었을 때 실행된다.
     * * @param db * @param oldVersion
     * * @param newVersion */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }


    public void createDB(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 웹서비스스레드
        StrictMode.setThreadPolicy(policy);

////////////////////////////////////////////////////////////////////////////////////////////////////
//        ArrayList<ArrayList<String[]>> arrReasultAll=new ArrayList<ArrayList<String[]>>();
//        arrReasultAll.add(new ArrayList<String[]>());
//        arrReasultAll.add(new ArrayList<String[]>());
//        arrReasultAll.add(new ArrayList<String[]>());
//        arrReasultAll.add(new ArrayList<String[]>());
//
//        if(arrReasultAll.size()>4)
//        {
//            arrReasultAll=new ArrayList<ArrayList<String[]>>();
//        }
//        ResultInit(arrReasultAll);

////////////////////////////////////////////////////////////////////////////////////////////////////


        ArrayList<String[]> arrReasult_ed=new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_ee=new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_eei=new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_ei = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_el = new ArrayList<String[]>();


        ArrayList<String> arrList=new ArrayList<String>();
        ArrayList<String> col_nm_ed=new ArrayList<String>();
        ArrayList<String> col_nm_ee=new ArrayList<String>();
        ArrayList<String> col_nm_eei=new ArrayList<String>();
        ArrayList<String> col_nm_ei=new ArrayList<String>();
        ArrayList<String> col_nm_el=new ArrayList<String>();

//        //path 지정
//        File dbfile = new File("mydatabase.db" );
//        try{
//            String myPath = DB_PATH + DB_NAME;
//            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//
////            db = SQLiteDatabase.openOrCreateDatabase("mydatabase.db",null);
//        }catch (Exception e){
//            e.printStackTrace() ;
//        }

        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor aaaa=db.rawQuery("SELECT * FROM sqlite_master WHERE TYPE='table';",null);
        int check=aaaa.getCount();


        //test <code>
        if(check==1) {

            ews = new EmbodyWebService(context);
            try {
                arrReasult_ed = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_DEPARTMENT_COLUM_LIST_ALL", "Package", "EMR", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                arrReasult_ee = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_EQUIPMENT_COLUM_LIST_ALL", "Package", "EMR", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                arrReasult_eei = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_EQUIP_INFO_COLUM_LIST_ALL", "Package", "EMR", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                arrReasult_ei = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_IMAGE_COLUM_LIST_ALL", "Package", "EMR", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                arrReasult_el = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_LOG_COLUM_LIST_ALL", "Package", "EMR", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < arrReasult_ed.size(); i++) {
                col_nm_ed.add(arrReasult_ed.get(i)[2].toString());

            }
            for (int i = 0; i < arrReasult_ee.size(); i++) {
                col_nm_ee.add(arrReasult_ee.get(i)[2].toString());

            }
            for (int i = 0; i < arrReasult_eei.size(); i++) {
                col_nm_eei.add(arrReasult_eei.get(i)[2].toString());

            }
            for (int i = 0; i < arrReasult_ei.size(); i++) {
                col_nm_ei.add(arrReasult_ei.get(i)[2].toString());

            }
            for (int i = 0; i < arrReasult_el.size(); i++) {
                col_nm_el.add(arrReasult_el.get(i)[2].toString());

            }

            StringBuffer sb_ed = new StringBuffer();
            StringBuffer sb_ee = new StringBuffer();
            StringBuffer sb_eei= new StringBuffer();
            StringBuffer sb_ei = new StringBuffer();
            StringBuffer sb_el = new StringBuffer();
            //create ED table
            sb_ed.append(" CREATE TABLE EIM_DEPARTMENT ( ");
            sb_ed.append(col_nm_ed.get(0));
            for (int i = 1; i < col_nm_ed.size(); i++) {
                sb_ed.append(",");
                sb_ed.append(col_nm_ed.get(i));
            }

            sb_ed.append("); ");
            //create EE table
            sb_ee.append(" CREATE TABLE EIM_EQUIPMENT ( ");
            sb_ee.append(col_nm_ee.get(0));
            for (int i = 1; i < col_nm_ee.size(); i++) {
                sb_ee.append(",");
                sb_ee.append(col_nm_ee.get(i));
            }

            sb_ee.append("); ");
            //create EEI table
            sb_eei.append(" CREATE TABLE EIM_EQUIP_INFO ( ");
            sb_eei.append(col_nm_eei.get(0));
            for (int i = 1; i < col_nm_eei.size(); i++) {
                sb_eei.append(",");
                sb_eei.append(col_nm_eei.get(i));
            }

            sb_eei.append("); ");
            //create EI table
            sb_ei.append(" CREATE TABLE EIM_IMAGE ( ");
            sb_ei.append(col_nm_ei.get(0));
            for (int i = 1; i < col_nm_ei.size(); i++) {
                sb_ei.append(",");
                sb_ei.append(col_nm_ei.get(i));
            }

            sb_ei.append("); ");
            //create EL table
            sb_el.append(" CREATE TABLE EIM_LOG ( ");
            sb_el.append(col_nm_el.get(0));
            for (int i = 1; i < col_nm_el.size(); i++) {
                sb_el.append(",");
                sb_el.append(col_nm_el.get(i));
            }
            sb_el.append("); ");

            // SQLite Database로 쿼리 실행
            db.execSQL(sb_ed.toString());
            db.execSQL(sb_ee.toString());
            db.execSQL(sb_eei.toString());
            db.execSQL(sb_ei.toString());
            db.execSQL(sb_el.toString());
            Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
        }
    }
    /** * */
//    public void testDB() {
//        SQLiteDatabase db = getReadableDatabase();
//        }
    public void getDB(String HSP_ID) {
//        SQLiteDatabase db = getReadableDatabase();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 웹서비스스레드
        StrictMode.setThreadPolicy(policy);

        ArrayList<String[]> arrReasult_ee = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_data_ee = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_eei = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_data_eei = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_ei = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_data_ei = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_ed = new ArrayList<String[]>();
        ArrayList<String[]> arrReasult_data_ed = new ArrayList<String[]>();
        ArrayList<String> arrList = new ArrayList<String>();
        ArrayList<String> arrListData = new ArrayList<String>();
        ArrayList<String> col_nm_ee = new ArrayList<String>();
        ArrayList<String> col_nm_eei = new ArrayList<String>();
        ArrayList<String> col_nm_ei = new ArrayList<String>();
        ArrayList<String> col_nm_ed = new ArrayList<String>();

        arrListData.add(HSP_ID);


        ews = new EmbodyWebService(context);
        try {
            arrReasult_ed = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_DEPARTMENT_COLUM_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            arrReasult_data_ed = ews.embodyWebService(arrListData, "PKG_PROCEDURE_INTERN_CYR.EIM_DEPARTMENT_DATA_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /////////
        try {
            arrReasult_ee = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_EQUIPMENT_COLUM_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            arrReasult_data_ee = ews.embodyWebService(arrListData, "PKG_PROCEDURE_INTERN_CYR.EIM_EQUIPMENT_DATA_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///////////
        try {
            arrReasult_eei = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_EQUIP_INFO_COLUM_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            arrReasult_data_eei = ews.embodyWebService(arrListData, "PKG_PROCEDURE_INTERN_CYR.EIM_EQUIP_INFO_DATA_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //////////////
        try {
        arrReasult_ei = ews.embodyWebService(arrList, "PKG_PROCEDURE_INTERN_CYR.EIM_IMAGE_COLUM_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
            try {
            arrReasult_data_ei = ews.embodyWebService(arrListData, "PKG_PROCEDURE_INTERN_CYR.EIM_IMAGE_DATA_LIST_ALL", "Package", "EMR", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < arrReasult_ed.size(); i++) {
            col_nm_ed.add(arrReasult_ed.get(i)[2].toString());

        }
        for (int i = 0; i < arrReasult_ee.size(); i++) {
            col_nm_ee.add(arrReasult_ee.get(i)[2].toString());

        }
        for (int i = 0; i < arrReasult_eei.size(); i++) {
            col_nm_eei.add(arrReasult_eei.get(i)[2].toString());

        }
        for (int i = 0; i < arrReasult_ei.size(); i++) {
            col_nm_ei.add(arrReasult_ei.get(i)[2].toString());

        }

        // 1. 쓸 수 있는 DB 객체를 가져온다.
        SQLiteDatabase db = getWritableDatabase();
        // 2. Person Data를 Insert한다.
        // _id는 자동으로 증가하기 때문에 넣지 않습니다.
//        StringBuffer sb_ed = new StringBuffer();
//        StringBuffer sb_ee = new StringBuffer();
//        StringBuffer sb_eei = new StringBuffer();
//        StringBuffer sb_ei = new StringBuffer();

        //기존에 있는 테이블의 데이터를 드롭시키고
        StringBuffer sb_ed_d = new StringBuffer();
        StringBuffer sb_ee_d = new StringBuffer();
        StringBuffer sb_eei_d = new StringBuffer();
        StringBuffer sb_ei_d = new StringBuffer();
        StringBuffer sb_el_d = new StringBuffer();
        sb_ed_d.append("DELETE FROM EIM_DEPARTMENT");
        sb_ee_d.append("DELETE FROM EIM_EQUIPMENT");
        sb_eei_d.append("DELETE FROM EIM_EQUIP_INFO");
        sb_ei_d.append("DELETE FROM EIM_IMAGE");
        sb_el_d.append("DELETE FROM EIM_LOG");
        db.execSQL(sb_ed_d.toString());
        db.execSQL(sb_ee_d.toString());
        db.execSQL(sb_eei_d.toString());
        db.execSQL(sb_ei_d.toString());
        db.execSQL(sb_el_d.toString());


        for (int j = 0; j < arrReasult_data_ed.size(); j++) {
            StringBuffer sb_ed = new StringBuffer();
            sb_ed.append("INSERT INTO EIM_DEPARTMENT (");
            sb_ed.append(col_nm_ed.get(0));
            for (int i = 1; i < col_nm_ed.size(); i++) {
                sb_ed.append(",");
                sb_ed.append(col_nm_ed.get(i));
            }

            sb_ed.append(")");
            sb_ed.append("VALUES (");
            sb_ed.append("'" + arrReasult_data_ed.get(j)[0].toString() + "'");
            for (int i = 1; i < col_nm_ed.size(); i++) {
                sb_ed.append(",");
                sb_ed.append("'" + arrReasult_data_ed.get(j)[i].toString() + "'");
            }

            sb_ed.append(");");

            db.execSQL(sb_ed.toString());
//            sb_ed=null;
//            sb_ed.append("");
        }
        //////
        for (int j = 0; j < arrReasult_data_ee.size(); j++) {
            StringBuffer sb_ee = new StringBuffer();


            sb_ee.append("INSERT INTO EIM_EQUIPMENT (");
            sb_ee.append(col_nm_ee.get(0));
            for (int i = 1; i < col_nm_ee.size(); i++) {
                sb_ee.append(",");
                sb_ee.append(col_nm_ee.get(i));
            }

            sb_ee.append(")");
            sb_ee.append("VALUES (");
            sb_ee.append("'" + arrReasult_data_ee.get(j)[0].toString() + "'");
            for (int i = 1; i < col_nm_ee.size(); i++) {
                sb_ee.append(",");
                sb_ee.append("'" + arrReasult_data_ee.get(j)[i].toString() + "'");
            }

            sb_ee.append(");");



            db.execSQL(sb_ee.toString());
//            sb_ee=null;
        }
        ////////
        for (int j = 0; j < arrReasult_data_eei.size(); j++) {
            StringBuffer sb_eei = new StringBuffer();

            sb_eei.append("INSERT INTO EIM_EQUIP_INFO (");
            sb_eei.append(col_nm_eei.get(0));
            for (int i = 1; i < col_nm_eei.size(); i++) {
                sb_eei.append(",");
                sb_eei.append(col_nm_eei.get(i));
            }

            sb_eei.append(")");
            sb_eei.append("VALUES (");
            sb_eei.append("'" + arrReasult_data_eei.get(j)[0].toString() + "'");
            for (int i = 1; i < col_nm_eei.size(); i++) {
                sb_eei.append(",");
                sb_eei.append("'" + arrReasult_data_eei.get(j)[i].toString() + "'");
            }

            sb_eei.append(");");

            db.execSQL(sb_eei.toString());
//            sb_eei=null;
        }
        /////////
        for (int j = 0; j < arrReasult_data_ei.size(); j++) {
            StringBuffer sb_ei = new StringBuffer();

            sb_ei.append("INSERT INTO EIM_IMAGE (");
            sb_ei.append(col_nm_ei.get(0));
            for (int i = 1; i < col_nm_ei.size(); i++) {
                sb_ei.append(",");
                sb_ei.append(col_nm_ei.get(i));
            }

            sb_ei.append(")");
            sb_ei.append("VALUES (");
            sb_ei.append("'" + arrReasult_data_ei.get(j)[0].toString() + "'");
            for (int i = 1; i < col_nm_ei.size(); i++) {
                sb_ei.append(",");
                sb_ei.append("'" + arrReasult_data_ei.get(j)[i].toString() + "'");
            }

            sb_ei.append(");");

            db.execSQL(sb_ei.toString());
//            sb_ei=null;
        }

//        db.execSQL(sb_ed.toString());
//        db.execSQL(sb_ee.toString());
//        db.execSQL(sb_eei.toString());
//        db.execSQL(sb_ei.toString());


    }

    public ArrayList<String[]> selectAll() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }

    public ArrayList<String[]> selectAll_orderByDept_asc() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("ORDER BY ED.EIM_DEPT_CENTER ASC");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> selectAll_orderByDept_desc() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("ORDER BY ED.EIM_DEPT_CENTER DESC");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> selectAll_orderByModel_asc() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("ORDER BY EE.EIM_EQUIP_MODEL ASC");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> selectAll_orderByModel_desc() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("ORDER BY EE.EIM_EQUIP_MODEL DESC");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> selectAll_orderByWdAsIs_asc() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("ORDER BY EEI.EIM_WD_AS_IS ASC");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> selectAll_orderByWdAsIs_desc() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("ORDER BY EEI.EIM_WD_AS_IS DESC");
        sb.append(";");




        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }

    public ArrayList<String[]> detail_info(String equip_code) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("EE.EIM_EQUIP_NM\n");
        sb.append(",EEI.EIM_EQUIP_MODEL\n");
        sb.append(",EEI.EIM_DEPT_USER\n");
        sb.append(",EEI.EIM_EQUIP_POS\n");
        sb.append(",EEI.EIM_AS_IS\n");
        sb.append(",EEI.EIM_TO_BE\n");
        sb.append(",EEI.EIM_WD_AS_IS\n");
        sb.append(",EEI.EIM_WD_RESULT\n");
        sb.append(",EEI.EIM_REMARK\n");
        sb.append(",EE.EIM_EQUIP_TYPE\n");
        sb.append(",EE.EIM_EQUIP_OPTION\n");
        sb.append(",EE.EIM_PROD_MGR_NM\n");
        sb.append(",EE.EIM_PROD_MGR_PHONENO\n");
        sb.append(",ED.EIM_DEPT_MGR_NM\n");
        sb.append(",ED.EIM_DEPT_MGR_PHONENO\n");
        sb.append(",EE.EIM_EQUIP_PRODUCER\n");
        sb.append(",EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM\n");
        sb.append("EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE\n");
        sb.append("EEI.EIM_EQUIP_MODEL=EE.EIM_EQUIP_MODEL\n");
        sb.append("AND\n");
        sb.append("ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND\n");
        sb.append("EEI.EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");

        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> dept_center_items(){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("EIM_DEPT_CENTER\n");
        sb.append("FROM\n");
        sb.append("EIM_DEPARTMENT ");
        sb.append(";");

        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> dept_user_items(String get_dept_center_nm){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("EIM_DEPT_LOC_NM\n");
        sb.append("FROM\n");
        sb.append("EIM_DEPARTMENT ");
        sb.append("WHERE EIM_DEPT_CENTER=");
        sb.append("'");
        sb.append(get_dept_center_nm);
        sb.append("'");
        sb.append(";");

        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> dept_equip_model(){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("EIM_EQUIP_MODEL\n");
        sb.append("FROM\n");
        sb.append("EIM_EQUIPMENT ");
        sb.append(";");

        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> select_by_dept(String dept_center, String dept_user) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("ED.EIM_HSP_ID,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("AND ED.EIM_DEPT_CENTER=");
        sb.append("'");
        sb.append(dept_center);
        sb.append("'");
        sb.append("\n");
        sb.append("AND EEI.EIM_DEPT_USER=");
        sb.append("'");
        sb.append(dept_user);
        sb.append("'");
        sb.append(";");



        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/" + test, null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();
        while (cursor.moveToNext()) {

            for (int i = 0; i < cursor.getColumnCount(); i++) {

                person.add(cursor.getString(i));
            }
            String[] sArrays = person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();
        }
        return people;

    }
    public ArrayList<String[]> select_by_model(String equip_model) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT\n");
        sb.append("DISTINCT\n");
        sb.append("ED.EIM_DEPT_CENTER,\n");
        sb.append("EE.EIM_EQUIP_NM,\n");
        sb.append("EE.EIM_EQUIP_MODEL,\n");
        sb.append("EEI.EIM_AS_IS,\n");
        sb.append("EEI.EIM_TO_BE,\n");
        sb.append("EEI.EIM_WD_AS_IS,\n");
        sb.append("ED.EIM_HSP_ID,\n");
        sb.append("EEI.EIM_EQUIP_CODE\n");
        sb.append("FROM EIM_DEPARTMENT ED,\n");
        sb.append("EIM_EQUIPMENT EE,\n");
        sb.append("EIM_EQUIP_INFO EEI\n");
        sb.append("WHERE ED.EIM_DEPT_LOC_NM=EEI.EIM_DEPT_USER\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=EEI.EIM_EQUIP_MODEL\n");
        sb.append("AND EE.EIM_EQUIP_MODEL=");
        sb.append("'");
        sb.append(equip_model);
        sb.append("'");
        sb.append(";");



        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();
        while( cursor.moveToNext() ) {

            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();
        }
        return people;
    }
    public void update_detail_info(ArrayList<String> new_info) {

        StringBuffer sb_ed = new StringBuffer();
        StringBuffer sb_ee = new StringBuffer();
        StringBuffer sb_eei = new StringBuffer();
       //ee update
        sb_ee.append("UPDATE EIM_EQUIPMENT \n");
        sb_ee.append("SET EIM_EQUIP_TYPE=");
        sb_ee.append("'");
        sb_ee.append(new_info.get(9).toString());
        sb_ee.append("'");
        sb_ee.append("\n");
        sb_ee.append(",EIM_EQUIP_OPTION=");
        sb_ee.append("'");
        sb_ee.append(new_info.get(10).toString());
        sb_ee.append("'");
        sb_ee.append("\n");
        sb_ee.append(",EIM_PROD_MGR_NM=");
        sb_ee.append("'");
        sb_ee.append(new_info.get(11).toString());
        sb_ee.append("'");
        sb_ee.append("\n");
        sb_ee.append(",EIM_PROD_MGR_PHONENO=");
        sb_ee.append("'");
        sb_ee.append(new_info.get(12).toString());
        sb_ee.append("'");
        sb_ee.append("\n");
        sb_ee.append(",EIM_EQUIP_PRODUCER=");
        sb_ee.append("'");
        sb_ee.append(new_info.get(15).toString());
        sb_ee.append("'");
        sb_ee.append("\n");
        sb_ee.append("WHERE EIM_EQUIP_MODEL=");
        sb_ee.append("'");
        sb_ee.append(new_info.get(1).toString());
        sb_ee.append("'");
        sb_ee.append(";");
        // ed update
        sb_ed.append("UPDATE EIM_DEPARTMENT \n");
        sb_ed.append("SET EIM_DEPT_MGR_NM=");
        sb_ed.append("'");
        sb_ed.append(new_info.get(13).toString());
        sb_ed.append("'");
        sb_ed.append("\n");
        sb_ed.append(",EIM_DEPT_MGR_PHONENO=");
        sb_ed.append("'");
        sb_ed.append(new_info.get(14).toString());
        sb_ed.append("'");
        sb_ed.append("\n");
        sb_ed.append("WHERE EIM_DEPT_LOC_NM=(SELECT EIM_EQUIP_POS \n");
        sb_ed.append("FROM EIM_EQUIP_INFO WHERE EIM_EQUIP_CODE=");
        sb_ed.append("'");
        sb_ed.append(new_info.get(16).toString());
        sb_ed.append("'");
        sb_ed.append(")");
        sb_ed.append(";");
        //eei update
        sb_eei.append("UPDATE EIM_EQUIP_INFO \n");
        sb_eei.append("SET EIM_AS_IS=");
        sb_eei.append("'");
        sb_eei.append(new_info.get(4).toString());
        sb_eei.append("'");
        sb_eei.append("\n");
        sb_eei.append(",EIM_TO_BE=");
        sb_eei.append("'");
        sb_eei.append(new_info.get(5).toString());
        sb_eei.append("'");
        sb_eei.append("\n");
        sb_eei.append(",EIM_WD_AS_IS=");
        sb_eei.append("'");
        sb_eei.append(new_info.get(6).toString());
        sb_eei.append("'");
        sb_eei.append("\n");
        sb_eei.append(",EIM_WD_RESULT=");
        sb_eei.append("'");
        sb_eei.append(new_info.get(7).toString());
        sb_eei.append("'");
        sb_eei.append("\n");
        sb_eei.append(",EIM_REMARK=");
        sb_eei.append("'");
        sb_eei.append(new_info.get(8).toString());
        sb_eei.append("'");
        sb_eei.append("\n");
        sb_eei.append("WHERE EIM_EQUIP_CODE=");
        sb_eei.append("'");
        sb_eei.append(new_info.get(16).toString());
        sb_eei.append("'");
        sb_eei.append(";");


        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb_ed.toString());
        db.execSQL(sb_ee.toString());
        db.execSQL(sb_eei.toString());



    }
    public void delete_detail_info(ArrayList<String> detail_info) {

        StringBuffer sb_ed = new StringBuffer();
        StringBuffer sb_ee = new StringBuffer();
        StringBuffer sb_eei = new StringBuffer();
        //ee update
        sb_ee.append("UPDATE EIM_EQUIPMENT \n");
        sb_ee.append("SET EIM_EQUIP_TYPE=''\n");
        sb_ee.append(",EIM_EQUIP_OPTION=''\n");
        sb_ee.append(",EIM_PROD_MGR_NM=''\n");
        sb_ee.append(",EIM_PROD_MGR_PHONENO=''\n");
        sb_ee.append(",EIM_EQUIP_PRODUCER=''\n");
        sb_ee.append("WHERE EIM_EQUIP_MODEL=");
        sb_ee.append("'");
        sb_ee.append(detail_info.get(1).toString());
        sb_ee.append("'");
        sb_ee.append(";");

//         ed update
        sb_ed.append("UPDATE EIM_DEPARTMENT \n");
        sb_ed.append("SET EIM_DEPT_MGR_NM=''\n");
        sb_ed.append(",EIM_DEPT_MGR_PHONENO=''\n");
        sb_ed.append("WHERE EIM_DEPT_LOC_NM=(SELECT EIM_EQUIP_POS \n");
        sb_ed.append("FROM EIM_EQUIP_INFO WHERE EIM_EQUIP_MODEL=");
        sb_ed.append("'");
//        sb_ed.append(detail_info.get(16).toString());  //이걸 사용하면 삭제가 안돼 ...
        sb_ed.append(detail_info.get(1).toString());
        sb_ed.append("'");
        sb_ed.append(")");
        sb_ed.append(";");
        //eei update
        sb_eei.append("UPDATE EIM_EQUIP_INFO \n");
        sb_eei.append("SET EIM_REMARK=''\n");
        sb_eei.append(", EIM_AS_IS=''\n");
        sb_eei.append(",EIM_TO_BE=''\n");
        sb_eei.append(",EIM_WD_AS_IS=''\n");
        sb_eei.append(",EIM_WD_RESULT=''\n");
        sb_eei.append("WHERE EIM_EQUIP_MODEL=");
        sb_eei.append("'");
        sb_eei.append(detail_info.get(1).toString());
        sb_eei.append("'");
        sb_eei.append(";");


        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb_ed.toString());
        db.execSQL(sb_ee.toString());
        db.execSQL(sb_eei.toString());



    }
    public void insert_device(ArrayList<String> insert_info) {
        StringBuffer sb_ed = new StringBuffer();
        StringBuffer sb_ei = new StringBuffer();
        StringBuffer sb_ee = new StringBuffer();
        StringBuffer sb_eei = new StringBuffer();
        //sb_ed insert
        sb_ed.append("INSERT INTO EIM_DEPARTMENT \n");
        sb_ed.append("(EIM_HSP_ID \n");
        sb_ed.append(",EIM_DEPT_LOC_NM \n");
        sb_ed.append(",EIM_DEPT_CENTER \n");
        sb_ed.append(",EIM_DEPT_MGR_NM \n");
        sb_ed.append(",EIM_DEPT_MGR_PHONENO) \n");
        sb_ed.append("VALUES (");
        sb_ed.append("'");
        sb_ed.append(test);
        sb_ed.append("'");
        sb_ed.append("\n");
        sb_ed.append(",");
        sb_ed.append("'");
        sb_ed.append(insert_info.get(2).toString());
        sb_ed.append("'");
        sb_ed.append("\n");
        sb_ed.append(",");
        sb_ed.append("'");
        sb_ed.append(insert_info.get(7).toString());
        sb_ed.append("','','')");
        sb_ed.append(";");

        //sb_ei insert
        sb_ei.append("INSERT INTO EIM_IMAGE \n");
        sb_ei.append("(EIM_EQUIP_CODE, \n");
        sb_ei.append("EIM_EQUIP_MODEL, \n");
        sb_ei.append("EIM_IMG_1, \n");
        sb_ei.append("EIM_IMG_2, \n");
        sb_ei.append("EIM_IMG_3, \n");
        sb_ei.append("EIM_IMG_4, \n");
        sb_ei.append("EIM_IMG_5, \n");
        sb_ei.append("EIM_IMG_6) \n");
        sb_ei.append("VALUES( ");
        sb_ei.append("'");
        sb_ei.append(insert_info.get(0).toString());
        sb_ei.append("'");
        sb_ei.append(", \n");
        sb_ei.append("'");
        sb_ei.append(insert_info.get(4).toString());
        sb_ei.append("','','','','','','')");
        sb_ei.append(";");

        //sb_ee insert
        sb_ee.append("INSERT INTO EIM_EQUIPMENT \n");
        sb_ee.append("(EIM_EQUIP_NM \n");
        sb_ee.append(", EIM_EQUIP_KR_NM \n");
        sb_ee.append(", EIM_EQUIP_MODEL \n");
        sb_ee.append(", EIM_EQUIP_PRODUCER \n");
        sb_ee.append(", EIM_EQUIP_TYPE \n");
        sb_ee.append(", EIM_EQUIP_OPTION \n");
        sb_ee.append(", EIM_PROD_MGR_NM \n");
        sb_ee.append(", EIM_PROD_MGR_PHONENO) \n");
        sb_ee.append("VALUES (");
        sb_ee.append("'");
        sb_ee.append(insert_info.get(3).toString());
        sb_ee.append("'");
        sb_ee.append(", \n");
        sb_ee.append("'");
        sb_ee.append(insert_info.get(5).toString());
        sb_ee.append("'");
        sb_ee.append(", \n");
        sb_ee.append("'");
        sb_ee.append(insert_info.get(4).toString());
        sb_ee.append("'");
        sb_ee.append(", \n");
        sb_ee.append("'");
        sb_ee.append(insert_info.get(6).toString());
        sb_ee.append("'");
        sb_ee.append(",'','','','');");
        //sb_eei insert
        sb_eei.append("INSERT INTO EIM_EQUIP_INFO \n");
        sb_eei.append("(EIM_EQUIP_CODE,\n");
        sb_eei.append("EIM_DEPT_USER,\n");
        sb_eei.append("EIM_EQUIP_POS,\n");
        sb_eei.append("EIM_EQUIP_MODEL,\n");
        sb_eei.append("EIM_HSP_ID,\n");
        sb_eei.append("EIM_AS_IS,\n");
        sb_eei.append("EIM_TO_BE,\n");
        sb_eei.append("EIM_WD_AS_IS,\n");
        sb_eei.append("EIM_WD_RESULT,\n");
        sb_eei.append("EIM_REMARK)\n");
        sb_eei.append("VALUES(\n");
        sb_eei.append("'");
        sb_eei.append(insert_info.get(0));
        sb_eei.append("',");
        sb_eei.append("'");
        sb_eei.append(insert_info.get(1));
        sb_eei.append("',");
        sb_eei.append("'");
        sb_eei.append(insert_info.get(2));
        sb_eei.append("',");
        sb_eei.append("'");
        sb_eei.append(insert_info.get(4));
        sb_eei.append("',");
        sb_eei.append("'");
        sb_eei.append(test);
        sb_eei.append("','','','','','');");

        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb_ed.toString());
        db.execSQL(sb_ee.toString());
        db.execSQL(sb_eei.toString());
        db.execSQL(sb_ei.toString());


    }
    public void add_image_1(String encodingText, String equip_code){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE EIM_IMAGE \n");
        sb.append("SET EIM_IMG_1=");
        sb.append("'");
        sb.append(encodingText);
        sb.append("'");
        sb.append("\n");
        sb.append("WHERE EIM_EQUIP_CODE=");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());
    }

    public ArrayList<String[]>  get_image_1(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_IMG_1 \n");
        sb.append("FROM EIM_IMAGE \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public void add_image_2(String encodingText, String equip_code){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE EIM_IMAGE \n");
        sb.append("SET EIM_IMG_2=");
        sb.append("'");
        sb.append(encodingText);
        sb.append("'");
        sb.append("\n");
        sb.append("WHERE EIM_EQUIP_CODE=");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());
    }

    public ArrayList<String[]>  get_image_2(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_IMG_2 \n");
        sb.append("FROM EIM_IMAGE \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public void add_image_3(String encodingText, String equip_code){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE EIM_IMAGE \n");
        sb.append("SET EIM_IMG_3=");
        sb.append("'");
        sb.append(encodingText);
        sb.append("'");
        sb.append("\n");
        sb.append("WHERE EIM_EQUIP_CODE=");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());
    }

    public ArrayList<String[]>  get_image_3(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_IMG_3 \n");
        sb.append("FROM EIM_IMAGE \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public void add_image_4(String encodingText, String equip_code){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE EIM_IMAGE \n");
        sb.append("SET EIM_IMG_4=");
        sb.append("'");
        sb.append(encodingText);
        sb.append("'");
        sb.append("\n");
        sb.append("WHERE EIM_EQUIP_CODE=");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());
    }

    public ArrayList<String[]>  get_image_4(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_IMG_4 \n");
        sb.append("FROM EIM_IMAGE \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public void add_image_5(String encodingText, String equip_code){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE EIM_IMAGE \n");
        sb.append("SET EIM_IMG_5=");
        sb.append("'");
        sb.append(encodingText);
        sb.append("'");
        sb.append("\n");
        sb.append("WHERE EIM_EQUIP_CODE=");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());
    }

    public ArrayList<String[]>  get_image_5(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_IMG_5 \n");
        sb.append("FROM EIM_IMAGE \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public void add_image_6(String encodingText, String equip_code){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE EIM_IMAGE \n");
        sb.append("SET EIM_IMG_6=");
        sb.append("'");
        sb.append(encodingText);
        sb.append("'");
        sb.append("\n");
        sb.append("WHERE EIM_EQUIP_CODE=");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());
    }

    public ArrayList<String[]>  get_image_6(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_IMG_6 \n");
        sb.append("FROM EIM_IMAGE \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]>  checkEquipCode(String equip_code){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT COUNT(*) \n");
        sb.append("FROM EIM_EQUIP_INFO \n");
        sb.append("WHERE EIM_EQUIP_CODE=\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("'");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]>  get_inner_DB_ed(){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_DEPT_LOC_NM, \n");
        sb.append("EIM_DEPT_CENTER, \n");
        sb.append("EIM_DEPT_MGR_NM, \n");
        sb.append("EIM_DEPT_MGR_PHONENO \n");
        sb.append("FROM EIM_DEPARTMENT \n");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]>  get_inner_DB_ee(){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_EQUIP_NM, \n");
        sb.append("EIM_EQUIP_KR_NM, \n");
        sb.append("EIM_EQUIP_MODEL, \n");
        sb.append("EIM_EQUIP_PRODUCER, \n");
        sb.append("EIM_EQUIP_TYPE, \n");
        sb.append("EIM_EQUIP_OPTION, \n");
        sb.append("EIM_PROD_MGR_NM, \n");
        sb.append("EIM_PROD_MGR_PHONENO \n");
        sb.append("FROM EIM_EQUIPMENT \n");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]>  get_inner_DB_eei(){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_EQUIP_CODE, \n");
        sb.append("EIM_DEPT_USER, \n");
        sb.append("EIM_EQUIP_POS, \n");
        sb.append("EIM_EQUIP_MODEL, \n");
        sb.append("EIM_AS_IS, \n");
        sb.append("EIM_TO_BE, \n");
        sb.append("EIM_WD_AS_IS, \n");
        sb.append("EIM_WD_RESULT, \n");
        sb.append("EIM_REMARK \n");
        sb.append("FROM EIM_EQUIP_INFO \n");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);

        Cursor cursor = db.rawQuery(sb.toString(), null);


        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> get_inner_DB_ei(){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_EQUIP_CODE, \n");
        sb.append("EIM_EQUIP_MODEL, \n");
        sb.append("EIM_IMG_1 , \n");
        sb.append("EIM_IMG_2 , \n");
        sb.append("EIM_IMG_3 , \n");
        sb.append("EIM_IMG_4 , \n");
        sb.append("EIM_IMG_5 , \n");
        sb.append("EIM_IMG_6\n");
        sb.append("FROM EIM_IMAGE \n");
//        sb.append("LIMIT 1 OFFSET \n");
//        sb.append(j);
//        sb.append("FROM (SELECT ROWNUM ROW_NUM,");
//        sb.append("EIM_EQUIP_CODE, \n");
//        sb.append("EIM_EQUIP_MODEL, \n");
//        sb.append("EIM_IMG_1 , \n");
//        sb.append("EIM_IMG_2 , \n");
//        sb.append("EIM_IMG_3 , \n");
//        sb.append("EIM_IMG_4 , \n");
//        sb.append("EIM_IMG_5 , \n");
//        sb.append("EIM_IMG_6\n");
//        sb.append("FROM EIM_IMAGE) IMAGE\n");
//        sb.append("WHERE IMAGE.ROW_NUM='3'\n");
        sb.append(";");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);


        Cursor cursor =db.rawQuery(sb.toString(), null);



        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public ArrayList<String[]> get_inner_DB_el(){
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT EIM_USER_ID, \n");
        sb.append("EIM_REASON, \n");
        sb.append("EIM_UPDATE_DATE , \n");
        sb.append("EIM_EQUIP_CODE ");
        sb.append("FROM EIM_LOG;");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);


        Cursor cursor =db.rawQuery(sb.toString(), null);



        ArrayList<String[]> people = new ArrayList<>();
        List<String> person = new ArrayList<>();

        while( cursor.moveToNext() ) {
            for(int i=0;i<cursor.getColumnCount();i++){

                person.add(cursor.getString(i));
            }
            String[] sArrays=person.toArray(new String[person.size()]);
            people.add(sArrays);
            person.clear();

        }
        return people;
    }
    public void delete_reason(String reson, String user,String equip_code){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
        Date currentTime = new Date( );
        String dTime = formatter.format ( currentTime );
        StringBuffer sb=new StringBuffer();
        sb.append("INSERT INTO EIM_LOG ");
        sb.append("(EIM_USER_ID, \n");
        sb.append("EIM_REASON, \n ");
        sb.append("EIM_UPDATE_DATE, \n ");
        sb.append("EIM_EQUIP_CODE) \n ");
        sb.append("VALUES ( ");
        sb.append("'");
        sb.append(user);
        sb.append("',\n");
        sb.append("'");
        sb.append(reson);
        sb.append("',\n");
        sb.append("'");
        sb.append(dTime);
        sb.append("',\n");
        sb.append("'");
        sb.append(equip_code);
        sb.append("');");
        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.example.neozensoft.internproject/databases/"+test,null);
        db.execSQL(sb.toString());

    }

}





