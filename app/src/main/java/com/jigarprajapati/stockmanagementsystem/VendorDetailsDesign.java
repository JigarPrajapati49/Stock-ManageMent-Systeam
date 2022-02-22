package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class VendorDetailsDesign extends AppCompatActivity {
    ImageButton Vendor,VendorEdit,VendorDelete;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details_design);
        Vendor=findViewById(R.id.vendor);
        VendorEdit=findViewById(R.id.vendoredit);
        VendorDelete=findViewById(R.id.vendordelete);
        builder=new AlertDialog.Builder(this);
        Vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VendorDetailsDesign.this,PurchaseOrders.class);
                startActivity(intent);

            }
        });

        VendorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VendorDetailsDesign.this,VendorAppUpdate.class);
                startActivity(intent);
            }
        });

        VendorDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Conform Delete...")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Toast.makeText(VendorDetailsDesign.this, "Delete SuccessFully...", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(VendorDetailsDesign.this, "NO", Toast.LENGTH_SHORT).show();
                            }
                        }) ;
                AlertDialog alertDialog=builder.create();
                alertDialog.setTitle("Conform Delete...");
                alertDialog.show();
            }
        });

    }
}