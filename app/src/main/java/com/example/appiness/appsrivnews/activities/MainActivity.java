package com.example.appiness.appsrivnews.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appiness.appsrivnews.R;
import com.example.appiness.appsrivnews.adapters.ListViewAdapter;
import com.example.appiness.appsrivnews.internet.NetworkCheck;
import com.example.appiness.appsrivnews.network.ApiClient;
import com.example.appiness.appsrivnews.network.ApiInterface;
import com.example.appiness.appsrivnews.pojo.Item;
import com.example.appiness.appsrivnews.pojo.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String include = "blogger";
    private final static String limit = "30";
    private final static String sortby = "title";
    private final static String order = "a";
    private final static String format = "json";
    RecyclerView recyclerView;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        if (NetworkCheck.isConnected(MainActivity.this)) {
            callApiService();

        } else {
            showAlert();
                   }


        }

    private void showAlert() {
        Snackbar snackbar = Snackbar
                .make(constraintLayout, R.string.no_connection, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (NetworkCheck.isConnected(MainActivity.this)){
                            callApiService();
                        }else {
                            showAlert();
                        }

                    }
                });

        snackbar.show();

    }


    private void callApiService() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List> call = apiService.getList(include,limit,sortby,order,format);
        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List>call, Response<List> response) {
                java.util.List<Item> item = response.body().getItems();
                //Log.d(TAG, "Number of movies received: " + item.size());
                recyclerView.setAdapter(new ListViewAdapter(item,R.layout.news_list_row,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
