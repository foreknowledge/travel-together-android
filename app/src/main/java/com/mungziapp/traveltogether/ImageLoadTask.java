package com.mungziapp.traveltogether;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private static final String TAG = "ImageLoadTask ::";

    private String urlStr;
    private ImageView imageView;

    private static HashMap<String, Bitmap> bitmapHash = new HashMap<>();

    public ImageLoadTask(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        try {
            Bitmap bitmap;
            // 기존에 존재하는 url이라면 저장한 hash map에서 비트맵 이미지를 가져온다.
            if (bitmapHash.containsKey(urlStr)) {
                bitmap = bitmapHash.get(urlStr);
            }
            else {
                URL url = new URL(urlStr);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                int scale = 1;
                int minImageSize = 100;

                if (options.outHeight > minImageSize || options.outWidth > minImageSize)
                    scale = (int)Math.pow(2, (int)Math.round(Math.log(minImageSize/(double)Math.max(options.outHeight, options.outWidth))/Math.log(0.5)));

                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = scale;
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options2);

                bitmapHash.put(urlStr, bitmap);
            }

            return bitmap;
        } catch (Exception e) {
            Log.d(TAG, "error message: " + e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }
}
