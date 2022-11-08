package com.egitoo.etesttool;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ShowDialogFragment extends DialogFragment {
    private String title;
    private String text;
    private String textButton;

    public void setTitle(String title) { this.title = title; }

    public void setText(String text) { this.text = text; }

    public void setTextButton(String text) { this.textButton = text; }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(text)
                .setPositiveButton(textButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}

