package com.figlylab.akcakocarehberi.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.figlylab.akcakocarehberi.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;


public class MapActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ProgressDialog dialog;
    GoogleMap googleMap;
    Spinner spinner;

    List<String> coordinateList;
    List<String> nameList;
    String[] spinnerArray;
    Toolbar mToolBar;

    final LatLng istanbulCoordinates = new LatLng(40.9894351,29.0409787);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        spinnerArray = getResources().getStringArray(R.array.spinner_items);
        initViews();
        setToolBar();
        getCafesCoordinates();
    }

    protected void setToolBar () {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(getResources().getString(R.string.title_map_activity));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initViews(){
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.please_wait_message));
        dialog.setCancelable(false);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (pos){
            case 0:
                getHotelsCoordinates();
                break;
            case 1:
                getResortsCoordinates();
                break;
            case 2:
                getHostelsCoordinates();
                break;
            case 3:
                getRestaurantsCoordinates();
                break;
            case 4:
                getCafesCoordinates();
                break;
            case 5:
                getBarsCoordinates();
                break;
            case 6:
                getBeachesCoordinates();
                break;
            case 7:
                getHistoricalPlacesCoordinates();
                break;
            case 8:
                getMustSeeCoordinates();
                break;
            case 9:
                getHospitalsCoordinates();
                break;
            case 10:
                getPharmacyCoordinates();
                break;
            default:
                getCafesCoordinates();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void getCafesCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.cafes_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.cafes_names));

        parseCoordinates();
    }

    private void getBarsCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.bars_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.bars_names));

        parseCoordinates();
    }

    private void getHotelsCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.hotels_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.hotels_names));

        parseCoordinates();
    }

    private void getBeachesCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.beaches_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.beaches_names));

        parseCoordinates();
    }

    private void getHistoricalPlacesCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.historical_places_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.historical_places_names));

        parseCoordinates();
    }

    private void getHostelsCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.hostels_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.hostels_names));

        parseCoordinates();
    }

    private void getResortsCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.resorts_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.resorts_names));

        parseCoordinates();
    }

    private void getHospitalsCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.hospitals_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.hospitals_names));

        parseCoordinates();
    }

    private void getPharmacyCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.pharmacy_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.pharmacy_names));

        parseCoordinates();
    }

    private void getRestaurantsCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.restaurant_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.restaurant_names));

        parseCoordinates();
    }

    private void getMustSeeCoordinates(){
        dialog.show();
        coordinateList = Arrays.asList(getResources().getStringArray(R.array.mustsee_coordinates));
        nameList = Arrays.asList(getResources().getStringArray(R.array.mustsee_names));

        parseCoordinates();
    }



    private void parseCoordinates(){
        if(googleMap != null)
            googleMap.clear();

        if(googleMap == null){
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }

        if(googleMap != null){
            for (int i = 0; i<coordinateList.size(); i++){
                String LatLngString = coordinateList.get(i);
                String[] parts = LatLngString.split(",");
                String part1 = parts[0];
                String part2 = parts[1];

                double latitude = Double.parseDouble(part1);
                double longitude = Double.parseDouble(part2);

                addMarker(latitude, longitude, nameList.get(i));
            } // end for
        }//end if

        createMap();
    }

    private void addMarker(Double latitude, Double longitude, String name){
        LatLng coordinate = new LatLng(latitude,longitude);
        googleMap.addMarker(new MarkerOptions().position(coordinate).title(name));
    }

    private void createMap(){
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(istanbulCoordinates);
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(10);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
        dialog.hide();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
