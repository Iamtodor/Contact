package com.example.todor.contacts.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.todor.contacts.Model.Contact;
import com.example.todor.contacts.DataBase;
import com.example.todor.contacts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by todor on 15.07.15.
 */
//public class FindContactActivity extends AppCompatActivity{
//
//    private EditText findEdit;
//    private DataBase dataBase;
//    private ListView listView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.find_contact);
//        dataBase = new DataBase(this);
//
//        findEdit = (EditText) findViewById(R.id.findEdit);
//        listView = (ListView) findViewById(R.id.listViewContact);
//
//        findEdit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                ArrayList<Contact> list = new ArrayList<>(dataBase.findContact(findEdit.getText().toString()));
//                ArrayList<Map<String, Object>> data = new ArrayList<>();
//                Map<String, Object> map;
//                for(Contact contact : list) {
//                map = new HashMap<>();
//                map.put("name", contact.getName());
//                map.put("phone", contact.getPhone());
//                data.add(map);
//                }
//                String[] from = {"name", "phone"};
//                int[] to = {R.id.nameView, R.id.phoneView};
//                SimpleAdapter adapter = new SimpleAdapter(FindContactActivity.this, data, R.layout.contact, from, to);
//                listView.setAdapter(adapter);
//                }
//        });
//
//    }
//}
