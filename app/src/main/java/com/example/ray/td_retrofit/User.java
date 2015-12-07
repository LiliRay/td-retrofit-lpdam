package com.example.ray.td_retrofit;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ray on 01/12/15.
 */
public class User extends RealmObject {
    @PrimaryKey
    private String login;

    private  int sessionId;



    public User() {


    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
