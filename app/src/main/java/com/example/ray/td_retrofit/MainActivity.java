package com.example.ray.td_retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Path;

public class MainActivity extends AppCompatActivity  {

    private Context activity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Github service = retrofit.create(Github.class);
        final Call repos = service.listRepos("LiliRay");
        repos.enqueue(new Callback<Repo>() {


            @Override
            public void onResponse(Response<Repo> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(), String.format("OK"), Toast.LENGTH_SHORT).show();
                    Repo user= response.body();
                    Log.i("", "response:" + user.getLogin());

                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", user.getLogin().toString());
                editor.commit();

                SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                Log.i("username:",  settings.getString("username","null"));

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), String.format("KO"), Toast.LENGTH_SHORT).show();
                Log.i("", "null");
            }

        });



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }

    public Context getActivity() {

        return activity;
    }


}
