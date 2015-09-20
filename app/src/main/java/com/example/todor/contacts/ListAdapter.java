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
        ViewHolder viewHolder;
        View v = convertView;
        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.contact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) v.findViewById(R.id.nameView);
            viewHolder.phoneTextView = (TextView) v.findViewById(R.id.phoneView);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        Contact contact = getItem(position);

        viewHolder.nameTextView.setText(contact.getName());
        viewHolder.phoneTextView.setText(contact.getPhone());
        return v;
    }

    static class ViewHolder{
        TextView nameTextView;
        TextView phoneTextView;
    }
}
