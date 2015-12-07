package com.example.ray.td_retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Path;

public class MainActivity extends AppCompatActivity {

    private Context activity;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private Realm realm;
    TextView editUser;
    Button BtnValider;
    ListView listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listuser= (ListView)findViewById(R.id.listUser);
        editUser= (TextView)findViewById(R.id.editUser);
        BtnValider = (Button)findViewById(R.id.BtnValider);

        ArrayList<User> users = new ArrayList<User>();
        realm = Realm.getInstance(MainActivity.this);
        RealmQuery<User> query = realm.where(User.class);
        RealmResults<User> result = query.findAll();
        if (result.size()> 0) {
            for (int i = 0; i < result.size(); i++) {
                User autreUser = new User();
                autreUser.setLogin(result.get(i).getLogin());
                users.add(autreUser);

            }
        }

        UserAdapter adapter = new UserAdapter(MainActivity.this, users);
        ListView listuser = (ListView)findViewById(R.id.listUser);

        listuser.setAdapter(adapter);


        BtnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //CODE UTILISE POUR RETROFIT
                 Retrofit retrofit = new Retrofit.Builder()
                         .baseUrl("https://api.github.com")
                         .addConverterFactory(GsonConverterFactory.create())
                         .build();


                 Github service = retrofit.create(Github.class);
                 Call repos = service.listRepos(editUser.getText());


                 repos.enqueue(new Callback<Repo>() {
                     @Override
                     public void onResponse(Response<Repo> response, Retrofit retrofit) {
                         Toast.makeText(getApplicationContext(), String.format("OK"), Toast.LENGTH_SHORT).show();
                         if (editUser.getText() == null){
                             Log.i("", "onResponse: votre login n'est pas rentré ");
                         }else {
                             Repo user = response.body();


                             if (response.body()== null){

                                 Log.i("reponse: ", "null" );
                             }else{
                                 Log.i("reponse: ", "" + user.getLogin());
                                 realm = Realm.getInstance(MainActivity.this);
                                 realm.beginTransaction();
                                 User userm = realm.createObject(User.class); // Create a new object
                                 userm.setLogin(user.getLogin());
                                 realm.copyToRealmOrUpdate(userm);
                                 realm.commitTransaction();
                                 ArrayList<User> users = new ArrayList<User>();


                                 RealmQuery<User> query = realm.where(User.class);
                                 RealmResults<User> result = query.findAll();
                                 if (result.size()> 0) {
                                     for (int i = 0; i < result.size(); i++) {
                                         User autreUser = new User();
                                         autreUser.setLogin(result.get(i).getLogin());
                                         users.add(autreUser);

                                     }
                                 }

                                UserAdapter adapter = new UserAdapter(MainActivity.this, users);
                                 ListView listuser = (ListView)findViewById(R.id.listUser);

                                 listuser.setAdapter(adapter);

                             }









                         }

                         //  SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                         //SharedPreferences.Editor editor = sharedPref.edit();
                         //editor.putString("username", user.getLogin().toString());
                         //editor.commit();

                         // SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                         //Log.i("username:",  settings.getString("username","null"));

                     }

                     @Override
                     public void onFailure(Throwable t) {
                         Toast.makeText(getActivity(), String.format("KO"), Toast.LENGTH_SHORT).show();
                         Log.i("", "null");
                     }

                 });

            }
        });
             }


             //UTILISATION DE LA LIBRARY REALM


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


             @Override
             public void onStart() {
                 super.onStart();


             }

             @Override
             public void onStop() {
                 super.onStop();


             }
         }
