package com.example.android.movieapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.movieapp.MovieData.MovieData;
import com.example.android.movieapp.MovieData.MovieJasonData;
import com.example.android.movieapp.MovieData.MyAdapter;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<MovieJasonData> movieDataList;
    public static ArrayList<MovieJasonData> movieDataList1;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.movieicon);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id==R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        View rootView;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 final Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final GridView gridView = (GridView)rootView.findViewById(R.id.gridView);
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
                RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
                String url ="https://api.themoviedb.org/3/movie/top_rated?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3";
                StringRequest s = new StringRequest(url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                String st = response.toString();
                                Gson gson = new Gson();
                                MovieData movieData = gson.fromJson(st, MovieData.class);
                                String s1 = movieData.getResults().get(1).getOriginalTitle();

                                movieDataList = new ArrayList<MovieJasonData>();
                                final String imageUrlConst = "http://image.tmdb.org/t/p/w185";
                                for (int i =0; i<movieData.getResults().size();i++) {
                                    String posterPath = movieData.getResults().get(i).getPosterPath();
                                    Uri builtUri = Uri.parse(imageUrlConst + posterPath).buildUpon().build();
                                    movieDataList.add(new MovieJasonData(movieData.getResults().get(i).getOverview(),movieData.getResults().get(i).getReleaseDate(),
                                            movieData.getResults().get(i).getTitle(),movieData.getResults().get(i).getPosterPath(),movieData.getResults().get(i).getVoteAverage()));
                                }
                                MyAdapter jasonAdapter = new MyAdapter(movieDataList,(Activity)rootView.getContext());
                                gridView.setAdapter(jasonAdapter);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent in = new Intent(rootView.getContext(), MovieDetails.class).putExtra("position", i);
                                        in.putExtra("tab",1);
                                        startActivity(in);
                                    }
                                });
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(rootView.getContext(),error.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(s);

            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
                String url ="https://api.themoviedb.org/3/movie/popular?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3";
                StringRequest s12 = new StringRequest(url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                String st1 = response.toString();
                                Gson gson1 = new Gson();
                                MovieData movieData1 = gson1.fromJson(st1, MovieData.class);
                                movieDataList1 = new ArrayList<MovieJasonData>();
                                final String imageUrlConst = "http://image.tmdb.org/t/p/w185";
                                for (int i =0; i<movieData1.getResults().size();i++) {
                                    String posterPath = movieData1.getResults().get(i).getPosterPath();
                                    Uri builtUri = Uri.parse(imageUrlConst + posterPath).buildUpon().build();
                                    movieDataList1.add(new MovieJasonData(movieData1.getResults().get(i).getOverview(),movieData1.getResults().get(i).getReleaseDate(),
                                            movieData1.getResults().get(i).getTitle(),movieData1.getResults().get(i).getPosterPath(),movieData1.getResults().get(i).getVoteAverage()));
                                }
                                MyAdapter jasonAdapter1 = new MyAdapter(movieDataList1,(Activity)rootView.getContext());
                                gridView.setAdapter(jasonAdapter1);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent in = new Intent(rootView.getContext(), MovieDetails.class).putExtra("position", i);
                                        in.putExtra("tab",2);
                                        startActivity(in);
                                    }
                                });
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(rootView.getContext(),error.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(s12);
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "Top Rated";
                case 1:
                    return "Popular";
            }
            return null;
        }
    }
}
