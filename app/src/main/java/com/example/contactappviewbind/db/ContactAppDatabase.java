package com.example.contactappviewbind.db;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contactappviewbind.db.entity.Contact;

@Database(entities = {Contact.class},version=2)
public abstract class ContactAppDatabase extends RoomDatabase {

    //linking DAO to database

    public abstract ContactDAO getContactDAO();



}
