package com.example.sachinsah.sp_project;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * 1.SAVE DATA TO FIREBASE
 * 2. RETRIEVE
 * 3.RETURN AN ARRAYLIST
 */
public class ParticularFirebaseHelper {

    DatabaseReference db;
    Boolean saved;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

    /*
 PASS DATABASE REFRENCE
  */
    public ParticularFirebaseHelper(DatabaseReference db) {
        this.db = db;

    }

    //WRITE IF NOT NULL
    public Boolean save(Spacecraft spacecraft) {
        if (spacecraft == null) {
            saved = false;
        } else {
            try {
                db.child("Spacecraft").push().setValue(spacecraft);
                saved = true;

            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }

        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot) {
        spacecrafts.clear();


        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Spacecraft spacecraft = ds.getValue(Spacecraft.class);
            spacecrafts.add(spacecraft);
        }
    }

    //RETRIEVE
    public ArrayList<Spacecraft> retrieve() {


        Globals p = Globals.getInstance();
        String product = p.getProduct();

        Query query = db.child("Spacecraft").orderByChild("productname").equalTo(product);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do with your result
                        fetchData(dataSnapshot);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return spacecrafts;

/*
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return spacecrafts;
    }
*/
    }
}