package com.example.solarmojo13.timedshutdown;

import android.content.Intent;
import android.database.DataSetObserver;
import android.media.UnsupportedSchemeException;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final int[] NOTIFCATION_NUMS = {1,2,3,4,5,6,7,8,9,10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        Spinner spinner = (Spinner)findViewById(R.id.spnNumNotifications);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.notificationNums,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void revert(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void onItemSelected(AdapterView<?> parent,View view, int pos, long id){
        SettingsStorage.numNotifications = Integer.parseInt(parent.getItemAtPosition(pos).toString());
    }
    public void onNothingSelected(AdapterView<?> parent){

    }
}
