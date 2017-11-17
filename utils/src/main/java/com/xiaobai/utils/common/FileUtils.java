package com.xiaobai.utils.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.List;

/**
 * 文件工具类
 * Created by yunfei on 2016/2/26.
 */
public class FileUtils {

    /**
     * 打开不同类型的文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static Intent getOpenFileIntent(Context context, String filePath) {

        File file = new File(filePath);

        // 打开
        Intent intent = new Intent();
        // 打开、显示
        intent.setAction(Intent.ACTION_VIEW);
        int index = file.getName().lastIndexOf(".");
        String suffix = file.getName().substring(index + 1);
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix);

        Uri data;

        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            data = FileProvider.getUriForFile(context,
                    "com.kzcpm.scene.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, type);

        return intent;
    }

    /**
     * 删除空目录
     *
     * @param dir 将要删除的目录路径
     */
    public static boolean doDeleteEmptyDir(String dir) {
        return (new File(dir)).delete();
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (String aChildren : children) {
                boolean success = deleteFile(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @return flag
     */
    public static boolean deleteFile(File file) {
        boolean flag = false;
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 删除文件集合
     */
    public static void deleteAddItemFile(List<File> fileList) {

        for (File file : fileList) {
            FileUtils.deleteFile(file);
        }
    }

    /**
     * 修改文件名
     *
     * @param filePath 文件名和路径
     * @param toFile   新的文件名
     */
    public static void renameFile(String filePath, String toFile) {

        File toBeRenamed = new File(filePath);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {

            System.out.println("File does not exist: " + filePath);
            return;
        }

        File newFile = new File(toFile);

        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            System.out.println("File has been renamed.");
        } else {
            System.out.println("Error renmaing file");
        }
    }

    /**
     * 转换文件大小值为string文字类型
     *
     * @param length 文件大小
     * @return
     */
    public static String getFileSize(long length) {

        long gb = 2 << 29;
        long mb = 2 << 19;
        long kb = 2 << 9;

        if (length < kb) {
            return length + "B";
        } else if (length >= kb && length < mb) {
            return String.format("%.2fKB", length / (double) kb);
        } else if (length >= mb && length < gb) {
            return String.format("%.2fMB", length / (double) mb);
        } else if (length >= gb) {
            return String.format("%.2fGB", length / (double) gb);
        }

        return "";
    }
}