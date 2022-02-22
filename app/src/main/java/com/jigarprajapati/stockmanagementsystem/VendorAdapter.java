package com.jigarprajapati.stockmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.MyVeiwHolder2> {

    Context context;
    ArrayList<vendordetail> list1;

    public VendorAdapter(Context context, ArrayList<vendordetail> list1) {
        this.context = context;
        this.list1=list1;
    }


    @NonNull
    @Override
    public MyVeiwHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        return new MyVeiwHolder2(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVeiwHolder2 holder, int position) {
        vendordetail vendordetail = list1.get(position);
        holder.VendorName.setText(vendordetail.getVendorName());
        holder.VendorCompanyName.setText(vendordetail.getVendorCompanyName());
        holder.VendorAddress.setText(vendordetail.getVendorAddress());
        holder.VendorContactNumber.setText(vendordetail.getVendorMobileNo());
        holder.VendorEmailID.setText(vendordetail.getVendorEmailID());


    }

    @Override
    public int getItemCount() {
        return  list1.size();
    }

    public static class MyVeiwHolder2 extends RecyclerView.ViewHolder{
        TextView VendorName, VendorCompanyName, VendorAddress, VendorContactNumber,VendorEmailID;

        public MyVeiwHolder2(@NonNull View itemView) {
            super(itemView);

            VendorName = itemView.findViewById(R.id.rvname);
            VendorCompanyName = itemView.findViewById(R.id.rvcompanyname);
            VendorAddress = itemView.findViewById(R.id.rvaddress);
            VendorContactNumber = itemView.findViewById(R.id.rvmobileno);
            VendorEmailID = itemView.findViewById(R.id.rvemailid);
        }
    }
}
