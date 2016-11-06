package app.moviefreaks.com.moviefreak;

import android.app.ProgressDialog;
import android.content.Context;

public class Spinner {

   static ProgressDialog progress;
    static Context context;

    public Spinner(Context context)
    {
        this.context=context;
    }

    public static void show(){

        progress = ProgressDialog.show(context, "Fetching movie details",
                "wait for a while", true);

    }
    public static void hide(){
        progress.hide();
    }
}
