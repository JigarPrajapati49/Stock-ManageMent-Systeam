package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class SalesEntry extends AppCompatActivity {
    String[] strings ={"Debit","Cash"};
    EditText Productname;
    EditText Customername;
    EditText Salesquality;
    EditText Salesdate;
    EditText Salesprice;
    TextView textView;
    ImageButton salesentry;
    Spinner spinner;
//    TextView Salesback;



    DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_entry);
        textView=findViewById(R.id.SalesEntryBackArrow);
        Productname=findViewById(R.id.ETpname);
        Customername=findViewById(R.id.ETcname);
        Salesquality=findViewById(R.id.ETsalequality);
        Salesdate=findViewById(R.id.ETsalesdate);
        Salesprice=findViewById(R.id.ETsalesprice);
        salesentry=findViewById(R.id.IBsalesentry);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SalesEntry.this,Home.class);
                startActivity(intent);
            }
        });



//        Salesback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SalesEntry.this,Home.class);
//                startActivity(intent);
//            }
//        });




        salesentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pname =Productname.getText().toString();
                String cname=Customername.getText().toString();
                String squality =Salesquality.getText().toString();
                String sdate =Salesdate.getText().toString();
                String sprice =Salesprice.getText().toString();







            //validation call
                boolean Check =ValidateInfo(pname,cname,squality,sdate,sprice);

                if (Check==true){
                    Toast.makeText(getApplicationContext(),"Sales Entry Successfully",Toast.LENGTH_SHORT).show();
                }
                else {


                }

            }
        });


        Spinner spinner = findViewById(R.id.SPsalestype);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,strings);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RefineSearch.this,strings[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Salesdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year =calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(SalesEntry.this,onDateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth +"/"+month+"/"+year;
                Salesdate.setText(date);
            }
        };








    }
// All Validation OF Sales Entry
    private Boolean ValidateInfo(String pname,String cname,String squality,String sdate,String sprice){
        if (pname.length()==0){
            Productname.requestFocus();
            Productname.setError("Product Name is Required");
            return false;
        }
        else if (cname.length()==0){
            Customername.requestFocus();
            Customername.setError("Customer Name is Required");
            return false;
        }
        else if (squality.length()==0){
            Salesquality.requestFocus();
            Salesquality.setError("Quality is Required");
            return false;
        }
        else if (sdate.length()==0){
            Salesdate.requestFocus();
            Salesdate.setError("Date is Required");
            return false;
        }
        else if (sprice.length()==0){
            Salesprice.requestFocus();
            Salesprice.setError("Price Required");
            return false;
        }

        else {
            return true;
        }
    }
}