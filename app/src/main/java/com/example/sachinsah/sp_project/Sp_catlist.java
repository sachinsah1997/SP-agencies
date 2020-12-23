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
public class Sp_catlist extends AppCompatActivity {

    Button finished,raw,semi_finished,rubber,chemical,metalfitting;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_catlist);



        finished=(Button)findViewById(R.id.fgoods);
        raw=(Button)findViewById(R.id.raw);

        /*  semi_finished=(Button)findViewById(R.id.sfgoods);
        rubber=(Button)findViewById(R.id.rubbers);
        chemical=(Button)findViewById(R.id.chemicals);
        metalfitting=(Button)findViewById(R.id.metalfit);
*/


        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                intent.putExtra("send", "finished");
                startActivity(intent);

            }
        });


        raw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                intent.putExtra("send", "raw");
                startActivity(intent);

            }
        });

      /* semi_finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sp_catlist.this,AddProduct.class);
                i.putExtra("send", "SEMIFINISHED");
                startActivity(i);


            }
        });


        rubber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sp_catlist.this,AddProduct.class);
                i.putExtra("send", "RUBBER");
                startActivity(i);


            }
        });


        chemical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sp_catlist.this,AddProduct.class);
                i.putExtra("send", "CHEMICAL");
                startActivity(i);



            }
        });

        metalfitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Sp_catlist.this,AddProduct.class);
                i.putExtra("send", "METALFITTING");
                startActivity(i);


            }
        });
 */ }


}