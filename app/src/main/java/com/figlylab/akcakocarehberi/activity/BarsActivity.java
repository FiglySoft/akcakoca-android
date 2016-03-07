package com.figlylab.akcakocarehberi.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.figlylab.akcakocarehberi.R;
import com.figlylab.akcakocarehberi.adapter.HotelsArrayAdapter;

public class BarsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar mToolBar;

    public static final String DETAIL_NAME_TAG = "detail_name";
    public static final String DETAIL_ADDRESS_TAG = "detail_address";
    public static final String DETAIL_COORDINATE_TAG = "detail_coordinate";
    public static final String DETAIL_PHONE_TAG = "detail_phone";
    public static final String DETAIL_EMAIL_TAG = "detail_email";
    public static final String DETAIL_WEB_TAG = "detail_web";
    public static final String DETAIL_PICTURE_TAG = "detail_picture";

    public static String[] barsNamesArray, barsAddressArray, barsCoordinatesArray, barsPhonesArray, barsEmailArray, barsWebArray;
    Intent intent;
    Context context;
    String detailName, detailAddress, detailCoordinate, detailPhone, detailEmail, detailWeb;
    int detailPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bars);

        setToolBar();
        fillArrays();
        fillRecylerView();
    }

    protected void setToolBar () {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(getResources().getString(R.string.title_bars_activity));
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

    private void fillRecylerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvBars);

        HotelsArrayAdapter adapter = new HotelsArrayAdapter(this, barsNamesArray, barsPhonesArray, R.array.bars_pictures);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    public void fillArrays(){
        barsNamesArray = getResources().getStringArray(R.array.bars_names);
        barsAddressArray = getResources().getStringArray(R.array.bars_address);
        barsCoordinatesArray = getResources().getStringArray(R.array.bars_coordinates);
        barsPhonesArray = getResources().getStringArray(R.array.bars_phones);
        barsEmailArray = getResources().getStringArray(R.array.bars_email);
        barsWebArray = getResources().getStringArray(R.array.bars_web);

    }

    public void setDetailValues(int position){
        detailName = barsNamesArray[position];
        detailAddress = barsAddressArray[position];
        detailCoordinate = barsCoordinatesArray[position];
        detailPhone = barsPhonesArray[position];
        detailEmail = barsEmailArray[position];
        detailWeb = barsWebArray[position];

        TypedArray img = getResources().obtainTypedArray(R.array.bars_pictures);
        img.getResourceId(position, -1);
        detailPicture = img.getResourceId(position, -1);
    }

    public void sendValues(){
        intent = new Intent(BarsActivity.this, HotelDetailActivity.class);
        intent.putExtra(DETAIL_NAME_TAG, detailName);
        intent.putExtra(DETAIL_ADDRESS_TAG, detailAddress);
        intent.putExtra(DETAIL_COORDINATE_TAG, detailCoordinate);
        intent.putExtra(DETAIL_PHONE_TAG, detailPhone);
        intent.putExtra(DETAIL_EMAIL_TAG, detailEmail);
        intent.putExtra(DETAIL_WEB_TAG, detailWeb);
        intent.putExtra(DETAIL_PICTURE_TAG, detailPicture);

        startActivity(intent);
    }

    public void hotelClicked(View view){
        int position = recyclerView.getChildLayoutPosition(view);
        setDetailValues(position);
        sendValues();
    }
}

