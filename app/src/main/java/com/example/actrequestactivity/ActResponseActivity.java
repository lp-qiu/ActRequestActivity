package com.example.actrequestactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActResponseActivity extends AppCompatActivity implements OnClickListener {

    private TextView tv_response;
    private EditText et_response;
    private Button btn_response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);

        tv_response = (TextView) findViewById(R.id.tv_response);
        et_response = (EditText) findViewById(R.id.et_response);
        btn_response = (Button) findViewById(R.id.btn_response);
        btn_response.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");
        String desc = String.format("收到请求消息：\n 请求时间：%s \n 请求内容：%s", request_time, request_content);
        tv_response.setText(desc);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_response) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putString("request_time", DateUtil.getNowTime());
            bundle.putString("request_content", et_response.getText().toString());
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
