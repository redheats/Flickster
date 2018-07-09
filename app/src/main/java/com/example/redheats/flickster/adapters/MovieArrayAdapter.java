package com.example.redheats.flickster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.redheats.flickster.R;
import com.example.redheats.flickster.models.Movie;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie > {

    Context context;

    private static class ViewHolder{
        ImageView movie_poster;
        TextView movie_title;
        TextView movie_overview;

    }

    public MovieArrayAdapter (Context context, List<Movie> movies){
        super(context,android.R.layout.simple_list_item_1,movies);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get data item for this position
        Movie movie = getItem(position);

        //Check if the existing view is being reused
        ViewHolder viewHolder; // view lookup cache stored in tag
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.movie_model, parent, false);
            viewHolder.movie_poster = convertView.findViewById(R.id.movie_poster);
            viewHolder.movie_title = convertView.findViewById(R.id.movie_title);
            viewHolder.movie_overview = convertView.findViewById(R.id.movie_overview);

            convertView.setTag(viewHolder);
        }
        else{
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Find the elements in the layout
        //ImageView movie_poster = convertView.findViewById(R.id.movie_poster);
        ////Clear out image from convertView
        //movie_poster.setImageResource(0);
        //TextView movie_title = convertView.findViewById(R.id.movie_title);
        //TextView movie_overview = convertView.findViewById(R.id.movie_overview);

        //Populate data
        //Glide.with(getContext())
        //        .load(movie.getPosterPath())
        //        .into(movie_poster);
        //movie_title.setText(movie.getOriginalTitle());
        //movie_overview.setText(movie.getOverView());

        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(getContext())
                    .load(movie.getPosterPath())
                    .into(viewHolder.movie_poster);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Glide.with(getContext())
                    .load(movie.getBackdropPoster())
                    .into(viewHolder.movie_poster);
        }

        viewHolder.movie_title.setText(movie.getOriginalTitle());
        viewHolder.movie_overview.setText(movie.getOverView());
        //Glide.with(getContext())
        //        .load(movie.getPosterPath())
        //        .into(viewHolder.movie_poster);
        // Return the completed view to render on screen
        return convertView;
    }



}
