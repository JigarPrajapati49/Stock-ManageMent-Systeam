package com.jigarprajapati.stockmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.ProductName.setText(user.getProductname());
        holder.ProductColor.setText(user.getProductcolor());
        holder.ProductSize.setText(user.getProductSize());
        holder.ProductDescription.setText(user.getProductDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ProductName, ProductColor, ProductSize, ProductDescription;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ProductName = itemView.findViewById(R.id.tvfirstname);
            ProductColor = itemView.findViewById(R.id.tvlastname);
            ProductSize = itemView.findViewById(R.id.tvage);
            ProductDescription = itemView.findViewById(R.id.tvdescription);
        }
    }
}
