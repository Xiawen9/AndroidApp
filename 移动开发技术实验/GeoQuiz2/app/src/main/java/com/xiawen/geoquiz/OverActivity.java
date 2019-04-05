package com.xiawen.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OverActivity extends AppCompatActivity {
    private TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);
        Intent intent = getIntent();
        int temp1 = intent.getIntExtra("score",0);
        String t = Integer.toString(temp1);
        System.out.println("得分："+temp1);
        //String temp = intent.getStringExtra("score");
        tv_show = (TextView)findViewById(R.id.tv_show);
        tv_show.setText(t);
    }
}
