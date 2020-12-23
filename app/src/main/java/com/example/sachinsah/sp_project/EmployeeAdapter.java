package com.example.sachinsah.sp_project;

/**
 * Created by Sachin Sah on 5/21/2018.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Moin Khan on 4/06/2017.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {


    DatabaseReference db;
    FirebaseHelper helper;
    Context mCtx;
    int listLayoutRes;
    List<Employee> employeeList;
    SQLiteDatabase mDatabase;
    private ArrayList<Employee> arraylist;


    EditText productio, consumptio, purchas, sal,remark;

    public EmployeeAdapter(Context mCtx, int listLayoutRes, List<Employee> employeeList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, employeeList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.employeeList = employeeList;
        this.mDatabase = mDatabase;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        final Employee employee = employeeList.get(position);


        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        //  TextView textViewDept = (TextView) view.findViewById(R.id.textViewDepartment);
        TextView textViewSalary = (TextView) view.findViewById(R.id.textViewSalary);
        //TextView textViewJoiningDate = (TextView) view.findViewById(R.id.textViewJoiningDate);


        textViewName.setText(employee.getName());
        //textViewDept.setText(employee.getDept());

        textViewSalary.setText(String.valueOf(employee.getSalary()));
        // textViewJoiningDate.setText(employee.getJoiningDate());


          ImageButton buttonDelete = (ImageButton) view.findViewById(R.id.buttonDeleteEmployee);
        ImageButton buttonEdit = (ImageButton) view.findViewById(R.id.buttonEditEmployee);
        buttonDelete.setVisibility(View.GONE);
        //adding a clicklistener to button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmployee(employee);
            }
        });
        Globals t = Globals.getInstance();
        String type = t.getType();

        if("admin".equals(type)) {

            buttonDelete.setVisibility(View.VISIBLE);

        }

        //the delete operation
       buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
  /*              AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM employees WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{employee.getId()});
                        reloadEmployeesFromDatabase();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
*/               String product=employee.getName();

                Globals p = Globals.getInstance();

                p.setProduct(product);
                Intent intent = new Intent(mCtx.getApplicationContext(),ParticularDataDisplayFirebase.class);

                mCtx.startActivity(intent);


            }
        });

       return view;
    }

    private void updateEmployee(final Employee employee) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_employee, null);
        builder.setView(view);


        final EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
        final EditText editTextSalary = (EditText) view.findViewById(R.id.editTextSalary);
        final EditText editTextCategory = (EditText) view.findViewById(R.id.editTextName1);
        productio = (EditText) view.findViewById(R.id.production);
        purchas = (EditText) view.findViewById(R.id.purchase);
        consumptio = (EditText) view.findViewById(R.id.consumption);
        remark = (EditText) view.findViewById(R.id.remarks);

        sal = (EditText) view.findViewById(R.id.sale);
        productio.requestFocus();
        productio.setText("0");
        purchas.setText("0");
        consumptio.setText("0");
        sal.setText("0");



        // final Spinner spinnerDepartment = (Spinner) view.findViewById(R.id.spinnerDepartment);

        editTextName.setText(employee.getName());
        editTextSalary.setText(String.valueOf(employee.getSalary()));
        editTextCategory.setText(String.valueOf(employee.getDept()));
        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.buttonUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String salary = editTextSalary.getText().toString().trim();
                String Dept = editTextCategory.getText().toString().trim();

                //getting the current time for joining date
                Calendar can = Calendar.getInstance();
                SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                String joiningDay = f.format(can.getTime());


                if (name.isEmpty()) {
                    editTextName.setError("Name can't be blank");
                    editTextName.requestFocus();
                    return;
                }

                if (salary.isEmpty()) {
                    editTextSalary.setError("Salary can't be blank");
                    editTextSalary.requestFocus();
                    return;
                }


                String production = productio.getText().toString().trim();
                String purchase = purchas.getText().toString().trim();
                String consumption = consumptio.getText().toString().trim();
                String sale = sal.getText().toString().trim();
                String rmk=remark.getText().toString().trim();

                int i = Integer.parseInt(production.replaceAll("[\\D]", ""));
                int j = Integer.parseInt(purchase.replaceAll("[\\D]", ""));
                int k = Integer.parseInt(consumption.replaceAll("[\\D]", ""));
                int l = Integer.parseInt(sale.replaceAll("[\\D]", ""));
                int z = Integer.parseInt(salary.replaceAll("[\\D]", ""));


                if (i == 0 && j == 0 && k == 0 && l == 0) {
                    productio.setError("Invalid Entry");
                    productio.requestFocus();
                    return;
                }
                double balance = ((i + j) - (k + l)) + z;
                String bal = String.valueOf(balance);


                java.util.Calendar cal = java.util.Calendar.getInstance();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String joiningDate = sdf.format(cal.getTime());

                Globals g = Globals.getInstance();
                String user = g.getData();
                //SET DATA
                Spacecraft s = new Spacecraft();
                s.setDated(joiningDate);
                s.setUsername(user);
                s.setProductname(name);
                s.setProduction(production);
                s.setPurchase(purchase);
                s.setConsumption(consumption);
                s.setSale(sale);
                s.setBalance(bal);
                s.setRemark(rmk);


                String sql = "UPDATE employees \n" +
                        "SET name = ?, \n" +
                        "department = ?, \n" +
                        "salary = ?, \n" +
                        "joiningdate= ?\n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{name, Dept, bal, joiningDay, String.valueOf(employee.getId())});
                Toast.makeText(mCtx, "Employee Updated", Toast.LENGTH_SHORT).show();
                reloadEmployeesFromDatabase(Dept);

                if (helper.save(s)) {
                    //IF SAVED CLEAR EDITXT
                    productio.setText("0");
                    purchas.setText("0");
                    consumptio.setText("0");
                    sal.setText("0");
                    dialog.dismiss();

                } else {

                    productio.setText("not saved");
                }

            }
        });
    }

    private void reloadEmployeesFromDatabase(String cat) {
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employees where department=?", new String[]{cat});
        if (cursorEmployees.moveToFirst()) {
            employeeList.clear();
            do {
                employeeList.add(new Employee(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getInt(4)
                ));
            } while (cursorEmployees.moveToNext());
        }
        cursorEmployees.close();
        notifyDataSetChanged();
    }

    private void reloadEmployeesFromDatabase() {
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employees", null);
        if (cursorEmployees.moveToFirst()) {
            employeeList.clear();
            do {
                employeeList.add(new Employee(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getInt(4)
                ));
            } while (cursorEmployees.moveToNext());
        }
        cursorEmployees.close();
        notifyDataSetChanged();

    }



}
    /*private void displayInputDialog() {
        Dialog d = new Dialog(getContext());
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.dialoginput);

        productio = (EditText) d.findViewById(R.id.production);
        purchas= (EditText) d.findViewById(R.id.purchase);
        consumptio = (EditText) d.findViewById(R.id.consumption);
        sal = (EditText) d.findViewById(R.id.sale);
        Button saveBtn = (Button) d.findViewById(R.id.enterdetail);

        productio.setText("0");
        purchas.setText("0");
        consumptio.setText("0");
        sal.setText("0");



        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //GET DATA
                String user="sachin";
                String name="first";



                String production = productio.getText().toString().trim();
                String purchase = purchas.getText().toString().trim();
                String consumption = consumptio.getText().toString().trim();
                String sale =sal.getText().toString().trim();




                int i=Integer.parseInt(production.replaceAll("[\\D]", ""));
                int j=Integer.parseInt(purchase.replaceAll("[\\D]", ""));
                int k=Integer.parseInt(consumption.replaceAll("[\\D]", ""));
                int l=Integer.parseInt(sale.replaceAll("[\\D]", ""));

                if (i == 0 && j == 0 && k == 0 && l == 0 ) {
                    productio.setError("Invalid Entry");
                    productio.requestFocus();
                    return;
                }
                int balance=((i+j)-(k+l));
                String bal = String.valueOf(balance);





                java.util.Calendar cal = java.util.Calendar.getInstance();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String joiningDate = sdf.format(cal.getTime());

                //SET DATA
                Spacecraft s = new Spacecraft();
                s.setDated(joiningDate);
                s.setUsername(user);
                s.setProductname(name);
                s.setProduction(production);
                s.setPurchase(purchase);
                s.setConsumption(consumption);
                s.setSale(sale);
                s.setBalance(bal);

                Toast.makeText(MainActivity.this, "Detail Successfully Entered", Toast.LENGTH_SHORT).show();
                //SIMPLE VALIDATION
                if (name != null && name.length() > 0) {
                    //THEN SAVE
                    if (helper.save(s)) {
                        //IF SAVED CLEAR EDITXT
                        productio.setText("0");
                        purchas.setText("0");
                        consumptio.setText("0");
                        sal.setText("0");

                        adapter = new EmployeeAdapter(.this, helper.retrieve());
                        lv.setAdapter(adapter);

                        d.dismiss();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        d.show();
    }

*/




