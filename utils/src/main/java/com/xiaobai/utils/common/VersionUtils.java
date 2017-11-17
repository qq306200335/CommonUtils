package com.xiaobai.utils.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 作者：柏云飞 on 2015/9/18.
 * 邮箱：306200335@qq.com
 */
public class VersionUtils {

    private static PackageInfo getPackageInfo(Context context) {

        PackageInfo packageInfo = null;

        try {
            PackageManager packageManager = context.getPackageManager();
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return packageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageInfo;
    }

    //获取应用的版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //获取应用的版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }
}
