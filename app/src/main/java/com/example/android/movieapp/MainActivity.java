package com.example.android.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<MovieJasonData> movieDataList;
    public static ArrayList<MovieJasonData> movieDataList1;
    public static ArrayList<MovieJasonData> movieDataList2;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    public static Activity activityMain;
    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.movieicon);
            activityMain = this;
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);


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
            Intent in = new Intent(MainActivity.this, About.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {
        View rootView;
        DBHelper dbHelper;
        public boolean isTablet;
        GridView gridView;
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
            final View rootView1 = inflater.inflate(R.layout.fragment_main1, container, false);
            final View rootView2 = inflater.inflate(R.layout.fragment_main2, container, false);
            if (rootView.findViewById(R.id.frame_LayoutDetailed) == null) {
                isTablet = false;

            } else {
                isTablet = true;
            }

            dbHelper = new DBHelper(getActivity());
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                gridView = (GridView) rootView.findViewById(R.id.gridView);
                RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
                String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3";
                StringRequest s = new StringRequest(url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                String st = response.toString();
                                Gson gson = new Gson();
                                final MovieData movieData = gson.fromJson(st, MovieData.class);
                                String s1 = movieData.getResults().get(1).getOriginalTitle();

                                movieDataList = new ArrayList<MovieJasonData>();
                                final ArrayList<String> list= new ArrayList<String>();
                                final String imageUrlConst = "http://image.tmdb.org/t/p/w185";
                                for (int i = 0; i < movieData.getResults().size(); i++) {
                                    String posterPath = movieData.getResults().get(i).getPosterPath();
                                    getMyTrailerList getMyTrailerList = new getMyTrailerList("https://api.themoviedb.org/3/movie/"+movieData.getResults().get(i).getId()+"/videos?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3",rootView.getContext());
                                    getMyReviews getMyReviews = new getMyReviews("https://api.themoviedb.org/3/movie/"+movieData.getResults().get(i).getId()+"/reviews?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3",rootView.getContext());
                                    Uri builtUri = Uri.parse(imageUrlConst + posterPath).buildUpon().build();
                                    movieDataList.add(new MovieJasonData(movieData.getResults().get(i).getOverview(), movieData.getResults().get(i).getReleaseDate(),
                                            movieData.getResults().get(i).getTitle(), movieData.getResults().get(i).getPosterPath(), movieData.getResults().get(i).getVoteAverage(), movieData.getResults().get(i).getId(),getMyTrailerList.getTrailerList(),getMyReviews.getAuthorReviews(),getMyReviews.getReviews()));
                                }
                                MyAdapter jasonAdapter = new MyAdapter(movieDataList, (Activity) rootView.getContext());
                                gridView.setAdapter(jasonAdapter);

                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (!isTablet) {
                                            Intent in = new Intent(rootView.getContext(), MovieDetails.class).putExtra("position", i);
                                            in.putExtra("tab", 1);
                                            in.putExtra("isTablet",isTablet);
                                            startActivity(in);
                                           } else {
                                            MovieFragmentData mv = new MovieFragmentData();
                                            Bundle b =new Bundle();
                                            b.putInt("tab",1);
                                            b.putInt("position",i);
                                            mv.setArguments(b);
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_LayoutDetailed, mv).commit();

                                        }
                                    }
                                });
                                gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()

                                {
                                    @Override
                                    public boolean onItemLongClick
                                            (AdapterView<?> adapterView, View view, int i, long l) {

                                        if (dbHelper.addMovie(i, movieDataList.get(i).getTitle(), movieDataList.get(i).getImage(), movieDataList.get(i).getDescription()
                                                , movieDataList.get(i).getRate(), movieDataList.get(i).getReleaseDate(), movieDataList.get(i).getID()) == true) {
                                            Toast.makeText(rootView.getContext(), "Movie added to Favorites", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(rootView.getContext(), "Movie is already added!", Toast.LENGTH_LONG).show();
                                        }
                                        return true;
                                    }
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(rootView.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(s);
                return rootView;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                gridView = (GridView) rootView1.findViewById(R.id.gridView1);
                RequestQueue queue = Volley.newRequestQueue(rootView1.getContext());
                String url = "https://api.themoviedb.org/3/movie/popular?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3";
                StringRequest s12 = new StringRequest(url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                String st1 = response.toString();
                                Gson gson1 = new Gson();
                                final MovieData movieData1 = gson1.fromJson(st1, MovieData.class);
                                movieDataList1 = new ArrayList<MovieJasonData>();


                                final String imageUrlConst = "http://image.tmdb.org/t/p/w185";
                                for (int i = 0; i < movieData1.getResults().size(); i++) {
                                    String posterPath = movieData1.getResults().get(i).getPosterPath();
                                    Uri builtUri = Uri.parse(imageUrlConst + posterPath).buildUpon().build();
                                    getMyTrailerList getMyTrailerList = new getMyTrailerList("https://api.themoviedb.org/3/movie/"+movieData1.getResults().get(i).getId()+"/videos?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3",rootView1.getContext());
                                    getMyReviews getMyReviews = new getMyReviews("https://api.themoviedb.org/3/movie/"+movieData1.getResults().get(i).getId()+"/reviews?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3",rootView1.getContext());
                                    movieDataList1.add(new MovieJasonData(movieData1.getResults().get(i).getOverview(), movieData1.getResults().get(i).getReleaseDate(),
                                            movieData1.getResults().get(i).getTitle(), movieData1.getResults().get(i).getPosterPath(), movieData1.getResults().get(i).getVoteAverage(), movieData1.getResults().get(i).getId(), getMyTrailerList.getTrailerList(), getMyReviews.getAuthorReviews(),getMyReviews.getReviews()));
                                }
                                MyAdapter jasonAdapter1 = new MyAdapter(movieDataList1, (Activity) rootView1.getContext());
                                gridView.setAdapter(jasonAdapter1);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (!isTablet) {
                                            Intent in = new Intent(rootView1.getContext(), MovieDetails.class).putExtra("position", i);
                                            in.putExtra("tab", 2);
                                            in.putExtra("isTablet",isTablet);
                                            startActivity(in);
                                        } else {
                                            MovieFragmentData mv = new MovieFragmentData();
                                            Bundle b =new Bundle();
                                            b.putInt("tab",2);
                                            b.putInt("position",i);
                                            mv.setArguments(b);
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_LayoutDetailed1, mv).commit();

                                        }
                                    }
                                });
                                gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (dbHelper.addMovie(50 + i, movieDataList1.get(i).getTitle(), movieDataList1.get(i).getImage(), movieDataList1.get(i).getDescription()
                                                , movieDataList1.get(i).getRate(), movieDataList1.get(i).getReleaseDate(), movieDataList1.get(i).getID()) == true) {
                                            Toast.makeText(rootView.getContext(), "Movie added to Favorites", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(rootView.getContext(), "Movie is already added!", Toast.LENGTH_LONG).show();
                                        }
                                        return true;
                                    }
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(rootView.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(s12);
                return rootView1;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                gridView = (GridView) rootView2.findViewById(R.id.gridView2);
                Cursor cursor;
                movieDataList2 = new ArrayList<MovieJasonData>();
                int i = 0;

                cursor = dbHelper.getMovie();
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            getMyTrailerList getMyTrailerList = new getMyTrailerList("https://api.themoviedb.org/3/movie/"+cursor.getInt(6)+"/videos?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3",rootView2.getContext());
                            getMyReviews getMyReviews = new getMyReviews("https://api.themoviedb.org/3/movie/"+cursor.getInt(6)+"/reviews?api_key=844c4fbfdd78fc1f6a130a0fd63d64a3",rootView.getContext());
                            movieDataList2.add(new MovieJasonData(cursor.getString(3), cursor.getString(5), cursor.getString(1),
                                   cursor.getString(2), (cursor.getDouble(4)), cursor.getInt(5), getMyTrailerList.getTrailerList(), getMyReviews.getAuthorReviews(), getMyReviews.getReviews()));
                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {

                }
                MyAdapter myAdapter1 = new MyAdapter(movieDataList2, (Activity) rootView2.getContext());
                gridView.setAdapter(myAdapter1);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (!isTablet) {
                            Intent in = new Intent(rootView2.getContext(), MovieDetails.class).putExtra("position", i);
                            in.putExtra("tab", 3);
                            in.putExtra("isTablet",isTablet);
                            startActivity(in);
                        } else {
                            MovieFragmentData mv = new MovieFragmentData();
                            Bundle b =new Bundle();
                            b.putInt("tab",3);
                            b.putInt("position",i);
                            mv.setArguments(b);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_LayoutDetailed2, mv).commit();

                        }
                    }
                });
                gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Cursor c = dbHelper.getMovie();
                        c.move(i);
                        int id = c.getInt(0);
                        dbHelper.deleteMovie(id);
                        movieDataList2.remove(i);
                        MyAdapter myAdapter1 = new MyAdapter(movieDataList2, (Activity) rootView.getContext());
                        gridView.setAdapter(myAdapter1);
                        Toast.makeText(rootView.getContext(), "Movie Deleted", Toast.LENGTH_LONG).show();

                        return true;
                    }
                });
                return rootView2;

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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "Top Rated";
                case 1:
                    return "Popular";
                case 2:
                    return "Favorites";
            }
            return null;
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}
