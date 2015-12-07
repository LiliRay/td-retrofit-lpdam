package com.example.ray.td_retrofit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lydie ray on 12/10/2015.
 */
public class UserAdapter extends BaseAdapter {

    private ArrayList<User> listUser;
    private User unuser;


    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

//
    public UserAdapter(Context context, ArrayList<User> ListUser) {
        mContext = context;
        this.listUser = ListUser;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return this.listUser.size();
    }

    public Object getItem(int position) {
        return listUser.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.listuser_main, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }





        TextView TxtLogin = (TextView)layoutItem.findViewById(R.id.textLogin);
        unuser= listUser.get(position);
        TxtLogin.setText( listUser.get(position).getLogin());



            //(3) : Renseignement des valeurs





            //On retourne l'item créé.
            return layoutItem;


    }

}
