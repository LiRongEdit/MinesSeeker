package com.example.haoyup.cmpt276a3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_layout, null);

        // Create a button Listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // return main menu
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        };
        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Win")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
