package com.example.contactappviewbind;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.contactappviewbind.adapter.ContactAdapter;
import com.example.contactappviewbind.databinding.ActivityMainBinding;
import com.example.contactappviewbind.db.ContactAppDatabase;
import com.example.contactappviewbind.db.entity.Contact;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    //Variable for view binding

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;


    //Variable

    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contactArrayList= new ArrayList<>();
    private RecyclerView recyclerView;
    Contact contact;
    private ContactAppDatabase contactAppDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data binding
        //contact= new Contact();

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
       // activityMainBinding.setContact(contact);


        clickHandler=new MainActivity.MainActivityClickHandler(this);
        //clickHandler =new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(clickHandler);

        //Toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bijoy Contacts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recycler view
        recyclerView=findViewById(R.id.recycler_view_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Adapter

      /*  contactsAdapter=new ContactAdapter(this,contactArrayList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/

        contactAdapter=new ContactAdapter(contactArrayList);
        //recyclerView.setAdapter(contactAdapter);

        //Database for data
       /* contactAppDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        ContactAppDatabase.class,
                        "ContactDB").build();
*/

        contactAppDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        ContactAppDatabase.class,
                        "ContactDB")
                .allowMainThreadQueries()
                .build();

        //Add data

         loadData();

        //Handling swiping

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact =contactArrayList.get(viewHolder.getAdapterPosition());
                DeleteContact(contact);

            }
        }).attachToRecyclerView(recyclerView);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){
            String name=data.getStringExtra("NAME");
            String email=data.getStringExtra("EMAIL");
            Contact contact=new Contact(name,email,0);
            addNewContact(contact);

        }



    }

    private void DeleteContact(Contact contact) {
        ExecutorService executor= Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //OnBAckground
               contactAppDatabase.getContactDAO().deleteContact(contact);
               contactArrayList.remove(contact);

                //on Post Execution
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        contactAdapter.notifyDataSetChanged();
                    }
                });


            }
        });

    }

    private void loadData() {
        ExecutorService executor= Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //OnBackground
              //  contactArrayList.addAll(contactAppDatabase.getContactDAO().getAllContacts());
                Log.v("TEST", "run:in load data ");
                //on Post Execution
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       // contactAdapter.setContacts(contactArrayList);
                       // contactAdapter.notifyDataSetChanged();
                    }
                });


            }
        });





    }


    private void addNewContact(Contact contact){
        ExecutorService executor= Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //OnBAckground
                contactAppDatabase.getContactDAO().insert(contact);
                contactArrayList.add(contact);

                //on Post Execution
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        contactAdapter.notifyDataSetChanged();
                    }
                });


            }
        });

    }

    public class MainActivityClickHandler{
        Context context;

        public MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onFabClicker(View view){
          Intent i =new Intent(MainActivity.this,AddNewContactActivity.class);
          startActivityForResult(i,1);
            //Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();

        }


    }


}