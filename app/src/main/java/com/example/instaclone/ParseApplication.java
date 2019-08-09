package com.example.instaclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("didier123")
        .clientKey("didierkey123")
        .server("http://instagram-did.herokuapp.com/parse").build());
    }
}
