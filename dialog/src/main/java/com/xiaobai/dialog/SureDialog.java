package com.xiaobai.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * 确认删除对话框
 * Created by yunfei on 2016/3/31.
 */
public class SureDialog {

    private ISureDialogBack iSureDialogBack;

    private Context context;

    private String title = "";//提示标题
    private String content = "确定删除？";//提示内容

    public SureDialog(Context context) {
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
        builder.setMessage(content);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iSureDialogBack.sure();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.create().show();
    }

    /**
     * 设置回调接口
     *
     * @param iSureDialogBack 回调接口
     */
    public void setISureDialogBack(ISureDialogBack iSureDialogBack) {
        this.iSureDialogBack = iSureDialogBack;
    }

    public interface ISureDialogBack {

        void sure();
    }
}