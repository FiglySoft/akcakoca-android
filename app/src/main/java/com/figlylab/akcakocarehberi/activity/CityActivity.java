package com.figlylab.akcakocarehberi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.figlylab.akcakocarehberi.R;


/**
 * Created by servetasutay on 10/11/15.
 */
public class CityActivity extends AppCompatActivity {

    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar();
    }

    protected void setToolBar () {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(getResources().getString(R.string.title_city_activity));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    public void hotelsButtonClicked(View view){
        //Intent intent = new Intent(CityActivity.this, HotelsActivity.class);
        //startActivity(intent);
        Intent intent = new Intent(CityActivity.this, HotelsMenuActivity.class);
        startActivity(intent);
    }

    public void restaurantsButtonClicked(View view){
        Intent intent = new Intent(CityActivity.this, RestaurantsActivity.class);
        startActivity(intent);
    }

    public void cafesButtonClicked(View view){
        Intent intent = new Intent(CityActivity.this, CafesMenuActivity.class);
        startActivity(intent);
    }

    public void beachesButtonClicked(View view){
        Intent intent = new Intent(CityActivity.this, BeachesActivity.class);
        startActivity(intent);
    }

}
