package com.example.schoolthon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Video extends AppCompatActivity {
    VideoView videoView;
    Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        Button btnCheck = (Button) findViewById(R.id.btnCheck);
        videoView = findViewById(R.id.videoView);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //****
                Toast.makeText(getApplicationContext(),"업로드 완료하였습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Video.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void bt1(View view) {    // 동영상 선택 누르면 실행됨 동영상 고를 갤러리 오픈
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                MediaController mc = new MediaController(this); // 비디오 컨트롤 가능하게(일시정지, 재시작 등)
                videoView.setMediaController(mc);

                Uri fileUri = data.getData();
                videoView.setVideoPath(String.valueOf(fileUri));    // 선택한 비디오 경로 비디오뷰에 셋
                videoView.start();  // 비디오뷰 시작
            }
        }
    }
}