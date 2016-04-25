package com.example.android.movieapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.*;


public class MovieFragmentData extends Fragment {
    Intent in;

    public MovieFragmentData() {
        // Required empty public constructor

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.fragment_movie_fragment_data, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        TextView textView = (TextView) view.findViewById(R.id.textView5);
        TextView textView1 = (TextView) view.findViewById(R.id.textView2);
        TextView textView2 = (TextView) view.findViewById(R.id.textView3);
        TextView textView3 = (TextView) view.findViewById(R.id.textView8);
        TextView textView4 = (TextView) view.findViewById(R.id.textView7);
        TextView textView5 = (TextView) view.findViewById(R.id.textView9);
        final ArrayList<String> myList = new ArrayList<String>();
        final ArrayList<String> myList1 = new ArrayList<String>();
        final int id;
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        Context context = getActivity().getApplicationContext();
        in = getActivity().getIntent();
        final ListView ls = (ListView) view.findViewById(R.id.listView);
        final ListView ls1 = (ListView) view.findViewById(R.id.listView1);
        final int position;
        boolean isTablet = in.getBooleanExtra("isTablet",true);
        if(isTablet){
         position=getArguments().getInt("position");
        }else{
            position = in.getIntExtra("position",0);
        }

        if((isTablet&&getArguments().getInt("tab",0)==1)||(in.getIntExtra("tab",0)==1)) {
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + MainActivity.movieDataList.get(position).getImage()).into(imageView);
            textView.setText(MainActivity.movieDataList.get(position).getDescription());
            textView1.setText((MainActivity.movieDataList.get(position).getTitle()));
            textView2.setText((MainActivity.movieDataList.get(position).getRate()) + "/10");
            textView3.setText(textView3.getText() + MainActivity.movieDataList.get(position).getReleaseDate().split("-")[2] + "-" + MainActivity.movieDataList.get(position).getReleaseDate().split("-")[1] + "-" + MainActivity.movieDataList.get(position).getReleaseDate().split("-")[0]);
            float f = (float) (MainActivity.movieDataList.get(position).getRate() / 2);
            ratingBar.setRating(f);
            id= MainActivity.movieDataList.get(position).getID();
            if(MainActivity.movieDataList.get(position).getKey().size()==0){
                textView4.setVisibility(View.GONE);
            }
            if(MainActivity.movieDataList.get(position).getReviews().size()==0){
                textView5.setVisibility(View.GONE);
            }
            for (int k=0;k<MainActivity.movieDataList.get(position).getKey().size();k++) {
                int x = k+1;
                myList1.add("Watch Trailer "+x);
            }
                        ArrayAdapter<String> arrayAdapter =
                                new ArrayAdapter<String>(getActivity(), R.layout.trailer_list_item,
                                        R.id.textView6, myList1);
                        ls.setAdapter(arrayAdapter);

                        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                      @Override
                                                      public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {
                                                          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + MainActivity.movieDataList.get(position).getKey().get(k))));
                                                      }
                                                  }

                        );

            ReviewsAdapter reviewsAdapter = new ReviewsAdapter(MainActivity.movieDataList.get(position).getReviewAuthors(),MainActivity.movieDataList.get(position).getReviews(),getActivity());
            ls1.setAdapter(reviewsAdapter);
        }else if((isTablet&&getArguments().getInt("tab",0)==2)||(in.getIntExtra("tab",0)==2)){
            if(MainActivity.movieDataList1.get(position).getKey().size()==0){
                textView4.setVisibility(View.GONE);
            }
            if(MainActivity.movieDataList1.get(position).getReviews().size()==0){
                textView5.setVisibility(View.GONE);
            }
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + MainActivity.movieDataList1.get(position).getImage()).into(imageView);
            textView.setText(MainActivity.movieDataList1.get(position).getDescription());
            textView1.setText((MainActivity.movieDataList1.get(position).getTitle()));
            textView2.setText((MainActivity.movieDataList1.get(position).getRate()) + "/10");
            textView3.setText(textView3.getText()+MainActivity.movieDataList1.get(position).getReleaseDate().split("-")[2]+"-"+MainActivity.movieDataList1.get(position).getReleaseDate().split("-")[1]+"-"+MainActivity.movieDataList1.get(position).getReleaseDate().split("-")[0]);
            float f = (float) (MainActivity.movieDataList1.get(position).getRate() / 2);
            ratingBar.setRating(f);
            id= MainActivity.movieDataList1.get(position).getID();
            for (int k=0;k<MainActivity.movieDataList1.get(position).getKey().size();k++) {
                int x = k+1;
                myList1.add("Watch Trailer "+x);
            }
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(getActivity(), R.layout.trailer_list_item,
                            R.id.textView6, myList1);
            ls.setAdapter(arrayAdapter);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {
                                              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + MainActivity.movieDataList1.get(position).getKey().get(k))));
                                          }
                                      }

            );
            ReviewsAdapter reviewsAdapter1 = new ReviewsAdapter(MainActivity.movieDataList1.get(position).getReviewAuthors(),MainActivity.movieDataList1.get(position).getReviews(),getActivity());
            ls1.setAdapter(reviewsAdapter1);
        }else if((isTablet&&getArguments().getInt("tab",0)==3)||(in.getIntExtra("tab",0)==3)){
            if(MainActivity.movieDataList2.get(position).getKey().size()==0){
                textView4.setVisibility(View.GONE);
            }
            if(MainActivity.movieDataList2.get(position).getReviews().size()==0){
                textView5.setVisibility(View.GONE);
            }
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + MainActivity.movieDataList2.get(position).getImage()).into(imageView);
            textView.setText(MainActivity.movieDataList2.get(position).getDescription());
            textView1.setText((MainActivity.movieDataList2.get(position).getTitle()));
            textView2.setText((MainActivity.movieDataList2.get(position).getRate()) + "/10");
            textView3.setText(textView3.getText()+MainActivity.movieDataList2.get(position).getReleaseDate().split("-")[2]+"-"+MainActivity.movieDataList2.get(position).getReleaseDate().split("-")[1]+"-"+MainActivity.movieDataList2.get(position).getReleaseDate().split("-")[0]);
            float f = (float) (MainActivity.movieDataList2.get(position).getRate() / 2);
            id= MainActivity.movieDataList2.get(position).getID();
            ratingBar.setRating(f);
            for (int k=0;k<MainActivity.movieDataList2.get(position).getKey().size();k++) {
                int x = k+1;
                myList1.add("Watch Trailer "+x);
            }
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(getActivity(), R.layout.trailer_list_item,
                            R.id.textView6, myList1);
            ls.setAdapter(arrayAdapter);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {
                                              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + MainActivity.movieDataList2.get(position).getKey().get(k))));
                                          }
                                      }

            );
            ReviewsAdapter reviewsAdapter2 = new ReviewsAdapter(MainActivity.movieDataList2.get(position).getReviewAuthors(),MainActivity.movieDataList2.get(position).getReviews(),getActivity());
            ls1.setAdapter(reviewsAdapter2);
            }
        return view;
    }



}
