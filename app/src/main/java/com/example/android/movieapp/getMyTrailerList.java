package com.example.android.movieapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MorcosS on 4/24/16.
 */
public class getMyTrailerList {
    final ArrayList<String> trailerList;
    public  getMyTrailerList(String url,Context context){
        trailerList = new ArrayList<String>();
        RequestQueue queue1 = Volley.newRequestQueue(context);
        StringRequest s12 = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // myList.clear();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("results");
                    for(int j=0;j<jsonArray.length();j++) {
                        trailerList.add(j,jsonArray.getJSONObject(j).get("key").toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     }
                });
        queue1.add(s12);
    }

    public ArrayList<String> getTrailerList(){
        return trailerList;
    }
}
