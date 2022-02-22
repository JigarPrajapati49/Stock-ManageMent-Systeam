package com.jigarprajapati.stockmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder3> {

    Context context;
    ArrayList<Customerpojo> list3;

    public CustomerAdapter(Context context, ArrayList<Customerpojo> list3) {
        this.context = context;
        this.list3 = list3;
    }


    @NonNull
    @Override
    public CustomerAdapter.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
        return new MyViewHolder3(v1);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.MyViewHolder3 holder, int position) {
        Customerpojo customerpojo = list3.get(position);
        holder.CustomerName.setText(customerpojo.getCustomerName());
        holder.CustomerCompanyName.setText(customerpojo.getCustomerCompanyName());
        holder.CustomerAddress.setText(customerpojo.getCustomerAddress());
        holder.CustomerMobileNo.setText(customerpojo.getCustomerMobileNo());
        holder.CustomerEmailID.setText(customerpojo.getCustomerEmailID());

    }

    @Override
    public int getItemCount() {
        return list3.size();
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder {
        TextView CustomerName, CustomerCompanyName, CustomerAddress, CustomerMobileNo, CustomerEmailID;

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);

            CustomerName = itemView.findViewById(R.id.rcname);
            CustomerCompanyName = itemView.findViewById(R.id.rccompanyname);
            CustomerAddress = itemView.findViewById(R.id.rcaddress);
            CustomerMobileNo = itemView.findViewById(R.id.rcmobileno);
            CustomerEmailID = itemView.findViewById(R.id.rcemailid);
        }
    }
}
