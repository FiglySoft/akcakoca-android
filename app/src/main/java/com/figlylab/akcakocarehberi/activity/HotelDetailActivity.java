package com.figlylab.akcakocarehberi.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.figlylab.akcakocarehberi.R;

public class HotelDetailActivity extends AppCompatActivity implements View.OnClickListener{

    TextView detailNameTextview, detailAddressTextview, detailPhoneTextview, detailEmailTextview, detailWebTextview;
    ImageView detailPictureImageView;

    public static final String DETAIL_NAME_TAG = "detail_name";
    public static final String DETAIL_ADDRESS_TAG = "detail_address";
    public static final String DETAIL_COORDINATE_TAG = "detail_coordinate";
    public static final String DETAIL_PHONE_TAG = "detail_phone";
    public static final String DETAIL_EMAIL_TAG = "detail_email";
    public static final String DETAIL_WEB_TAG = "detail_web";
    public static final String DETAIL_PICTURE_TAG = "detail_picture";
    public static final String URL_TAG = "url";

    String detailName, detailAddress, detailCoordinate, detailPhone, detailEmail, detailWeb;
    int detailPicture;
    String parsedPhone;
    double latitude, longitude;

    Toolbar mToolBar;

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        initViews();
        getIntentValues();
        parseLatLng();
        setContentValues();
        setToolBar();
    }

    protected void setToolBar () {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(detailName);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.detail_phone_textview) {
            createDialog(getResources().getString(R.string.alert_dialog_call_number_message), "calling");
        }

        if (id == R.id.detail_web_textview) {
            createDialog(getResources().getString(R.string.alert_dialog_web_site_message), "web_site");
        }
    }

    private void initViews() {
        detailNameTextview = (TextView) findViewById(R.id.detail_name_textview);
        detailAddressTextview = (TextView) findViewById(R.id.detail_address_textview);
        detailPhoneTextview = (TextView) findViewById(R.id.detail_phone_textview);
        detailEmailTextview = (TextView) findViewById(R.id.detail_email_textview);
        detailWebTextview = (TextView) findViewById(R.id.detail_web_textview);
        detailPictureImageView = (ImageView) findViewById(R.id.detail_image);

        detailPhoneTextview.setOnClickListener(this);
        detailWebTextview.setOnClickListener(this);
    }

    private void getIntentValues() {
        detailName = getIntent().getStringExtra(DETAIL_NAME_TAG);
        detailAddress = getIntent().getStringExtra(DETAIL_ADDRESS_TAG);
        detailCoordinate = getIntent().getStringExtra(DETAIL_COORDINATE_TAG);
        detailPhone = getIntent().getStringExtra(DETAIL_PHONE_TAG);
        detailEmail = getIntent().getStringExtra(DETAIL_EMAIL_TAG);
        detailWeb = getIntent().getStringExtra(DETAIL_WEB_TAG);
        detailPicture = getIntent().getIntExtra(DETAIL_PICTURE_TAG, 0);
    }

    private void parseLatLng() {
        String[] parts = detailCoordinate.split(",");
        latitude = Double.parseDouble(parts[0]);
        longitude = Double.parseDouble(parts[1]);

        createMap(latitude, longitude);
    }

    private void setContentValues() {
        detailNameTextview.setText(detailName);
        detailAddressTextview.setText(detailAddress);
        detailPhoneTextview.setText(detailPhone);
        detailEmailTextview.setText(detailEmail);
        detailWebTextview.setText(detailWeb);
        detailPictureImageView.setImageResource(detailPicture);
    }

    private void createDialog(String message, final String tag){
        AlertDialog alertDialog = new AlertDialog.Builder(HotelDetailActivity.this).create();
        alertDialog.setTitle(R.string.alert_dialog_warning_label);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.alert_dialog_ok_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (tag.equals("calling")) {
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:" + detailPhone));
                            startActivity(callIntent);
                        } else if (tag.equals("web_site")) {
                            Intent intent = new Intent(HotelDetailActivity.this, WebViewActivity.class);
                            intent.putExtra(URL_TAG, detailWeb);
                            startActivity(intent);
                        }
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.alert_dialog_cancel_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void createMap(Double latitude, Double longitude){
        if (googleMap == null) {
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
        if (googleMap != null) {
            LatLng coordinate = new LatLng(latitude,longitude);
            googleMap.addMarker(new MarkerOptions().position(coordinate).title(detailName));

            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
        }
    }

}