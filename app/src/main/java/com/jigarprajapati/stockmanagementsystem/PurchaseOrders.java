package com.jigarprajapati.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PurchaseOrders extends AppCompatActivity {
    ImageButton purchaseorders;
    TextView textView;

    RecyclerView recyclerView;
    DatabaseReference database;
    POlist pOlist;
    ArrayList<pentry> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_orders);
        purchaseorders = findViewById(R.id.IBpurchaseorders);
        textView = findViewById(R.id.PurchaseOrderBackArrow);
        recyclerView = findViewById(R.id.RVpurchaseorder);


        database = FirebaseDatabase.getInstance().getReference("PurchaseEntry");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list1 = new ArrayList<>();
        pOlist = new POlist(this, list1);
        recyclerView.setAdapter(pOlist);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    pentry pentrys = dataSnapshot.getValue(pentry.class);
                    list1.add(pentrys);


                }
                pOlist.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PurchaseOrders.this,Home.class);
                startActivity(intent);
            }
        });





        purchaseorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseOrders.this, RefineSearch.class);
                startActivity(intent);
                //finish();

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseOrders.this, Home.class);
                startActivity(intent);
            }
        });
    }
}