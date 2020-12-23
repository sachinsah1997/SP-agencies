package com.example.sachinsah.sp_project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 1. where WE INFLATE OUR MODEL LAYOUT INTO VIEW ITEM
 * 2. THEN BIND DATA
 */
public class FirebaseCustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public FirebaseCustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {

        return spacecrafts.get(getCount() - position - 1);
     }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView date= (TextView) convertView.findViewById(R.id.date_d);
        TextView username= (TextView) convertView.findViewById(R.id.username_d);
        TextView productname= (TextView) convertView.findViewById(R.id.productname_d);
        TextView production= (TextView) convertView.findViewById(R.id.production_d);
        TextView purchase= (TextView) convertView.findViewById(R.id.purchase_d);
        TextView consumption= (TextView) convertView.findViewById(R.id.consumption_d);
        TextView sale= (TextView) convertView.findViewById(R.id.sale_d);
        TextView amt= (TextView) convertView.findViewById(R.id.amount_d);
        TextView rmk= (TextView) convertView.findViewById(R.id.remarkdisplay);




        final Spacecraft s= (Spacecraft) this.getItem(position);

        date.setText(s.getDated());
        username.setText(s.getUsername());
        productname.setText(s.getProductname());
        production.setText(s.getProduction());
        purchase.setText(s.getPurchase());
        consumption.setText(s.getConsumption());
        sale.setText(s.getSale());
        amt.setText(s.getBalance());
        rmk.setText(s.getRemark());


        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getProduction(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}