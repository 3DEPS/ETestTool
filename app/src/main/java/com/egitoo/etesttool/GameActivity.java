package com.egitoo.etesttool;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;



public class GameActivity extends AppCompatActivity {
    Context context;
    String versionName = BuildConfig.VERSION_NAME;
    private static final String APP_PREFERENCES = "mysettings";

    String currentPlayer;

    CountPreferences preferences;

    ShowDialogFragment myDialogFragment;
    public TextView textView;
    public TextView versionView;
    public MotionLayout motionLayout;
    public Button plus_But;
    public Button min_But;
    Integer count;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();

        preferences = new CountPreferences(getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE));
        if(arguments!=null) {
            currentPlayer = arguments.getString("currentPlayer");
            try {
                count = preferences.getCount(currentPlayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        versionView = findViewById(R.id.version);
        versionView.setText(versionName);
        min_But = (Button) findViewById(R.id.minus_button);
        plus_But = (Button) findViewById(R.id.plus_button);
        motionLayout = (MotionLayout) findViewById(R.id.motionLayout);

        Thread thread = new CheckCount();
        thread.start();

        updateText(String.format(Locale.ENGLISH,"Click: %d", count));

        plus_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));
                try {

                    preferences.updateCount(currentPlayer, count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        min_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));
                try {
                    preferences.updateCount(currentPlayer, count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateText (String text){
        textView.setText(text);
    }

    class CheckCount extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {}
                if (count % 100 == 0 && !flag) {
                    flag = true;
                    myDialogFragment = new ShowDialogFragment();
                    myDialogFragment.setText(String.format(Locale.ENGLISH, "%s вы достигли: %d !!!",currentPlayer, count));
                    myDialogFragment.setTextButton("Погнали!!!");
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    myDialogFragment.show(transaction, "dialog");
                } else if(count % 100 != 0) {
                    flag = false;
                }
            }
        }
    }

    public static int getRandomIntegerBetweenRange(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}