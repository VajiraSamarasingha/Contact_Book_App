package com.example.listcontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DB_Name = "ContactDb";
    private static final String Contact_Table = "Contacts";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String EMAIL = "email";
    private static final String ORGANAIZATION = "organaization";
    private static final String RELATIONSHIP = "relationship";
    private static final String DATE = "dop";

    public DbHandler(Context context) {

        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREAT_CONTEXT_TABLE =
                "CREATE TABLE "+Contact_Table
                        +"("+ID+" integer PRIMARY KEY autoincrement,"
                        +NAME+" TEXT,"
                        +NUMBER+" TEXT,"
                        +EMAIL+" TEXT,"
                        +ORGANAIZATION+" TEXT,"
                        +RELATIONSHIP+" TEXT)";

        db.execSQL(CREAT_CONTEXT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE "+ Contact_Table;
        db.execSQL(sql);
        onCreate(db);

    }

    public void addContact(Contact contact){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,contact.getName());
        values.put(NUMBER,contact.getNumber());
        values.put(EMAIL,contact.getEmail());
        values.put(ORGANAIZATION,contact.getOrganaization());
        values.put(RELATIONSHIP,contact.getRelationship());

        db.insert(Contact_Table,null,values);
        db.close();

    }

    public Contact getContact(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                Contact_Table,
                new String[]{ID,NAME,NUMBER,EMAIL,ORGANAIZATION,RELATIONSHIP},
                ID+ " = ?",
                new String[]{String.valueOf(id)},
                null,null,null,null
        );
        Contact contact;
        if(cursor != null){
            cursor.moveToFirst();
            contact = new Contact(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            return contact;
        }else{
            return null;
        }
    }

    public List<Contact> getAllContact(){
        SQLiteDatabase db = getReadableDatabase();
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM "+Contact_Table;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setOrganaization(cursor.getString(4));
                contact.setRelationship(cursor.getString(5));
                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }
        return contacts;
    }
    public int updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME,contact.getName());
        values.put(NUMBER,contact.getNumber());
        values.put(EMAIL,contact.getEmail());
        values.put(ORGANAIZATION,contact.getOrganaization());
        values.put(RELATIONSHIP,contact.getRelationship());

        return db.update(
                Contact_Table,values,
                ID+ " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(
                Contact_Table,ID+ " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getContactCount(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+Contact_Table;

        Cursor cursor = db.rawQuery(query,null);

        return cursor.getCount();
    }


}
