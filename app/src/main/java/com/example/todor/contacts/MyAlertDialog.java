package com.example.todor.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todor.contacts.activity.EditContactActivity;
import com.example.todor.contacts.activity.MainActivity;

import java.util.Arrays;

/**
 * Created by todor on 16.07.15.
 */
public class MyAlertDialog extends DialogFragment {
    private static DataBase dataBase;
    public static MyAlertDialog newInstance(String name, String phone) {
        MyAlertDialog dialog = new MyAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("phone", phone);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == 1) {
            ((MainActivity) getActivity()).show();
        }
    }

    public Dialog onCreateDialog(final Bundle bundle) {
        dataBase = new DataBase(getActivity());
        final String name = getArguments().getString("name");
        final String phone = getArguments().getString("phone");

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit contact");
        builder.setItems(R.array.dialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            String[] dialogItems = getResources().getStringArray(R.array.dialogItems);
            if (which == Arrays.asList(dialogItems).indexOf("Update")) {
                Intent intent = new Intent(getActivity(), EditContactActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                getActivity().startActivityForResult(intent, 1);
            } else if (which == Arrays.asList(dialogItems).indexOf("Delete")) {
                dataBase.deleteContact(name, phone);
                ((MainActivity) getActivity()).show();
                Toast.makeText(getActivity(), "Contact was removed", Toast.LENGTH_SHORT).show();
            }
            }
        });
        return builder.create();
    }


}
