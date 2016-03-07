package com.figlylab.akcakocarehberi.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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


/**
 * Created by servetasutay on 10/11/15.
 */
public class EmergencyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar mToolBar;

    public static String[] emergencyPhoneNumbersArray, emergencyPhoneNamesArray;
    Intent intent;
    Context context;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        setToolBar();
        fillArrays();
        fillRecylerView();

    }

    protected void setToolBar () {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setTitle(getResources().getString(R.string.title_emergency_activity));
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
        recyclerView = (RecyclerView) findViewById(R.id.rvEmergencyCalls);
        HotelsArrayAdapter adapter = new HotelsArrayAdapter(this, emergencyPhoneNamesArray, emergencyPhoneNumbersArray, R.array.emergency_pictures);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

    }

    public void fillArrays(){
        emergencyPhoneNamesArray = getResources().getStringArray(R.array.emergency_names);
        emergencyPhoneNumbersArray = getResources().getStringArray(R.array.emergency_phones);
    }

    public void setDetailValues(int position) {
        phoneNumber = emergencyPhoneNumbersArray[position];
    }

    public void hotelClicked(View view) {
        createDialog(getResources().getString(R.string.alert_dialog_call_number_message), "calling");
        int position = recyclerView.getChildLayoutPosition(view);
        setDetailValues(position);
    }

    private void createDialog(String message, final String tag){
        AlertDialog alertDialog = new AlertDialog.Builder(EmergencyActivity.this).create();
        alertDialog.setTitle(R.string.alert_dialog_warning_label);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.alert_dialog_ok_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (tag.equals("calling")) {
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:" + phoneNumber));
                            startActivity(callIntent);
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
}
