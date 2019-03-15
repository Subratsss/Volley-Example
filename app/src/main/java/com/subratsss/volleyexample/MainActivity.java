package com.subratsss.volleyexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Movie> movieList;
    private RecyclerView.Adapter adapter;
    private DividerItemDecoration dividerItemDecoration;

    private String url = "https://api.myjson.com/bins/ys34i";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        movieList = new ArrayList<>();

        adapter = new MovieAdapter(getApplicationContext(), movieList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);

        getData();
    }


    private void getData() {
        final ProgressDialog pr = new ProgressDialog(this);
        pr.setMessage("Loading ...");
        pr.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Movie movie = new Movie();

                        movie.setTitle(jsonObject.getString("title"));
                        movie.setRating(jsonObject.getInt("rating"));
                        movie.setYear(jsonObject.getInt("releaseYear"));

                        movieList.add(movie);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        pr.dismiss();
                    }

                    adapter.notifyDataSetChanged();
                    pr.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("MainActivity","SSS error is : "+error.toString());
                pr.dismiss();

            }
        });

        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}

