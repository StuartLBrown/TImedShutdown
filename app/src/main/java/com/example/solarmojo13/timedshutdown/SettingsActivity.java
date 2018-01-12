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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        final CheckBox checkBox = (CheckBox)findViewById(R.id.chkTime);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    SettingsStorage.timeLayout=true;
                }
                else SettingsStorage.timeLayout=false;
            }
        });
    }
    public void revert(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
