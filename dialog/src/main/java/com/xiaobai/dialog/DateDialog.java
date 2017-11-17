package com.xiaobai.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import com.xiaobai.utils.common.DateUtils;

/**
 * 日期选择对话框
 * Created by yunfei on 2016/3/31.
 */
public class DateDialog {

    private IDateDialogBack iDateDialogBack;

    private AlertDialog dateDialog;

    private Context context;
    private String date;//默认时间
    private boolean isSetDate = false;//是否设置默认时间

    public DateDialog(Context context) {
        this.context = context;
    }

    public void setDate(String date) {
        this.date = date;
        isSetDate = true;
    }

    public void show() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final DatePicker datePicker = new DatePicker(context);
        builder.setView(datePicker);

        if (isSetDate) {//设置默认时间
            String[] dateString = date.split("-");
            datePicker.updateDate(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]) - 1,
                    Integer.parseInt(dateString[2]));
        }

        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dateDialog = builder.create();
        dateDialog.show();
        dateDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iDateDialogBack != null) {
                    iDateDialogBack.getDate(datePicker.getYear() + "", DateUtils.getNumber(datePicker.getMonth() + 1),
                            DateUtils.getNumber(datePicker.getDayOfMonth()), dateDialog);
                }
            }
        });
    }

    /**
     * 设置回调接口
     *
     * @param iDateDialogBack 回调接口
     */
    public void setIDateDialogBack(IDateDialogBack iDateDialogBack) {
        this.iDateDialogBack = iDateDialogBack;
    }

    /**
     * 选择日期对话框回调接口
     * Created by yunfei on 2016/3/31.
     */
    public interface IDateDialogBack {
        void getDate(String year, String month, String day, AlertDialog dateDialog);
    }
}