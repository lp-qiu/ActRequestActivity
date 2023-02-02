package com.example.actrequestactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private TextView tv_request;
    private EditText et_request;
    private Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_request = (TextView) findViewById(R.id.tv_request);
        et_request = (EditText) findViewById(R.id.et_request);
        btn_request = (Button) findViewById(R.id.btn_request);
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_request) {
            Intent intent = new Intent();
            intent.setClass(this, ActResponseActivity.class);
            intent.putExtra("request_time", DateUtil.getNowTime());
            intent.putExtra("request_content", et_request.getText().toString());
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            String response_time = data.getStringExtra("request_time");
            String response_content = data.getStringExtra("request_content");
            String desc = String.format("收到返回消息：\n 应答时间：%s \n 应答内容：%s", response_time, response_content);
            tv_request.setText(desc);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}