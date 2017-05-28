package com.andreadev.poi.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.andreadev.poi.R;

/**
 * Created by andrea on 22/10/16.
 */

public class AlertDialogHelper {

    private String TAG = getClass().getSimpleName();


    public static void showAlert(Context context, String title, String text){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(ContextCompat.getDrawable(context, R.mipmap.ic_launcher));
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void showPermissionRequestAlert(Context context, String title, String text, DialogInterface.OnClickListener listener){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(ContextCompat.getDrawable(context, R.mipmap.ic_launcher));
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setPositiveButton(context.getResources().getString(R.string.settings), listener);
        alertDialog.show();
    }
}