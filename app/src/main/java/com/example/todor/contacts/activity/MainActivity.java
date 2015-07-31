package com.example.todor.contacts.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.todor.contacts.Model.Contact;
import com.example.todor.contacts.DataBase;
import com.example.todor.contacts.ListAdapter;
import com.example.todor.contacts.MyAlertDialog;
import com.example.todor.contacts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText findEdit;
    private ListView listViewContact;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this);

        listViewContact = (ListView) findViewById(R.id.listViewContact);
        findEdit = (EditText) findViewById(R.id.findEdit);

        Button addButton = (Button) findViewById(R.id.addButton);
        show();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        findEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Contact> list = new ArrayList<>(dataBase.findContact(findEdit.getText().toString()));
                ArrayList<Map<String, Object>> data = new ArrayList<>();
                Map<String, Object> map;
                for (Contact contact : list) {
                    map = new HashMap<>();
                    map.put("name", contact.getName());
                    map.put("phone", contact.getPhone());
                    data.add(map);
                }
                String[] from = {"name", "phone"};
                int[] to = {R.id.nameView, R.id.phoneView};
                SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data, R.layout.contact, from, to);
                listViewContact.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listViewContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) ((TextView) view.findViewById(R.id.nameView)).getText();
                String phone = (String) ((TextView) view.findViewById(R.id.phoneView)).getText();
                System.out.println(name + phone);
                showDialog(name, phone, MainActivity.this);
                return true;
            }
        });
    }

    public void show() {
        List<Contact> list = new ArrayList<>(dataBase.show());
        ListAdapter customAdapter = new ListAdapter(this, R.layout.contact, list);
        listViewContact.setAdapter(customAdapter);
    }

    void showDialog (String name, String phone, Activity activity) {
        DialogFragment newFragment = MyAlertDialog.newInstance(name, phone);
        newFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK) {
            show();
        }
    }
}
