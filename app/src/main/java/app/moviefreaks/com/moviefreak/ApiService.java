package app.moviefreaks.com.moviefreak;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

  @GET("/")
  public void getMovieDetails(@Query("t") String movie,@Query("y") String year, Callback<MovieModel> callback);

}