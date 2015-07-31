package com.example.todor.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todor.contacts.DataBase;
import com.example.todor.contacts.activity.AddContactActivity;

/**
 * Created by todor on 18.07.15.
 */
public class MyCustomDialog extends DialogFragment {
    CustomDialog customDialog;

    public static MyCustomDialog newInstance(String title) {
        MyCustomDialog dialog = new MyCustomDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(title);

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                passData("Yes");
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                passData("No");
            }
        });
        return dialog.create();
    }

    public interface CustomDialog {
        void onButtonClick(String nameButton, Dialog dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        customDialog = (CustomDialog) activity;
    }

    public void passData(String data) {
        customDialog.onButtonClick(data, getDialog());
    }
}
