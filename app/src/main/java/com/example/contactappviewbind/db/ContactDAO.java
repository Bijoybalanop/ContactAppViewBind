package com.example.contactappviewbind.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactappviewbind.db.entity.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insert(Contact contact);

     //contacts table name
    @Query( "SELECT * FROM contact_table")
     List<Contact> getAll();

    @Delete
    void deleteContact(Contact contact);

  /*  @Query("Select * from contact_table where contact_id==:contact_id")
    public Contact getContact(long contact_id);*/



}
