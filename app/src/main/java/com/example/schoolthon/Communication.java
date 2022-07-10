package com.example.schoolthon;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Communication extends AppCompatActivity {
    private static String text= "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comm);

        Button btninput = (Button) findViewById(R.id.btninput);
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(text.toString());
        btninput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"글 작성 완료",Toast.LENGTH_LONG).show();
                text=editText.getText().toString();
                editText.setEnabled(false);
                btninput.setText("뒤로 돌아가기");
                Intent intent = new Intent(Communication.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}