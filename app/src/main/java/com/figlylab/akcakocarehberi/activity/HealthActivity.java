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
 * Created by onurseref on 14/11/15.
 */
public class HealthActivity extends AppCompatActivity {

    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar();
    }

    protected void setToolBar () {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(getResources().getString(R.string.title_health_activity));
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

    public void hospitalsButtonClicked(View view){
        Intent intent = new Intent(HealthActivity.this, HospitalsActivity.class);
        startActivity(intent);
    }

    public void pharmacyButtonClicked(View view){
        Intent intent = new Intent(HealthActivity.this, PharmacyActivity.class);
        startActivity(intent);
    }


}
