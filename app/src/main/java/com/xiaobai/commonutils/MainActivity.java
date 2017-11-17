package com.xiaobai.commonutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiaobai.dialog.MyDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DateDialog dateDialog = new DateDialog(this);
//        dateDialog.show();

//        SureDialog sureDialog = new SureDialog(this);
//        sureDialog.setContent("确认删除？");
//        sureDialog.show();

        MyDialog myDialog = new MyDialog(this);
        myDialog.setTitle("提示");
        myDialog.setContent("自定义消息");
        myDialog.show();

//        EdiTextDialog ediTextDialog = new EdiTextDialog(this);
//        ediTextDialog.setTitle("填写姓名");
//        ediTextDialog.setContent("张三");
//        ediTextDialog.show();

//        ProgressDialog progressDialog = ProgressDialog.createDialog(this);
//        progressDialog.show();
    }
}
