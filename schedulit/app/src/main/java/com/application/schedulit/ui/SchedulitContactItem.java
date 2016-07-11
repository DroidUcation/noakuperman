package com.application.schedulit.ui;

import android.net.Uri;

/**
 * Created by yosinoa on 10/07/2016.
 */
public class SchedulitContactItem {

    String id;
    String name;
    String phone;
    Uri imageUri;

    public void setId (String id) {this.id = id;}

    public void setName (String name) {this.name = name;}

    public void setPhone (String phone) {this.phone = phone;}

    public void setImageUri(Uri imageUri){this.imageUri = imageUri;}

    public String getId (){return this.id;}

    public String getPhone (){return this.phone;}

    public String getName (){return this.name;}

    public Uri getImageUri(){return  this.imageUri;}
}
