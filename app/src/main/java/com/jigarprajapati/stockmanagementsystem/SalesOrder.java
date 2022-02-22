package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SalesOrder extends AppCompatActivity {
    ImageButton salesorder;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        salesorder=findViewById(R.id.IBsalesordders);
        textView=findViewById(R.id.SalesOrderBackArrow);

        salesorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesOrder.this,RefineSearch.class);
                startActivity(intent);
                //finish();

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SalesOrder.this,Home.class);
                startActivity(intent);
            }
        });
    }
}