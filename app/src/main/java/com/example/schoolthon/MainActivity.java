package com.example.schoolthon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TabHost;
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost() ; //탭 호스트 객체 생성
        ImageView imageView, imageView1, imageView2;

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.image_21);

        imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.image_22);
        imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.image_20);
        String number = getIntent().getStringExtra("number");
// 탭스팩 선언하고, 탭의 내부 명칭, 탭에 출력될 글 작성
        TabHost.TabSpec spec;
        Intent intent; //객체


//탭에서 액티비티를 사용할 수 있도록 인텐트 생성
        intent = new Intent().setClass(this, Home.class);
        spec = tabHost.newTabSpec("Home"); // 객체를 생성
        spec.setIndicator(imageView); //탭의 이름 설정
        spec.setContent(intent);
        tabHost.addTab(spec);



//탭에서 액티비티를 사용할 수 있도록 인텐트 생성
        intent = new Intent().setClass(this, func.class);
        spec = tabHost.newTabSpec("Func"); // 객체를 생성
        spec.setIndicator(imageView1); //탭의 이름 설정
        spec.setContent(intent);
        tabHost.addTab(spec);



//탭에서 액티비티를 사용할 수 있도록 인텐트 생성
        intent = new Intent().setClass(this, Record.class);
        spec = tabHost.newTabSpec("Record"); // 객체를 생성
        spec.setIndicator(imageView2); //탭의 이름 설정
        spec.setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0); //먼저 열릴 탭을 선택! (2)로 해두면 그룹이 시작 화면!

    }

}