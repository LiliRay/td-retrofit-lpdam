package com.example.ray.td_retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ray on 22/11/15.
 */
public interface Github {



    @GET("/users/{user}")

    Call<Repo> listRepos(@Path("user") String user);

    //public void getFeed(@Path("user") String user, Callback<Repo> response);


    //Github service = retrofit.create(Github.class);
    //Call<List<Repo>>repos = service.listRepos("LiliRay");



}





