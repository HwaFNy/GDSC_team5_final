package com.example.schoolthon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;


public class Photo extends Activity implements View.OnClickListener {
    static final int REQEUST_IMAGE_CAPTURE = 1;
    static final int REQEUST_TAKE_PHOTO = 2;
    static final int REQEUST_IMAGE_CROP = 3;


    Button btnUploadPhoto,check;
    String mCurrentPhotoPath;
    ImageView iv_capture;
    Uri photoURI,albumURI=null;
    Boolean album = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_photo);

        iv_capture = (ImageView) findViewById(R.id.photo);
        check=(Button)findViewById(R.id.check);
        btnUploadPhoto=(Button)findViewById(R.id.btnUploadPhoto);
        btnUploadPhoto.setOnClickListener(this);
        check.setOnClickListener(this);
    }

    public void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQEUST_TAKE_PHOTO);
    }

    private void cropImage() {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        cropIntent.setDataAndType(photoURI,"image/*");
        cropIntent.putExtra("scale",true);
        if(album == false)
            cropIntent.putExtra("output",photoURI);
        else if(album==true)
            cropIntent.putExtra("output",albumURI);
        startActivityForResult(cropIntent,REQEUST_IMAGE_CROP);
    }

    private File createImageFile() throws IOException {
        String imageFileName="tmp_" + String.valueOf(System.currentTimeMillis())+".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory(),imageFileName);
        mCurrentPhotoPath = storageDir.getAbsolutePath();
        return storageDir;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != RESULT_OK){
        } else {
            switch (requestCode){
                case REQEUST_TAKE_PHOTO:
                    album=true;
                    File albumFile = null;
                    try{
                        albumFile= createImageFile();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    if(albumFile != null){
                        albumURI = Uri.fromFile(albumFile);
                    }
                    photoURI = data.getData();

                    Bitmap image_bitmap = null;
                    try {
                        image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iv_capture.setImageBitmap(image_bitmap);

                case REQEUST_IMAGE_CAPTURE:
                    cropImage();
                    break;
                case REQEUST_IMAGE_CROP:

                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    if(album==false)
                        mediaScanIntent.setData(photoURI);
                    else if(album==true) {
                        album = false;
                        mediaScanIntent.setData(albumURI);
                    }
                    this.sendBroadcast(mediaScanIntent);
                    break;
            }

        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case(R.id.check):
                Toast.makeText(getApplicationContext(),"업로드 완료하였습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Photo.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case(R.id.btnUploadPhoto):
                doTakeAlbumAction();
                break;
        }


    }

}