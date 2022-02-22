package com.jigarprajapati.stockmanagementsystem;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VendorAppUpdate extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText vendorname;
    EditText vendorcompanyname;
    EditText vendoraddress;
    EditText vendormobileno;
    EditText vendoremailid;
    ImageButton vendoraddupdate;
    DatabaseReference databaseReference;
    TextView textView;

    String name, companyname, address, mobileno, emailid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_app_update);

        vendorname = findViewById(R.id.ETvendorname);
        vendorcompanyname = findViewById(R.id.ETvendorcompanyname);
        vendoraddress = findViewById(R.id.ETvendoraddress);
        vendormobileno = findViewById(R.id.ETvendormobileno);
        vendoremailid = findViewById(R.id.ETvendoremailid);
        vendoraddupdate = findViewById(R.id.IBvendoraddupdate);
        recyclerView = findViewById(R.id.RVvendordetails);
        textView=findViewById(R.id.VendorAddUpdateBackArrow);
        databaseReference = FirebaseDatabase.getInstance().getReference("VendorDetails");

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VendorAppUpdate.this,Home.class);
                startActivity(intent);
            }
        });


        //For Mobile Validation
//        vendormobileno.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (validatemobile(vendormobileno.getText().toString())) {
//                    vendoraddupdate.setEnabled(true);
//                } else {
//                    vendoraddupdate.setEnabled(false);
//                    vendormobileno.setError("Invalid Mobile No");
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        //vendoraddupdate.setOnClickListener(new View.OnClickListener() {


        //    @Override
        //    public void onClick(View v) {
        //        EmailAddress(vendoremailid);

        //    }


        // });

        vendoraddupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                name = vendorname.getText().toString();
//                companyname = vendorcompanyname.getText().toString();
//                address = vendoraddress.getText().toString();
//                mobileno = vendormobileno.getText().toString();
//                emailid = vendoremailid.getText().toString();

                String vname = vendorname.getText().toString();
                String vcompanyname = vendorcompanyname.getText().toString();
                String vaddress = vendoraddress.getText().toString();
                String vmobileno = vendormobileno.getText().toString();
                String vemailid = vendoremailid.getText().toString();

                boolean check = ValidateInfo(vname, vcompanyname, vaddress, vmobileno, vemailid);

                if (check == true) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("VendorDetails");
                    HashMap<String, String> map = new HashMap<>();
                    String key = databaseReference.push().getKey();

                    Map newpost = new HashMap();
                    newpost.put("VendorName", vname);
                    newpost.put("VendorCompanyName", vcompanyname);
                    newpost.put("VendorAddress", vaddress);
                    newpost.put("VendorMobileNo", vmobileno);
                    newpost.put("VendorEmailID", vemailid);
                    databaseReference.child("" + System.currentTimeMillis()).setValue(map);


                    databaseReference.child(key).setValue(newpost);

                    Toast.makeText(getApplicationContext(), "Data Insert Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    //    Toast.makeText(getApplicationContext(), "Data Insert Successfully", Toast.LENGTH_SHORT).show();


                }


//                if (name.isEmpty() || companyname.isEmpty() || address.isEmpty() || mobileno.isEmpty() || emailid.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please Enter The Data...", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Vendor Add Successfully...!!", Toast.LENGTH_SHORT).show();
//
//                    databaseReference = FirebaseDatabase.getInstance().getReference("Vendor Add Update");
//                    String key = databaseReference.push().getKey();
//
//                    Map newpost = new HashMap();
//                    newpost.put("Vendor Name", name);
//                    newpost.put("Vendor Company Name", companyname);
//                    newpost.put("Vendor Address", address);
//                    newpost.put("Vendor MobileNo", mobileno);
//                    newpost.put("Vendor EmailID", emailid);
//
//
//                    databaseReference.child(key).setValue(newpost);
//                }
            }
        });

    }

    //Vendor Mobile Number Validation
//    boolean validatemobile(String input) {
//        Pattern pattern = Pattern.compile("[6-9] [0-9] [9]");
//        Matcher matcher = pattern.matcher(input);
//        return matcher.matches();
//
//
//    }

    //Vendor Email ID Validation
//    private boolean EmailAddress(EditText email) {
//        String emailInput = email.getText().toString();
//
//        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            Toast.makeText(this, "Please Enter Valid EmailID", Toast.LENGTH_SHORT).show();
//            return true;
//
//        }
//    }


    private Boolean ValidateInfo(String vname, String vcompanyname, String vaddress, String vmobileno, String vemailid) {
        if (vname.length() == 0) {
            vendorname.requestFocus();
            vendorname.setError("Vendor Name is Required");
            return false;
        } else if (vcompanyname.length() == 0) {
            vendorcompanyname.requestFocus();
            vendorcompanyname.setError(" Vendor Comapany is Required");
            return false;
        } else if (vaddress.length() == 0) {
            vendoraddress.requestFocus();
            vendoraddress.setError("Vendor Address is Required");
            return false;
        } else if (vmobileno.length() == 0) {
            vendormobileno.requestFocus();
            vendormobileno.setError("Vendor Mobile Number is Required");
            return false;
        } else if (vemailid.length() == 0) {
            vendoremailid.requestFocus();
            vendoremailid.setError("Vendor EmailID is Reqired");
            return false;
        } else {
            return true;
        }
    }
}