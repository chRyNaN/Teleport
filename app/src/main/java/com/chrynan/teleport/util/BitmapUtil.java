package com.chrynan.teleport.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Copyright 2016 chRyNaN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Created by ckeenan on 9/11/16.
 * This is a util class focused on storing and retrieving Bitmaps.
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
