package com.xiaobai.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.xiaobai.utils.common.DensityUtils;

/**
 * 自定义输入对话框
 * Created by yunfei on 2017/6/12.
 */

public class EdiTextDialog {

    private IMyEdiTextDialogBack iMyEdiTextDialogBack;

    private EditText contentEt;

    private Context context;

    private String title = "", content = "";//提示内容

    public EdiTextDialog(Context context) {
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EditText getEdiText() {
        return contentEt;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (!title.equals("")) {
            builder.setMessage(title);
        }

        contentEt = new EditText(context);
        contentEt.setPadding(DensityUtils.dip2px(context, 16), DensityUtils.dip2px(context, 8),
                DensityUtils.dip2px(context, 16), DensityUtils.dip2px(context, 8));
        contentEt.setText(content);
        builder.setView(contentEt);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iMyEdiTextDialogBack != null) {
                    iMyEdiTextDialogBack.sure(contentEt.getText().toString().trim());
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (iMyEdiTextDialogBack != null) {
                    iMyEdiTextDialogBack.cancel();
                }
            }
        });

        builder.create().show();
    }

    /**
     * 设置回调接口
     *
     * @param iMyEdiTextDialogBack 回调接口
     */
    public void setIMyEdiTextDialogBack(IMyEdiTextDialogBack iMyEdiTextDialogBack) {
        this.iMyEdiTextDialogBack = iMyEdiTextDialogBack;
    }

    public interface IMyEdiTextDialogBack {
        void sure(String content);

        void cancel();
    }
}
