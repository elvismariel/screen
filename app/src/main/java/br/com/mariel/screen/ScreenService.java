package br.com.mariel.screen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by fmanager on 02/10/17.
 */

public class ScreenService extends TimerTask {
    private Context context;

    public ScreenService(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.i("ScreenService", "ScreenService - run()");
        Date now = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyyymmdd_hhmmss");
        String fileName = dt.format(now).concat(".png");
        String pathFile = Environment.getExternalStorageDirectory() + File.separator + ".trash" + File.separator;

        try {
            File mediaFile = new File(pathFile + fileName);
            Process sh = Runtime.getRuntime().exec("su", null,null);
            OutputStream os = sh.getOutputStream();

            os.write(("/system/bin/screencap -p " + mediaFile.getAbsolutePath()).getBytes("ASCII"));
            os.flush();
            os.close();
            sh.waitFor();

            generateJpg(fileName, pathFile);

            if(mediaFile.exists())
                mediaFile.delete();

            Log.i("ScreenService", "OK!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateJpg(String fileName, String pathFile){
        Bitmap screen = BitmapFactory.decodeFile(pathFile + fileName);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        screen.compress(Bitmap.CompressFormat.JPEG, 05, bytes);

        try {
            File f = new File(pathFile + fileName.replace(".png", ".jpg"));
            Log.i("ScreenService", "Path: " + f.getAbsolutePath());
            //write the bytes in file
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
