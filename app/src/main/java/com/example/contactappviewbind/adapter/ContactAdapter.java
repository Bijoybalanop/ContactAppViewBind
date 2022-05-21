package com.example.contactappviewbind.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactappviewbind.MainActivity;
import com.example.contactappviewbind.R;
import com.example.contactappviewbind.db.entity.Contact;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    // Variable declaration


   private ArrayList<Contact> contactArrayList;



    //constructor


    public ContactAdapter(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textName;
        public TextView textEmail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.nameTV);
            textEmail=itemView.findViewById(R.id.emailTV);

        }
    }


    //Method


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Contact contact=contactArrayList.get(position);
    holder.textName.setText(contact.getName());
    holder.textEmail.setText(contact.getEmail());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getApp, "Item onclick", Toast.LENGTH_SHORT).show();
        }
    });

    }

    @Override
    public int getItemCount() {
        if(contactArrayList!=null){
            return contactArrayList.size();
        }else{
            return 0;
        }
    }

    //

    public void setContacts(ArrayList<Contact> contacts){
        this.contactArrayList=contacts;
        notifyDataSetChanged();
    }



}
