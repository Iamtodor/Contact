package com.example.todor.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todor.contacts.Model.Contact;

import java.util.List;

/**
 * Created by todor on 16.07.15.
 */
public class ListAdapter extends ArrayAdapter<Contact> {

    public ListAdapter(Context context, int resource, List<Contact> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(R.layout.contact, null);
        }

        Contact contact = getItem(position);

        TextView name = (TextView) v.findViewById(R.id.nameView);
        TextView phone = (TextView) v.findViewById(R.id.phoneView);

        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        return v;
    }
}
