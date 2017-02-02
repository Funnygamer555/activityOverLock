package com.example.anh_tuan.activityoverlock;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by anh-tuan on 01/02/17.
 */

public class DialogWindow extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        Activity a= getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setMessage("fire misslies")
                .setPositiveButton("fire", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        System.exit(0);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
