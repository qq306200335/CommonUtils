package com.xiaobai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

/**
 * 网络请求进度
 * 作者：柏云飞 on 2015/9/14.
 * 邮箱：306200335@qq.com
 */
public class ProgressDialog extends Dialog {

    private static ProgressDialog progressDialog = null;

    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static ProgressDialog createDialog(Context context) {
        progressDialog = new ProgressDialog(context, R.style.ProgressDialog);
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (progressDialog == null) {
            return;
        }
    }
}
