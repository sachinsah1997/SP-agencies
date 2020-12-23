package com.example.sachinsah.sp_project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.app.ProgressDialog;
/**
 * Created by Sachin Sah on 5/12/2018.
 */
public class Companylist extends AppCompatActivity{


    Button sp,sudharshan,report,logout,signup;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser;
    // Creating progress dialog.
    ProgressDialog progressDialog;

    // Creating FirebaseAuth object.
    FirebaseAuth firebaseAut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companylist);

        sp=(Button)findViewById(R.id.SP);
        sudharshan=(Button)findViewById(R.id.sudharshan);
        report=(Button)findViewById(R.id.report);
        signup=(Button)findViewById(R.id.register);

        logout=(Button)findViewById(R.id.logout);

        report.setVisibility(View.GONE);
        signup.setVisibility(View.GONE);

        Globals t = Globals.getInstance();
        String type = t.getType();


        if("admin".equals(type)) {

            report.setVisibility(View.VISIBLE);
            signup.setVisibility(View.VISIBLE);

        }



        // Adding FirebaseAuth instance to FirebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();

        // On activity start check whether there is user previously logged in or not.

        if(firebaseAuth.getCurrentUser() == null){

            // Finishing current Profile activity.
            finish();

            // If user already not log in then Redirect to LoginActivity .
            Intent intent = new Intent(Companylist.this, MainActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(Companylist.this, "Please Log in to continue", Toast.LENGTH_LONG).show();

        }

       firebaseUser = firebaseAuth.getCurrentUser();

        String userName=firebaseUser.getEmail();

        // Getting logged in user email from firebaseUser.getEmail() method and set into TextView.
        Globals g = Globals.getInstance();
        g.setData(userName);


        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for SignUpActivity abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),Register.class);
                startActivity(intentSignUP);
            }
        });



        sp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent sp=new Intent(Companylist.this,Sp_catlist.class);
                startActivity(sp);
            }
        });

        sudharshan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent sudharshan=new Intent(Companylist.this,Sudharshan_catlist.class);
                startActivity(sudharshan);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent report=new Intent(Companylist.this,FirebaseDataDisplay.class);
                startActivity(report);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Destroying login season.
                firebaseAuth.signOut();

                // Finishing current User Profile activity.
                finish();

                Toast.makeText(Companylist.this, "Logged Out Successfully.", Toast.LENGTH_LONG).show();

                Intent report=new Intent(Companylist.this,MainActivity.class);
                startActivity(report);
            }
        });



    }
    @Override
    public void onResume(){
        super.onResume();

        firebaseAuth.signOut();

    }
}
