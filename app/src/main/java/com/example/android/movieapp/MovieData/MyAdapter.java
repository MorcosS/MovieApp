package com.example.android.movieapp.MovieData;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MorcosS on 3/23/16.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<MovieJasonData> list;
    LayoutInflater inflater;
    Activity activity;

    public MyAdapter(ArrayList<MovieJasonData> list, Activity activity) {
        inflater = activity.getLayoutInflater();
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.movie_item,null);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        Picasso.with(activity).load("http://image.tmdb.org/t/p/w185"+list.get(i).getImage()).into(image);
        textView.setText(list.get(i).getTitle());
        return view;

    }
}
