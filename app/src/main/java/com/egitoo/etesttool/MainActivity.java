package com.egitoo.etesttool;

import android.annotation.SuppressLint;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.egitoo.etesttool.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {
    private NfcAdapter nfcAdapter;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String resp;

    public TextView textView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        textView = findViewById(R.id.textView);
        textView.setText("Wait....");

    }

    private void updateTextView(final String s) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.textView);
                tv.setText(s);
            }
        });
    }

    @Override public void onResume() {
        super.onResume();
        if(nfcAdapter != null) {
            nfcAdapter.enableReaderMode(this, this,
                    NfcAdapter.FLAG_READER_NFC_A,
                    null);
        }
    }

    @Override public void onPause() {
        super.onPause();
        nfcAdapter.disableReaderMode(this);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        try {
            resp = UtilsJa.bytesToHex(isoDep.transceive(UtilsJa.hexStringToByteArray("00A4040007A0000002471001")));
            Log.println(Log.DEBUG, "onTagDiscovered", resp);
            updateTextView(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}