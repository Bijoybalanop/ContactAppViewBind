package com.example.contactappviewbind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactappviewbind.databinding.ActivityAddNewContactBinding;
import com.example.contactappviewbind.db.ContactAppDatabase;
import com.example.contactappviewbind.db.entity.Contact;

public class AddNewContactActivity extends AppCompatActivity {


    private ActivityAddNewContactBinding activityAddNewContactBinding;
    Contact contact;
    private ActivityNewContactActivityClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);


        contact= new Contact();
        //binding

        activityAddNewContactBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);
        activityAddNewContactBinding.setContactbind(contact);

        clickHandler=new ActivityNewContactActivityClickHandler(this);
        activityAddNewContactBinding.setClickHandler(clickHandler);







    }

    public class ActivityNewContactActivityClickHandler{
        Context context;

        public ActivityNewContactActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitClick(View view){

            if(contact.getName()==null){
                Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_SHORT).show();
            }else{
                Intent i =new Intent();
                i.putExtra("NAME",contact.getName());
                i.putExtra("EMAIL",contact.getEmail());
                setResult(RESULT_OK,i);
                finish();
            }



        }
    }
}