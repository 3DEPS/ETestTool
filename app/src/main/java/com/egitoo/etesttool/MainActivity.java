package com.egitoo.etesttool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;



public class MainActivity extends AppCompatActivity {
    Context context;
    String versionName = BuildConfig.VERSION_NAME;
    private static final String APP_PREFERENCES = "mysettings";
    com.egitoo.etesttool.CountPreferences preferences;

    Intent intent;
    String currentPlayer;

    ShowDialogFragment successDialog;
    NewPlayerDialogFragment newPlayerDialog;
    public TextView title;
    public Button newPlayer_Bt;
    public Button selectPlayer_Bt;
    public Button startGame_Bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        title = findViewById(R.id.titleApp);

        newPlayer_Bt = (Button) findViewById(R.id.newPlayer);
        startGame_Bt = (Button) findViewById(R.id.startGame);
        selectPlayer_Bt = (Button) findViewById(R.id.playersGame);

        intent = new Intent(this, GameActivity.class);
        preferences = new CountPreferences(getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE));

        //First init
        if(preferences.isEmpty()){
            newPlayerDialog = new NewPlayerDialogFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            successDialog.show(transaction, "dialog");
        }

        //Button listener
        newPlayer_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPlayerDialog = new NewPlayerDialogFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                newPlayerDialog.show(transaction, "dialog");
            }
        });
        selectPlayer_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] result = preferences.getAllPlayer().keySet().toArray(new String[0]);
                ListDialogFragment playersDialog = new ListDialogFragment();
                playersDialog.setList(result);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                playersDialog.show(transaction, "dialog");
            }
        });
        startGame_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("currentPlayer", currentPlayer);
                try {
                    intent.putExtra("count", preferences.getCount(currentPlayer));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }

    public void okClicked(String m_Text) {
        try {
            preferences.addPlayer(m_Text, 0);
            currentPlayer = m_Text;
        } catch (Exception e){
            showDialogFragment("Ошибка", "Игрок " + m_Text + " уже существует", "Принято");
        }
    }

    public void selectPlayerClicked(String player) {
        currentPlayer = player;
        title.setText("Hello " + currentPlayer + "!");
    }

    void showDialogFragment(String title, String massage, String button){
        successDialog = new ShowDialogFragment();
        successDialog.setTitle(title);
        successDialog.setText(massage);
        successDialog.setTextButton(button);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        successDialog.show(transaction, "dialog");
    }

//    public static int getRandomIntegerBetweenRange(int min, int max){
//        int x = (int)(Math.random()*((max-min)+1))+min;
//        return x;
//    }
}