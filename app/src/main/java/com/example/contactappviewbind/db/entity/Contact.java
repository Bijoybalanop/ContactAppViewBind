package com.example.contactappviewbind.db.entity;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.contactappviewbind.BR;


@Entity(tableName = "contact_table")
public class Contact extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name="name")
    String name;
    @ColumnInfo(name="email")
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


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "ALTER TABLE Song ADD COLUMN tag TEXT NOT NULL DEFAULT ''");
        }
    };

}


