package com.example.solarmojo13.timedshutdown;

import android.content.Intent;
import android.database.DataSetObserver;
import android.media.UnsupportedSchemeException;
import android.support.annotation.IntegerRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        final EditText editText = (EditText)findViewById(R.id.etxtMinutes);
        editText.setText("" + SettingsStorage.delay/1000/60);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                SettingsStorage.delay = Long.parseLong(s.toString())*60*1000;}
                catch (Exception e){
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                return;
            }

        });
        if(SettingsStorage.timeLayout)
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsStorage.timeLayout = checkBox.isChecked();
            }
        });
    }
    public void revert(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void onNothingSelected(AdapterView<?> parent){
        return;
    }
}
