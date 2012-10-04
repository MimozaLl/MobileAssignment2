package com.example.foursquareassig;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Kot extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kot, menu);
        return true;
    }
}
