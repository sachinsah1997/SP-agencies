package com.example.sachinsah.sp_project;

/**
 * Created by Sachin Sah on 5/11/2018.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends Activity
{
   /* EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;
    TextView reset;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

// get Instance of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

// Get Refferences of Views
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

// check if any of the fields are vaccant
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
// check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
// Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Register.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });

        reset = (TextView) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub

                editTextUserName.setText("");
                editTextPassword.setText("");
                editTextConfirmPassword.setText("");
            }
        });
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }*/




    EditText email, password ,editTextConfirmPassword;

    // Creating button.
    Button SignUp, ButtonGoToLoginActivity;

    // Creating string to hold email and password .
    String EmailHolder, PasswordHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Creating FirebaseAuth object.
    FirebaseAuth firebaseAuth ;

    // Creating Boolean variable that holds EditText is empty or not status.
    Boolean EditTextStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Assigning layout email ID and Password ID.
        email = (EditText)findViewById(R.id.EditText_User_EmailID);
        password = (EditText)findViewById(R.id.EditText_User_Password);
         editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        // Assign button layout ID.
        SignUp = (Button)findViewById(R.id.Button_SignUp);
//        ButtonGoToLoginActivity = (Button)findViewById(R.id.Button_LoginActivity);


        // Creating object instance.
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Register.this);

        // Adding click listener to Sign Up Button.
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = password.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();


                // Calling method to check EditText is empty or no status.
                CheckEditTextIsEmptyOrNot();

                if (!pass.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }

                // If EditText is true then this block with execute.
                if(EditTextStatus){

                    // If EditText is not empty than UserRegistrationFunction method will call.
                    UserRegistrationFunction();

                }
                // If EditText is false then this block with execute.
                else {

                    Toast.makeText(Register.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


        // Adding click listener to ButtonGoToLoginActivity button.
       /* ButtonGoToLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Finishing current Main Activity.
                finish();

                // Opening the Login Activity using Intent.
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);

            }
        });

*/
        TextView reset;

        reset = (TextView) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub

                email.setText("");
                password.setText("");
                editTextConfirmPassword.setText("");
            }
        });

    }

    // Creating UserRegistrationFunction
    public void UserRegistrationFunction(){

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
        progressDialog.show();

        // Creating createUserWithEmailAndPassword method and pass email and password inside it.
        firebaseAuth.createUserWithEmailAndPassword(EmailHolder, PasswordHolder).
                addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // Checking if user is registered successfully.
                        if(task.isSuccessful()){

                            // If user registered successfully then show this toast message.
                            Toast.makeText(Register.this,"User Registration Successfully",Toast.LENGTH_LONG).show();

                            email.setText("");
                            password.setText("");
                            editTextConfirmPassword.setText("");
                            Intent i=new Intent(Register.this,MainActivity.class);
                            startActivity(i);
                            firebaseAuth.signOut();

                        }else{

                            // If something goes wrong.
                            Toast.makeText(Register.this,"Something Went Wrong.",Toast.LENGTH_LONG).show();
                        }

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                    }
                });

    }

    public void CheckEditTextIsEmptyOrNot(){

        // Getting name and email from EditText and save into string variables.
        EmailHolder = email.getText().toString().trim();
        PasswordHolder = password.getText().toString().trim();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            EditTextStatus = false;

        }
        else {

            EditTextStatus = true ;
        }

    }


}