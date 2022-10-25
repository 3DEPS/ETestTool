package com.egitoo.etesttool;

import android.annotation.SuppressLint;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.egitoo.etesttool.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button plus_But;
    public Button min_But;
    Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        updateText(String.format(Locale.ENGLISH,"Click: %d", count));
        min_But = (Button) findViewById(R.id.minus_button);
        plus_But = (Button) findViewById(R.id.plus_button);

        plus_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));
            }
        });

        min_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count - 1;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));
            }
        });
    }

    private void updateText (String text){
        textView.setText(text);
    }
}