package com.example.ray.td_retrofit;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

import io.realm.RealmObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Path;

/**
 * Created by ray on 22/11/15.
 */
public class Repo {

   @SerializedName("login")

   @Expose
   private String login;

    public Repo(String login) {
        this.login= login;


    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}