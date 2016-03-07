package com.figlylab.akcakocarehberi.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.figlylab.akcakocarehberi.R;


/**
 * Created by servetasutay on 10/11/15.
 */
public class AboutUsActivity extends AppCompatActivity {

    Toolbar mToolBar;
    ImageView presidentImage;
    TextView presidentMessageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        setToolBar();
    }

    protected void setToolBar () {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(getResources().getString(R.string.title_about_us_activity));
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
    private void InitViews(){
        presidentImage = (ImageView) findViewById(R.id.president_image);
        presidentMessageTitle = (TextView) findViewById(R.id.president_message_title);
    }
}
