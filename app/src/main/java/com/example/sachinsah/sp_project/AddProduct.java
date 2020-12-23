package com.example.sachinsah.sp_project;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddProduct extends Activity implements View.OnClickListener {

    public static final String DATABASE_NAME = "myemployeedatabase";

    TextView textViewViewEmployees,head;
    EditText editTextName, editTextSalary;
    Button add;



    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct1);


        head = (TextView) findViewById(R.id.head);

        textViewViewEmployees = (TextView) findViewById(R.id.textViewViewEmployees);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        add=(Button)findViewById(R.id.buttonAddEmployee);

        editTextSalary.setVisibility(View.GONE);
        editTextName.setVisibility(View.GONE);
        add.setVisibility(View.GONE);
        head.setVisibility(View.GONE);

        Globals t = Globals.getInstance();
        String type = t.getType();

        if("admin".equals(type)) {

            editTextSalary.setVisibility(View.VISIBLE);
            editTextName.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
            head.setVisibility(View.VISIBLE);

        }

        findViewById(R.id.buttonAddEmployee).setOnClickListener(this);
        textViewViewEmployees.setOnClickListener(this);

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);


            //The key argument here must match that used in the other activity

        createEmployeeTable();
    }


    //this method will create the table
    //as we are going to call this method everytime we will launch the application
    //I have added IF NOT EXISTS to the SQL
    //so it will only create the table when the table is not already created
    private void createEmployeeTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    name varchar(200) NOT NULL,\n" +
                        "    department varchar(200) NOT NULL,\n" +
                        "    joiningdate datetime NOT NULL,\n" +
                        "    salary int NOT NULL\n" +
                        ");"
        );
    }

    //this method will validate the name and salary
    //dept does not need validation as it is a spinner and it cannot be empty
    private boolean inputsAreCorrect(String name, String salary) {
        if (name.isEmpty()) {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return false;
        }

        if (salary.isEmpty() || Integer.parseInt(salary) <= 0) {
            editTextSalary.setError("Please enter salary");
            editTextSalary.requestFocus();
            return false;
        }
        return true;
    }

    //In this method we will do the create operation
    private void addEmployee(String dep) {

        String name = editTextName.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();


        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        //validating the inptus
        if (inputsAreCorrect(name, salary)) {

            String insertSQL = "INSERT INTO employees \n" +
                    "(name, department, joiningdate, salary)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query
            mDatabase.execSQL(insertSQL, new String[]{name, dep, joiningDate, salary});
            Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextSalary.setText("");
        }
    }


    //Intent intent = getIntent();
    @Override
    public void onClick(View view) {
      //  String valx = intent.getStringExtra("send");

        String value = getIntent().getStringExtra("send");

        switch (view.getId()) {
            case R.id.buttonAddEmployee:

            String valx=value;

                addEmployee(valx);

                break;
            case R.id.textViewViewEmployees:

                String recieve=value;
                Intent i=new Intent(AddProduct.this,EmployeeActivity.class);
              i.putExtra("key2", recieve);
                startActivity(i);


                break;
        }
    }
}