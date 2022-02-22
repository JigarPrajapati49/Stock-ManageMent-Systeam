package com.jigarprajapati.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VendorDetails extends AppCompatActivity {

    TextView textView;
    ImageButton vendordetails;
    RecyclerView recyclerView;
    DatabaseReference database;
    VendorAdapter vendorAdapter;
    ArrayList<vendordetail> list1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);
        vendordetails = findViewById(R.id.IBvendordetails);
        recyclerView = findViewById(R.id.RVvendordetails);
        textView=findViewById(R.id.VendorDetailsBackArrow);

    //Data Retrive From Database.
        database = FirebaseDatabase.getInstance().getReference("VendorDetails");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list1 = new ArrayList<>();
        vendorAdapter = new VendorAdapter(this, list1);
        recyclerView.setAdapter(vendorAdapter);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VendorDetails.this,Home.class);
                startActivity(intent);
            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    vendordetail vendordetails = dataSnapshot.getValue(vendordetail.class);
                    list1.add(vendordetails);


                }
                vendorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    //Click write Icon
        vendordetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorDetails.this, VendorAppUpdate.class);
                startActivity(intent);
                // finish();

            }
        });


    }
    //Vendor Mobile Number Validation


}

