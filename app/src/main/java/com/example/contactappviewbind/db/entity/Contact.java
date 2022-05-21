package com.example.contactappviewbind.db.entity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.contactappviewbind.BR;


@Entity(tableName = "contact_table")
public class Contact extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String email;


/*  @ColumnInfo(name="contact_id")
    @PrimaryKey(autoGenerate = true)
    private  int id;*/

    public Contact(String name, String email,int id) {
        this.name = name;
        this.email = email;
        this.id=id;

    }

    @Ignore
    public Contact() {
    }
/*
    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }*/

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);

    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);

    }
}


