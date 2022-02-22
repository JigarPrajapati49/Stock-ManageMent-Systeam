package com.jigarprajapati.stockmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class POlist extends RecyclerView.Adapter<POlist.MyViewHolder> {
    Context context;
    ArrayList<pentry> list1;

    public POlist(Context context, ArrayList<pentry> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v4 = LayoutInflater.from(context).inflate(R.layout.item4, parent, false);
        return new MyViewHolder(v4);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        pentry pentryy = list1.get(position);
        holder.ProductName.setText(pentryy.getProductName());
        holder.VendorName.setText(pentryy.getVendorName());
        holder.PurchaseQuantity.setText(pentryy.getPurchaseQuantity());
        holder.PurchaseDate.setText(pentryy.getPurchaseDate());
        holder.PurchasePrice.setText(pentryy.getPurchasePrice());
        holder.PurchaseType.setText(pentryy.getPurchaseType());


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ProductName, VendorName, PurchaseQuantity, PurchaseDate,PurchasePrice,PurchaseType;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ProductName = itemView.findViewById(R.id.popname);
            VendorName = itemView.findViewById(R.id.povname);
            PurchaseQuantity = itemView.findViewById(R.id.popquantity);
            PurchaseDate = itemView.findViewById(R.id.popdate);
            PurchasePrice = itemView.findViewById(R.id.poprice);
            PurchaseType =itemView.findViewById(R.id.poptype);

        }
    }
}
