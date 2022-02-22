package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PurchaseEntry extends AppCompatActivity {

    String[] strings = {"Cash", "Debit"};

    EditText editTextDate;
    TextView textView;
    EditText productname;
    EditText vendorname;
    EditText purchasequality;
    ImageButton PurchaseEntry;
    ImageButton plusicon1;
    ImageButton plusicon2;
    EditText purchaseprice;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_entry);
        editTextDate = findViewById(R.id.ETpurchasedate);
        PurchaseEntry = findViewById(R.id.IBpurchaseentry);
        plusicon1 = findViewById(R.id.IBplusicon1);
        plusicon2 = findViewById(R.id.IBplusicon2);
        productname = findViewById(R.id.ETproductname);
        vendorname = findViewById(R.id.ETvendornameinpurchase);
        purchasequality = findViewById(R.id.ETpurchasequantity);
        purchaseprice = findViewById(R.id.ETpurchaseprice);
        textView=findViewById(R.id.PurchaseEntryBackArrow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(com.jigarprajapati.stockmanagementsystem.PurchaseEntry.this,Home.class);
                startActivity(intent);
            }
        });


        //click Plus Icon
        plusicon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseEntry.this, ProductAddUpdate.class);
                startActivity(intent);

            }
        });
        //click Plus Icon
        plusicon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseEntry.this, VendorAppUpdate.class);
                startActivity(intent);


            }
        });


        //Datepicker
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PurchaseEntry.this, onDateSetListener, year, month, day);

                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                editTextDate.setText(date);
            }
        };

        // Spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strings);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    Toast.makeText(PurchaseEntry.this, strings[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //validation click
        PurchaseEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pname = productname.getText().toString();
                String vname = vendorname.getText().toString();
                String pquality = purchasequality.getText().toString();
                String pdate = editTextDate.getText().toString();
                String pprice = purchaseprice.getText().toString();


                //validation call
                boolean Check = ValidateInfo(pname, vname, pquality, pdate, pprice);

                if (Check == true) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("PurchaseEntry");
                    HashMap<String, String> map = new HashMap<>();
                    String key = databaseReference.push().getKey();

                    Map newpost = new HashMap();
                    newpost.put("ProductName", pname);
                    newpost.put("VendorName", vname);
                    newpost.put("PurchaseQuantity", pquality);
                    newpost.put("PurchaseDate", pdate);
                    newpost.put("PurchasePrice", pprice);

                    databaseReference.child("" + System.currentTimeMillis()).setValue(map);


                    databaseReference.child(key).setValue(newpost);

                    Toast.makeText(getApplicationContext(), "Purchase Entry Successfully", Toast.LENGTH_SHORT).show();

                } else {


                }

            }
        });


    }

    //all Validation
    private Boolean ValidateInfo(String pname, String vname, String pquality, String pdate, String pprice ) {
        if (pname.length() == 0) {
            productname.requestFocus();
            productname.setError("Product Name is Required");
            return false;
        } else if (vname.length() == 0) {
            vendorname.requestFocus();
            vendorname.setError("Customer Name is Required");
            return false;
        } else if (pquality.length() == 0) {
            purchasequality.requestFocus();
            purchasequality.setError("Quality is Required");
            return false;
        } else if (pdate.length() == 0) {
            editTextDate.requestFocus();
            editTextDate.setError("Date is Required");
            return false;
        } else if (pprice.length() == 0) {
            purchaseprice.requestFocus();
            purchaseprice.setError("Price Required");
            return false;
        }
        else {
            return true;
        }
    }
}