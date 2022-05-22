package com.example.contactappviewbind.db;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contactappviewbind.db.entity.Contact;

/*@Database(
        version = 2,
        exportSchema = false,
        entities = {Contact.class},
        autoMigrations = {
                @AutoMigration(from = 1, to = 2)

        })*/
@Database(entities = { Contact.class }, version = 1, exportSchema = false)
public abstract class ContactAppDatabase extends RoomDatabase {

    //linking DAO to database

    public abstract ContactDAO getContactDAO();



}
