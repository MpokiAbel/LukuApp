package com.example.lukuapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class deposit_dialog extends DialogFragment {
    Home home;
    EditText salio;
    

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        home= (Home)getActivity();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //LayoutInflater inflater = requireActivity().getLayoutInflater();
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.weka_salio, (ViewGroup) getView(), false);
        salio=viewInflated.findViewById(R.id.salio);
        final float value=Float.valueOf(home.current_price);

        builder.setView(viewInflated);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final float sumvalue=value+Float.valueOf(salio.getText().toString());
                        home.textView.setText("Salio Lako ni "+sumvalue);
                        home.myReference.setValue(String.valueOf(sumvalue));

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCELLING TRANSACTIONS
                        deposit_dialog.this.getDialog().cancel();
                    }
                });



        return builder.create();
    }


}
