package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CustomerAddUpdate extends AppCompatActivity {
    EditText Customername;
    EditText Customercompanyname;
    EditText Customeraddress;
    EditText Customermobileno;
    EditText Customeremailid;
    ImageButton imageButton;
    String cname, ccompanyname, caddress, cmobileno, cemailid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add_update);

        Customername = findViewById(R.id.ETcustomername);
        Customercompanyname = findViewById(R.id.ETcustomercompanyname);
        Customeraddress = findViewById(R.id.ETcustomeraddress);
        Customermobileno = findViewById(R.id.ETcustomermobileno);
        Customeremailid = findViewById(R.id.ETcustomeremailid);
        imageButton = findViewById(R.id.IBCustomeraddupdate);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cname = Customername.getText().toString();
                ccompanyname = Customercompanyname.getText().toString();
                caddress = Customeraddress.getText().toString();
                cmobileno = Customermobileno.getText().toString();
                cemailid = Customeremailid.getText().toString();

                boolean Check = ValidateInfo(cname, ccompanyname, caddress, cmobileno, cemailid);


                if (Check == true) {

                    databaseReference = FirebaseDatabase.getInstance().getReference("CustomerDetails");
                    HashMap<String, String> map = new HashMap<>();
                    String key = databaseReference.push().getKey();

                    Map newpost = new HashMap();
                    newpost.put("CustomerName", cname);
                    newpost.put("CustomerCompanyName", ccompanyname);
                    newpost.put("CustomerAddress", caddress);
                    newpost.put("CustomerMobileNo", cmobileno);
                    newpost.put("CustomerEmailID", cemailid);

                    databaseReference.child("" + System.currentTimeMillis()).setValue(map);


                    databaseReference.child(key).setValue(newpost);


                    Toast.makeText(getApplicationContext(), "Customer Data Insert Successfully", Toast.LENGTH_SHORT).show();

                } else {


                }
            }
        });


    }

    private Boolean ValidateInfo(String cname, String ccompanyname, String caddress, String cmobileno, String cemailid) {
        if (cname.length() == 0) {
            Customername.requestFocus();
            Customername.setError("Customer Name is Required");
            return false;
        } else if (ccompanyname.length() == 0) {
            Customercompanyname.requestFocus();
            Customercompanyname.setError(" Customer Company is Required");
            return false;
        } else if (caddress.length() == 0) {
            Customeraddress.requestFocus();
            Customeraddress.setError("Customer Address is Required");
            return false;
        } else if (cmobileno.length() == 0) {
            Customermobileno.requestFocus();
            Customermobileno.setError("Customer Mobile No is Required");
            return false;
        } else if (cemailid.length() == 0) {
            Customeremailid.requestFocus();
            Customeremailid.setError("Customer EmailID is Required");
            return false;
        } else {
            return true;
        }
    }
}