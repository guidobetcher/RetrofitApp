package com.example.citrixbib18.retrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Button fab = (Button) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
                    final Call<List<Contributor>> call =
                            gitHubService.repoContributors("square", "retrofit");

                    call.enqueue(new Callback<List<Contributor>>() {
                        @Override
                        public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                            final TextView textView = (TextView) findViewById(R.id.textView);
                            textView.setText(response.body().toString());
                        }
                        @Override
                        public void onFailure(Call<List<Contributor>> call, Throwable t) {
                            final TextView textView = (TextView) findViewById(R.id.textView);
                            textView.setText("Something went wrong: " + t.getMessage());
                        }
                    });
                }
            });
    }
}
