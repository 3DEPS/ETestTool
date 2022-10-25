package com.egitoo.etesttool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.egitoo.etesttool.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {
    Context context;
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;

    public MainActivity() {}

    public MainActivity(Context context) {
        this.context = context;
    }

    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor editor;
    public TextView textView;
    public TextView versionView;
    public Button plus_But;
    public Button min_But;
    Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        versionView = findViewById(R.id.version);
        versionView.setText(versionName);
        min_But = (Button) findViewById(R.id.minus_button);
        plus_But = (Button) findViewById(R.id.plus_button);

        mySharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mySharedPreferences.edit();

        if(mySharedPreferences.contains(APP_PREFERENCES_COUNT)) {
            count = mySharedPreferences.getInt(APP_PREFERENCES_COUNT, 0);
        } else {
            editor.putInt(APP_PREFERENCES_COUNT, count);
            editor.apply();
        }

        updateText(String.format(Locale.ENGLISH,"Click: %d", count));
        plus_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));

                editor.putInt(APP_PREFERENCES_COUNT, count);
                editor.apply();
            }
        });

        min_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count - 1;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));

                editor.putInt(APP_PREFERENCES_COUNT, count);
                editor.apply();
            }
        });
    }

    private void updateText (String text){
        textView.setText(text);
    }

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNT = "Count";
}