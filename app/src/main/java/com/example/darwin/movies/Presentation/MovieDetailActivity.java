package com.example.darwin.movies.Presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darwin.movies.Domain.*;
import com.example.darwin.movies.R;
import com.example.darwin.movies.Service.*;

import java.util.ArrayList;

import static com.example.darwin.movies.Presentation.MainActivity.MOVIE_DATA;


/**
 * Created by Darwin on 3-7-2017.
 */

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener, MovieRequest.MovieListener{
    private TextView movieTitle;
    private TextView movieDescription;
    private TextView txtResponseMsg;
    private TextView movieLength;
    private TextView movieRating;
    private TextView movieReleaseYear;
    private Button rentButton;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieTitle = (TextView) findViewById(R.id.movieDetailTitle);
        movieReleaseYear = (TextView) findViewById(R.id.movieDetailYear);
        movieRating = (TextView) findViewById(R.id.movieDetailRating);
        movieLength = (TextView) findViewById(R.id.movieDetailLength);
        txtResponseMsg = (TextView) findViewById(R.id.txtResponse);
        movieDescription = (TextView) findViewById(R.id.movieDetailDescription);
        rentButton = (Button) findViewById(R.id.movieDetailRentButton);
        rentButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        Movie movie = (Movie) extras.getSerializable(MOVIE_DATA);
        Log.i(TAG, movie.toString());

        movieTitle.setText(movie.getMovietitle());
        movieDescription.setText("Description: " + movie.getDescription());
        movieReleaseYear.setText("Release Year: " + movie.getReleaseYear());
        movieRating.setText("Rating: " + movie.getRating());
        movieLength.setText("Length: " + movie.getLength() + " minutes" );
    }

    @Override
    public void onClick(View v){
        Bundle extras = getIntent().getExtras();
        Movie movie = (Movie) extras.getSerializable(MOVIE_DATA);
        MovieRequest request = new MovieRequest(getApplicationContext(), this);
        request.handleRentMovie(movie);
    }

    public void onMoviesError(String message) {
        Log.e(TAG, message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMoviesAvailable(ArrayList<Movie> movieArrayList) {

//        Log.i(TAG, "We hebben " + movieArrayList.size() + " films in de lijst");
//
//        movies.clear();
//        for(int i = 0; i < movieArrayList.size(); i++) {
//            movies.add(movieArrayList.get(i));
//        }
//        movieAdapter.notifyDataSetChanged();
    }

    /**
     * Callback function - handle a single ToDo
     *
     * @param movie
     */
    @Override
    public void onMovieAvailable(Movie movie) {
//        movies.add(movie);
//        movieAdapter.notifyDataSetChanged();
    }
    @Override
    public void onRental(String message) {
        Log.e(TAG, message);
        txtResponseMsg.setText(message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
