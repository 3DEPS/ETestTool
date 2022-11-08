package com.egitoo.etesttool;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class NewPlayerDialogFragment extends DialogFragment {
    private String text = "Введите имя:";
    private String okButton = "OK";
    private String cancelButton = "Cancel";

    private String m_Text = "";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(text);

        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setTitle("Создание нового игрока")
                .setMessage(text)
                .setPositiveButton(okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        m_Text = input.getText().toString();
                        ((MainActivity) getActivity()).okClicked(m_Text);
                        dialog.cancel();
                    }
                }).setNegativeButton(cancelButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}

