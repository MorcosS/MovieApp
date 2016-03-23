package com.example.android.movieapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MovieFragmentData extends Fragment {


    public MovieFragmentData() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_movie_fragment_data, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        TextView textView = (TextView) view.findViewById(R.id.textView2);
        TextView textView1 = (TextView) view.findViewById(R.id.textView3);
        TextView textView2 = (TextView) view.findViewById(R.id.textView4);
        TextView textView3 = (TextView) view.findViewById(R.id.textView5);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        Context context = getActivity().getApplicationContext();
        Intent in = getActivity().getIntent();
        int position = in.getIntExtra("position",0);
        if(in.getIntExtra("tab",0)==1) {
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + MainActivity.movieDataList.get(position).getImage()).into(imageView);
            textView.setText(MainActivity.movieDataList.get(position).getDescription());
            textView1.setText((MainActivity.movieDataList.get(position).getTitle()));
            textView2.setText((MainActivity.movieDataList.get(position).getRate()) + "/10");
            textView3.setText(textView3.getText()+MainActivity.movieDataList.get(position).getReleaseDate().split("-")[2]+"-"+MainActivity.movieDataList.get(position).getReleaseDate().split("-")[1]+"-"+MainActivity.movieDataList.get(position).getReleaseDate().split("-")[0]);
            float f = (float) (MainActivity.movieDataList.get(position).getRate() / 2);
            ratingBar.setRating(f);
        }else if(in.getIntExtra("tab",0)==2){
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + MainActivity.movieDataList1.get(position).getImage()).into(imageView);
            textView.setText(MainActivity.movieDataList1.get(position).getDescription());
            textView1.setText((MainActivity.movieDataList1.get(position).getTitle()));
            textView2.setText((MainActivity.movieDataList1.get(position).getRate()) + "/10");
            textView3.setText(textView3.getText()+MainActivity.movieDataList1.get(position).getReleaseDate().split("-")[2]+"-"+MainActivity.movieDataList1.get(position).getReleaseDate().split("-")[1]+"-"+MainActivity.movieDataList1.get(position).getReleaseDate().split("-")[0]);
            float f = (float) (MainActivity.movieDataList1.get(position).getRate() / 2);
            ratingBar.setRating(f);
        }
        return view;
    }



}
