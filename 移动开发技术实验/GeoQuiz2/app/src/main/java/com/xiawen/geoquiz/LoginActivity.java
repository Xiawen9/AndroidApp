package com.xiawen.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Map;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_name;
    private EditText et_password;
    private Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Map<String,String > userInfo = SpSaveLogin.getUserInfo(this);
        et_name = (EditText)findViewById(R.id.et_name);
        et_password = (EditText)findViewById(R.id.et_password);
        login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String m_name = et_name.getText().toString();
        String m_password = et_password.getText().toString();
        if(TextUtils.isEmpty(m_name)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(m_password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        SpSaveLogin.saveUserInfo(this,m_name,m_password);
        Map<String, String > userInfo = SpSaveLogin.getUserInfo(this);
        String m_name_key = userInfo.get("name");
        String m_password_key = userInfo.get("password");
        // System.out.println("姓名"+m_name_key);
        //System.out.println("密码"+m_password_key);
        if(m_name_key.equals("1610421036") && m_password_key.equals("111111")){
             //Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    jump();
                }
            },500);
        }
        else{
            Toast.makeText(this,"您的信息输入有误",Toast.LENGTH_SHORT).show();
        }
    }
    public void jump(){
        Intent intent = new Intent(this,QuizActivity.class);
        startActivity(intent);
    }
}
