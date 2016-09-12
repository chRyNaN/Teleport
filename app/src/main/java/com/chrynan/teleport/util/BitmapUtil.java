package com.chrynan.teleport.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ckeenan on 9/11/16. This is a util class focused on storing and retrieving Bitmaps.
 */
public class BitmapUtil {
    private static final String TEMP_FOLDER_LOCATION = "_temp" + File.separator + "images";

    public static void saveBitmap(final Context context, final String key, final Bitmap bitmap) {
        if (context == null) {
            throw new IllegalArgumentException("The Context parameter in the saveBitmap method in the BitmapUtil class cannot be null.");
        }

        if (key == null) {
            throw new IllegalArgumentException("The String parameter in the saveBitmap method in the BitmapUtil class cannot be null.");
        }

        if (bitmap == null) {
            throw new IllegalArgumentException("The Bitmap parameter in the saveBitmap method in the BitmapUtil class cannot be null.");
        }

        FileOutputStream fileOutputStream = null;

        try {
            final File folder = new File(context.getFilesDir(), TEMP_FOLDER_LOCATION);

            if (folder.exists() || folder.mkdirs()) {

                final File file = new File(folder, key);
                fileOutputStream = new FileOutputStream(file);

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {

                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap getBitmap(final Context context, final String key) {
        if (context == null) {
            throw new IllegalArgumentException("The Context parameter in the saveBitmap method in the BitmapUtil class cannot be null.");
        }

        if (key == null) {
            throw new IllegalArgumentException("The String parameter in the saveBitmap method in the BitmapUtil class cannot be null.");
        }

        final File folder = new File(context.getFilesDir(), TEMP_FOLDER_LOCATION);

        if (folder.exists()) {
            final File file = new File(folder, key);

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            return BitmapFactory.decodeFile(file.getPath(), options);
        }

        return null;
    }
}
