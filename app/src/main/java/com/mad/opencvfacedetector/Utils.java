package com.mad.opencvfacedetector;

import android.content.Context;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;

public class Utils {
    public static String getPathFromAssets(Context context, String assetsFileName) {
        File file = new File(context.getCacheDir().getAbsolutePath(), assetsFileName);
//        if (file.exists() && file.length() > 0) {
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                inputStream = context.getAssets().open(assetsFileName);
                fileOutputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1000];
                int bytes;
                while ((bytes = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(inputStream);
                closeStream(fileOutputStream);
            }
//        }

        return file.getAbsolutePath();
    }

    private static void closeStream(@Nullable Closeable stream) {
        if (stream == null) {
            return;
        }

        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
