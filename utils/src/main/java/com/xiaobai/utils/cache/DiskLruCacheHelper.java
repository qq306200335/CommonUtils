package com.xiaobai.utils.cache;

import android.content.Context;

import com.xiaobai.utils.common.MyUtils;
import com.xiaobai.utils.common.SDCardUtils;
import com.xiaobai.utils.common.VersionUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 作者：柏云飞 on 2015/9/18.
 * 邮箱：306200335@qq.com
 */

public class DiskLruCacheHelper {

    private static DiskLruCache mCache;

    /**
     * 打开DiskLruCache
     */
    public static void openCache(Context context) {
        try {
            File cacheDir = SDCardUtils.getDiskCacheDir(context, "object");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mCache = DiskLruCache.open(cacheDir, VersionUtils.getVersionCode(context), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入缓存
     */
    public static void writeObject(Context context, Object object, String cachePath) throws IOException {

        //缓存最新数据
        DiskLruCacheHelper.openCache(context);
        try {
            DiskLruCacheHelper.write(object, MyUtils.hashKeyForDisk(cachePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入缓存
     */
    public static void write(Object object, String keyCache) throws IOException {

        if (mCache == null) throw new IllegalStateException("Must call openCache() first!");

        DiskLruCache.Editor editor = mCache.edit(keyCache);

        OutputStream outputStream = editor.newOutputStream(0);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();

        if (outputStream != null) {
            editor.commit();
        } else {
            editor.abort();
        }

        mCache.flush();
    }

    /**
     * 写入缓存
     */
    public static void writeInputStream(InputStream inputStream, String keyCache) throws IOException {

        if (mCache == null) throw new IllegalStateException("Must call openCache() first!");

        DiskLruCache.Editor mEditor = mCache.edit(keyCache);

        OutputStream outputStream = mEditor.newOutputStream(0);

        BufferedInputStream bin = new BufferedInputStream(inputStream);
        BufferedOutputStream bout = new BufferedOutputStream(outputStream);

        byte[] buf = new byte[1024];
        int len;
        while ((len = bin.read(buf)) != -1) {
            bout.write(buf, 0, len);
        }

        bout.close();
        outputStream.close();

        mEditor.commit();
    }

    /**
     * 读取缓存
     */
    public static Object readObject(Context context, String cachePath) throws IOException {

        Object object = null;
        try {
            DiskLruCacheHelper.openCache(context);
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    DiskLruCacheHelper.read(MyUtils.hashKeyForDisk(cachePath)));
            object = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * 读取缓存
     */
    public static InputStream read(String keyCache) throws IOException {
        if (mCache == null) throw new IllegalStateException("Must call openCache() first!");

        DiskLruCache.Snapshot snapshot = mCache.get(keyCache);

        if (snapshot == null) return null;
        else return snapshot.getInputStream(0);
    }

    /**
     * 获取缓存的大小
     *
     * @return 缓存的大小
     */
    public static long getCacheSize() {
        return mCache.size();
    }

    /**
     * 删除全部缓存
     */
    public static void deletCache() throws IOException {
        mCache.delete();
    }

    /**
     * 同步日志
     */
    public static void syncLog() {
        try {
            mCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

