package com.example.redheats.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {

    /*
    {

    "vote_count": 1486,
    "id": 351286,
    "video": false,
    "vote_average": 6.6,
    "title": "Jurassic World: Fallen Kingdom",
    "popularity": 294.284,
    "poster_path": "/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg",
    "original_language": "en",
    "original_title": "Jurassic World: Fallen Kingdom",
    "genre_ids": [
        28,
        12,
        878
    ],
    "backdrop_path": "/gBmrsugfWpiXRh13Vo3j0WW55qD.jpg",
    "adult": false,
    "overview": "Several years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the island of Isla Nublar. Claire Dearing, the former park manager and founder of the Dinosaur Protection Group, recruits Owen Grady to help prevent the extinction of the dinosaurs once again.",
    "release_date": "2018-06-06"

    }
    */

    String posterPath;
    String originalTitle;
    String overView;
    String backdropPoster;

    public String getBackdropPoster() {
        return  String.format("https://image.tmdb.org/t/p/w342/%s" ,backdropPoster);
    }

    public String getPosterPath() {
        return  String.format("https://image.tmdb.org/t/p/w342/%s" ,posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public Movie(JSONObject jsonObject )throws JSONException{

        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle=jsonObject.getString("original_title");
        this.overView=jsonObject.getString("overview");
        this.backdropPoster = jsonObject.getString("backdrop_path");


    }
    public static ArrayList<Movie> fronJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();


        for (int x=0 ;x<array.length(); x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return results;
    }

}
