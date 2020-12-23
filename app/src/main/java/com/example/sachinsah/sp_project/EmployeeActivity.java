package com.example.sachinsah.sp_project;

/**
 * Created by Sachin Sah on 5/21/2018.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmployeeActivity extends AppCompatActivity {


    List<Employee> employeeList;
    SQLiteDatabase mDatabase;
    ListView listViewEmployees, searchlist;
    EmployeeAdapter adapter, view;
    EditText Search;
    Button searchdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Search = (EditText) findViewById(R.id.search);
        searchdisplay = (Button) findViewById(R.id.searchbutton);

        listViewEmployees = (ListView) findViewById(R.id.listViewEmployees);
        searchlist = (ListView) findViewById(R.id.listViewEmployeessearch);

        employeeList = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(AddProduct.DATABASE_NAME, MODE_PRIVATE, null);

        final String fuck = getIntent().getStringExtra("key2");
        final String gt = fuck;
        //this method will display the employees in the list
        showEmployeesFromDatabase(gt);


        searchdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s=Search.getText().toString();
                final String fuck = getIntent().getStringExtra("key2");
                final String gt = fuck;
                populate(gt,s);

            }
        });










    }

    private void showEmployeesFromDatabase(String cat) {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employees where department=?", new String[]{cat});

        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new Employee(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getInt(4)
                ));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        //creating the adapter object
        view = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList, mDatabase);

        //adding the adapter to listview
        listViewEmployees.setAdapter(view);


    }
    public void populate(String cat,String s) {


        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employees where department=? and name LIKE ?", new String[]{cat,s+"%"});

      view.clear();
        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new Employee(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getInt(4)
                ));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        //creating the adapter object
        view = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList, mDatabase);

        //adding the adapter to listview
        listViewEmployees.setAdapter(view);

    }
    }






