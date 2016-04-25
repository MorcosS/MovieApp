package com.example.android.movieapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapp.MovieData.MovieJasonData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MorcosS on 4/25/16.
 */
public class ReviewsAdapter extends BaseAdapter {
    ArrayList<String> list;
    ArrayList<String> list1;
    LayoutInflater inflater;
    Activity activity;

    public ReviewsAdapter(ArrayList<String> list,ArrayList<String> list1, Activity activity) {
        inflater = activity.getLayoutInflater();
        this.list = list;
        this.activity = activity;
        this.list1=list1;
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
        view = inflater.inflate(R.layout.reviews_list_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView10);
        TextView tv1 = (TextView) view.findViewById(R.id.textView11);
        tv.setText(list.get(i));
        tv1.setText(list1.get(i));
        return view;

    }






}
