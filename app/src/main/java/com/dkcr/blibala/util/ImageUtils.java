package com.dkcr.blibala.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * Author: lyl
 * Date: 2022/6/23 23:05
 */
public class ImageUtils {

    public static File getFile(Context context, Uri uri) {
        File rootFile = context.getExternalFilesDir(null);
        File file = new File(rootFile, System.currentTimeMillis() + ".jpeg");

        try {
            byte[] buffer = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            while (true) {
                int numRead = inputStream.read(buffer);
                if (numRead == -1) {
                    break;
                }
                fileOutputStream.write(buffer, 0, numRead);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            file = null;
            e.printStackTrace();
        }

        return file;
    }

    /***
     * 将指定路径的图片转uri
     * @param context
     * @param path ，指定图片(或文件)的路径
     * @return
     */
    public static Uri getMediaUriFromPath(Context context, String path) {
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(mediaUri,
                null,
                MediaStore.Images.Media.DISPLAY_NAME + "= ?",
                new String[] {path.substring(path.lastIndexOf("/") + 1)},
                null);

        Uri uri = null;
        if(cursor.moveToFirst()) {
            uri = ContentUris.withAppendedId(mediaUri,
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)));
        }
        cursor.close();
        return uri;
    }
}
