package com.xiaobai.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * 选择对话框
 * Created by yunfei on 2016/3/31.
 */
public class SelectDialog {

    private ISelectDialogBack iSelectDialogBack;

    private Dialog selectDialog;

    private Context context;
    private String[] strings;
    private int position;
    private boolean isCancel = true;

    public SelectDialog(Context context) {
        this.context = context;
    }

    public void setStrings(String[] strings, int position) {
        this.strings = strings;
        this.position = position;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (strings == null) {
            return;
        }

        builder.setSingleChoiceItems(strings, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (iSelectDialogBack != null) {
                    iSelectDialogBack.getPosition(i);
                }
            }
        });

        selectDialog = builder.create();
        selectDialog.setCanceledOnTouchOutside(isCancel);
        selectDialog.show();
    }

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * 设置回调接口
     *
     * @param iSelectDialogBack 回调接口
     */
    public void setISelectDialogBack(ISelectDialogBack iSelectDialogBack) {
        this.iSelectDialogBack = iSelectDialogBack;
    }

    public interface ISelectDialogBack {

        void getPosition(int position);
    }
}
