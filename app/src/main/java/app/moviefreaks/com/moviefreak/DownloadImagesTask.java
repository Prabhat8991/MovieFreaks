package app.moviefreaks.com.moviefreak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {
    Bitmap bm = null;
    Bitmap pic=null;
    Context context;




 
@Override 
protected Bitmap doInBackground(String... urls) {

    try {
        URL aURL = new URL(urls[0]);
        URLConnection conn = aURL.openConnection();
        conn.connect();
        InputStream is = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        bm = BitmapFactory.decodeStream(bis);
        bis.close();
        is.close();
    } catch (IOException e) {
       // Log.e("Hub", "Error getting the image from server : " + e.getMessage().toString());
    }
    return bm;
} 
 
@Override 
protected void onPostExecute(Bitmap result) {
                 // how do I pass a reference to mChart here ?
   EnterMovie.img.setImageBitmap(result);



}


}