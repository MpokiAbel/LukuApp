package com.example.lukuapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class buying_dialog extends DialogFragment {
Home home;
Float salio;
EditText kiasi;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        home= (Home)getActivity();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.nunua, (ViewGroup) getView(), false);
        kiasi=viewInflated.findViewById(R.id.kiasi);
        builder.setView(viewInflated);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        home.transaction(Float.valueOf(kiasi.getText().toString()));
                       }
                })
         .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //CANCELLING TRANSACTIONS
                buying_dialog.this.getDialog().cancel();

            }
        });



        return builder.create();
    }

}
