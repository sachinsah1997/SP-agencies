package com.example.sachinsah.sp_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sachin Sah on 5/12/2018.
 */
public class Sudharshan_catlist extends AppCompatActivity {

    Button fi,ra,forg,pm,che,mt,nyc,rub,pol,trail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudharshan_catlist);


        fi=(Button)findViewById(R.id.figoods);
        ra=(Button)findViewById(R.id.sudraw);

        /* forg=(Button)findViewById(R.id.forgings);
        pm=(Button)findViewById(R.id.pmaterials);
        che=(Button)findViewById(R.id.chemicalssud);
        mt=(Button)findViewById(R.id.mfit);
        nyc=(Button)findViewById(R.id.nc);
        rub=(Button)findViewById(R.id.rub);
        pol=(Button)findViewById(R.id.poly);
        trail=(Button)findViewById(R.id.rej);
*/

        fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "finished");
                startActivity(i);


            }
        });

        ra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "rawmaterial");
                startActivity(i);


            }
        });

  /*      forg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "FORGING");
                startActivity(i);


            }
        });

        pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "PACKAGEMATERIAL");
                startActivity(i);


            }
        });

        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "CHEMICALS");
                startActivity(i);


            }
        });

        mt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "METALFIT");
                startActivity(i);


            }
        });


        nyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "NYLON");
                startActivity(i);


            }
        });

        rub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "RUBBERS");
                startActivity(i);


            }
        });


        pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "POLYURATHANE");
                startActivity(i);


            }
        });

        trail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sudharshan_catlist.this,AddProduct.class);
                i.putExtra("send", "REJECTION");
                startActivity(i);


            }
        });

*/

    }
}