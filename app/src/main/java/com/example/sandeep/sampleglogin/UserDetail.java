package com.example.sandeep.sampleglogin;

import android.net.Uri;

/**
 * Created by sandeep on 1/13/2017.
 */

public class UserDetail {

    String personName ;
    String firstName ;
    String lastName ;
    String email ;
    String id ;
    String idToken ;
    String auth ;
    Uri image;

    public UserDetail() {}

    public UserDetail(String personName, Uri image, String auth, String idToken, String id, String email, String lastName, String firstName) {
        this.personName = personName;
        this.image = image;
        this.auth = auth;
        this.idToken = idToken;
        this.id = id;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
