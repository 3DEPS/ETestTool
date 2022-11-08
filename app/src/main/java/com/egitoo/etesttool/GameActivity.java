package com.egitoo.etesttool;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;



public class GameActivity extends AppCompatActivity {
    Context context;
    String versionName = BuildConfig.VERSION_NAME;

    ShowDialogFragment myDialogFragment;
    public TextView textView;
    public TextView versionView;
    public Button plus_But;
    public Button min_But;
    Integer count = 0;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        versionView = findViewById(R.id.version);
        versionView.setText(versionName);
        min_But = (Button) findViewById(R.id.minus_button);
        plus_But = (Button) findViewById(R.id.plus_button);

        Thread thread = new CheckCount();
        thread.start();

        updateText(String.format(Locale.ENGLISH,"Click: %d", count));

        plus_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));
            }
        });

        min_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                updateText(String.format(Locale.ENGLISH,"Click: %d", count));
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
                    myDialogFragment.setText(String.format(Locale.ENGLISH, "%s вы достигли: %d !!!",names[getRandomIntegerBetweenRange(0,9)], count));
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