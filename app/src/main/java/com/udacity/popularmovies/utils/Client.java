package com.udacity.popularmovies.utils;

import android.content.Context;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.entities.MovieOrg;
import com.udacity.popularmovies.entities.Reviews;
import com.udacity.popularmovies.entities.Video;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by vaibhav on 18/2/16.
 */
public class Client {

    RetrofitConnector service;
    Context context;

    public Client(Context context) {
        this.context = context;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
        service = retrofit.create(RetrofitConnector.class);
    }

    public Call<MovieOrg> getMoviesDetails(String sortingKey) {
        Call<MovieOrg> movieDetailsByPopularity = service.getMovieDetailsByPopularity(context.getString(R.string.api_key), sortingKey);
        return movieDetailsByPopularity;
    }

    public Call<Video> getVideoDetails(String id) {
        Call<Video> videoCall =  service.getVideoDetails(id, context.getString(R.string.api_key));
        return videoCall;
    }

    public Call<Reviews> getReviewDetails(String id) {
        Call<Reviews> reviewDetails =  service.getReviewDetails(id, context.getString(R.string.api_key));
        return reviewDetails;
    }
}


