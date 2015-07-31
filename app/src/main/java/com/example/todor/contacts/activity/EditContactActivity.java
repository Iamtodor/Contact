package com.example.todor.contacts.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todor.contacts.DataBase;
import com.example.todor.contacts.MyCustomDialog;
import com.example.todor.contacts.R;

/**
 * Created by todor on 16.07.15.
 */
public class EditContactActivity extends AppCompatActivity implements MyCustomDialog.CustomDialog{
    
    private EditText editName, editPhone;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        Button addButton = (Button) findViewById(R.id.addEditButton);
        dataBase = new DataBase(this);

        editName.setText(getIntent().getStringExtra("name"));
        editPhone.setText(getIntent().getStringExtra("phone"));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(EditContactActivity.this);
                showAlertDialog();
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onButtonClick(String nameButton, Dialog dialog) {
        if(nameButton.equals("Yes")) {
            if(editName.getText().toString().equals("") | editPhone.getText().toString().equals("")) {
                Toast.makeText(EditContactActivity.this, "Input correct data", Toast.LENGTH_SHORT).show();
            } else {
                dataBase.updateContact(getIntent().getStringExtra("name"), getIntent().getStringExtra("phone"),
                        editName.getText().toString(), editPhone.getText().toString());
                editName.setText("");
                editPhone.setText("");
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    private void showAlertDialog() {
        MyCustomDialog alertDialog = MyCustomDialog.newInstance("Edit contact?");
        alertDialog.show(getFragmentManager(), "Fragment_alert");
    }
}
