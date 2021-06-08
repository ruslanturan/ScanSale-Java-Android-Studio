package com.IOStarter.scansale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imgBtn;

    public DownloadImageTask(ImageView imageView) {
        this.imgBtn = imageView;
    }

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(String... strArr) {
        try {
            return BitmapFactory.decodeStream(new URL(strArr[0]).openStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Bitmap bitmap) {
        this.imgBtn.setImageBitmap(bitmap);
    }
}
