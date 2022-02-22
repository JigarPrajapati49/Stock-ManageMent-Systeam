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

public class CustomerDetail extends AppCompatActivity {

    ImageButton imageButton;
    RecyclerView recyclerView;
    TextView textView;
    DatabaseReference database;
    CustomerAdapter customerAdapter;
    ArrayList<Customerpojo> list3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        imageButton=findViewById(R.id.IBcustomerdetails);
        recyclerView=findViewById(R.id.rvcustomerdetails);
        textView=findViewById(R.id.CustomerDetailsBackArrow);

        database = FirebaseDatabase.getInstance().getReference("CustomerDetails");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list3 = new ArrayList<>();
        customerAdapter = new CustomerAdapter(this, list3);
        recyclerView.setAdapter(customerAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Customerpojo customerpojo = dataSnapshot.getValue(Customerpojo.class);
                    list3.add(customerpojo);


                }
                customerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CustomerDetail.this,Home.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CustomerDetail.this,CustomerAddUpdate.class);
                startActivity(intent);
            }
        });
    }
}