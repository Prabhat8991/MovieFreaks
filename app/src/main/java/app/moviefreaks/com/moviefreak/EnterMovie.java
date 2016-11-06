package app.moviefreaks.com.moviefreak;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EnterMovie extends AppCompatActivity implements View.OnClickListener {
TextView title,rating,date,yearRelease;
    Button go;
    EditText movieName,year;
    Spinner spinner;
    static ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title=(TextView)findViewById(R.id.titleText);
        rating=(TextView)findViewById(R.id.ratingText);
        date=(TextView)findViewById(R.id.releaseDateText);
        year=(EditText)findViewById(R.id.movieYearEditText);
        yearRelease=(TextView) findViewById(R.id.releaseDateText1);
        go=(Button)findViewById(R.id.buttonGo);
        go.setOnClickListener(this);
        img=(ImageView)findViewById(R.id.imagePoster);
        movieName=(EditText)findViewById(R.id.movieNameEditText);
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonGo)
        {

            RestClient rc=new RestClient();
            rc.getApiService().getMovieDetails(movieName.getEditableText().toString(),year.getEditableText().toString(), new Callback<MovieModel>() {
                @Override
                public void success(MovieModel movieModel, Response response) {
                  /*  spinner.hide();*/
                    DownloadImagesTask downloadImagesTask=new DownloadImagesTask();
                    downloadImagesTask.execute(movieModel.getPoster());
                    title.setText(movieModel.getTitle());
                    rating.setText(movieModel.getImdbRating());
                    date.setText(movieModel.getPlot());
                    yearRelease.setText(movieModel.getReleased());

                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(),"error in retrieving",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
