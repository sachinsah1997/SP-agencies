package com.example.sachinsah.sp_project;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

/*
1.INITIALIZE FIREBASE DB
2.INITIALIZE UI
3.DATA INPUT
 */
public class FirebaseDataDisplay extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    FirebaseCustomAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebasedatadisplay);

        lv = (ListView) findViewById(R.id.lv);

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        //ADAPTER
          adapter = new FirebaseCustomAdapter(this, helper.retrieve());
          lv.setAdapter(adapter);

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new FirebaseCustomAdapter(FirebaseDataDisplay.this, helper.retrieve());

                lv.setAdapter(adapter);


            }
        });


    }

    }
