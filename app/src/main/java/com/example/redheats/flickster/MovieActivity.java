package com.example.redheats.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.redheats.flickster.adapters.MovieArrayAdapter;
import com.example.redheats.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView Lvitems;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Lvitems = findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        Lvitems.setAdapter(movieAdapter);

        Lvitems.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (Lvitems.getLastVisiblePosition() - Lvitems.getHeaderViewsCount() -
                        Lvitems.getFooterViewsCount()) >= (movieAdapter.getCount() - 1)) {
                    // Now your listview has hit the bottom
                    page++;
                    loadData(page);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        loadData(page);
    }

    private void loadData(int page) {
        AsyncHttpClient  client = new AsyncHttpClient();
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&page="+page,
                new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                JSONArray  mouvieJsonResults;
                try {
                    mouvieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fronJSONArray(mouvieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG" ,movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
