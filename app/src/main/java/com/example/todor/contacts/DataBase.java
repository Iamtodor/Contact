package com.example.todor.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todor.contacts.Model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by todor on 14.07.15.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Contacts";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    private SQLiteDatabase sqLiteDatabase;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DATABASE_NAME + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Contact> show() {
        List<Contact> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DATABASE_NAME + " ORDER BY " + KEY_NAME + " COLLATE NOCASE";
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do{
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                list.add(contact);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return list;
    }

    public ArrayList<Contact> findContact(String name) {
        ArrayList<Contact> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DATABASE_NAME +
                " WHERE name LIKE " + "'%" + name + "%'" + " ORDER BY " + KEY_NAME;

        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do{
                Contact contact = new Contact();
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                contact.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                list.add(contact);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return list;
    }

    public void addContact(String name, String phone) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_PHONE, phone);
        sqLiteDatabase.insert(DATABASE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public void updateContact(String lastName, String lastPhone, String newName, String newPhone) {
        int id = 0;
        sqLiteDatabase = this.getWritableDatabase();
        String idQuery = "SELECT " + KEY_ID + " FROM " + DATABASE_NAME + " WHERE name='" + lastName +
                "'" + " AND phone='" + lastPhone + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(idQuery, null);
        if(cursor.moveToFirst())
            id = cursor.getInt(0);
        String where = KEY_ID + "=" + id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, newName);
        contentValues.put(KEY_PHONE, newPhone);
        sqLiteDatabase.update(DATABASE_NAME, contentValues, where, null);
    }

    public void deleteContact(String name, String phone) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_NAME, ("name='" + name + "' AND phone='" + phone + "'"), null);
        sqLiteDatabase.close();
    }
}
