package com.xiaobai.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * 自定义对话框
 * Created by yunfei on 2016/12/2.
 */

public class MyDialog {

    private IMyDialogBack iMyDialogBack;

    private Context context;

    private String title = "", content = "";//提示内容

    public MyDialog(Context context) {
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (!title.equals("")) {
            builder.setTitle(title);
        }

        if (!content.equals("")) {
            builder.setMessage(content);
        }

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iMyDialogBack!=null){
                    iMyDialogBack.sure();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iMyDialogBack!=null){
                    iMyDialogBack.cancel();
                }
            }
        });

        builder.create().show();
    }

    /**
     * 设置回调接口
     *
     * @param iMyDialogBack 回调接口
     */
    public void setIMyDialogBack(IMyDialogBack iMyDialogBack) {
        this.iMyDialogBack = iMyDialogBack;
    }

    public interface IMyDialogBack {

        void sure();

        void cancel();
    }
}
