package com.figlylab.akcakocarehberi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.figlylab.akcakocarehberi.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    CoordinatorLayout rootLayout;
    FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initInstances();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Akçakoca");
    }

    public void cityButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, CityActivity.class);
        startActivity(intent);
    }

    public void historicalButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, HistoricalPlacesActivity.class);
        startActivity(intent);
    }

    public void mustSeeButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, MustSeeActivity.class);
        startActivity(intent);
    }

    public void healthButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, HealthActivity.class);
        startActivity(intent);
    }

    public void transportationButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, TransportationActivity.class);
        startActivity(intent);
    }

    public void emergencyButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, EmergencyActivity.class);
        startActivity(intent);
    }

    public void mapButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    public void aboutUsClicked(View view){
        Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void hospitalFabClicked(View view){
        Intent intent = new Intent(MainActivity.this, HospitalsActivity.class);
        startActivity(intent);
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
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
