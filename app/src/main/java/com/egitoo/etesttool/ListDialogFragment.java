package com.egitoo.etesttool;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ListDialogFragment extends DialogFragment {
    private String[] players;

    public void setList(String[] pl){
        players = pl;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выбор Игрока")
                .setItems(players, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),
                                "Выбран игрок: " + players[which],
                                Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).selectPlayerClicked(players[which]);
                    }
                }).setNegativeButton("Выбрать", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}

