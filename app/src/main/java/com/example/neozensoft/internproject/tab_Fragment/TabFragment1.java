package com.example.neozensoft.internproject.tab_Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neozensoft.internproject.Camera_function;
import com.example.neozensoft.internproject.MyApplication;
import com.example.neozensoft.internproject.R;
import com.example.neozensoft.internproject.SubActivity;
import com.example.neozensoft.internproject.localDBconnection.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by neozensoft on 2017-10-17.
 */

public class TabFragment1 extends Fragment{

    private Button btn;
    private ImageView imageView;
    private static final String IMAGE_DIRECTORY="/demonuts";
    private int GALLERY=1, CAMERA=2;
    public MyApplication myApp;
    private DBHelper dbHelper;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String equip_code=this.getArguments().getString("code");


        View v;
        v= inflater.inflate(R.layout.tab_fragment_1, container, false);
        myApp=(MyApplication)getActivity().getApplication();
        final String id=myApp.getGlobalArrayList().get(0);



        String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        ActivityCompat.requestPermissions(this.getActivity(), permissions, CAMERA);


        dbHelper=new DBHelper(this.getContext(),id,null,1);
        ArrayList<String[]> decode_image=dbHelper.get_image_1(equip_code);






        btn=(Button)v.findViewById(R.id.btn);
        imageView=(ImageView)v.findViewById(R.id.iv);

        //사진 저장된게 있다면 사진 띄우기
        if(decode_image.size()!=0){

            byte[] byte_image=Base64.decode(String.valueOf(decode_image.get(0)[0].toString()), 0);
            Bitmap bitmap_image= BitmapFactory.decodeByteArray(byte_image,0,byte_image.length);


            if(bitmap_image!=null) {
                //수정
                imageView.setImageBitmap(bitmap_image);
            }

        }

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                showPictureDialog();
            }
        });
        return v;
    }


    //갤러리 열것인지 아님 사진기를 열것인지
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog=new AlertDialog.Builder(this.getContext());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems={
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        switch(which){
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();

    }

    //갤러리로 열것이다
    public void choosePhotoFromGallary(){
        Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    //사진기를 열것이다
    private void takePhotoFromCamera(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA);
    }

    //사진가지고 오기
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== getActivity().RESULT_CANCELED){
            return;
        }
        if(requestCode==GALLERY){
            if(data !=null){
                Uri contentURI=data.getData();
                try{
                    Bitmap bitmap=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    String path=saveImage(bitmap);
                    Toast.makeText(this.getContext(),"Image Saved!",Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(bitmap);
                }catch(IOException e){
                    e.printStackTrace();
                    Toast.makeText(this.getContext(),"Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode==CAMERA){
            Bitmap thumbnail=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(this.getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    public String saveImage(Bitmap myBitmap){
        myApp=(MyApplication)getActivity().getApplication();
        final String id=myApp.getGlobalArrayList().get(0);
        String equip_code=this.getArguments().getString("code");


        ByteArrayOutputStream bytes=new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG,90,bytes);
//저장하는 디렉토리
        File wallpaperDirectory=new File(
                Environment.getExternalStorageDirectory()+IMAGE_DIRECTORY );


        if(!wallpaperDirectory.exists()){
            wallpaperDirectory.mkdirs();
        }try {
            //base64로 인코딩하기
            String encodingText=
                    Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);
            dbHelper=new DBHelper(this.getContext(),id,null,1);
            dbHelper.add_image_1(encodingText, equip_code);



            //////////////////////////파일명 지정하기
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this.getContext(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        return "";

    }


}
