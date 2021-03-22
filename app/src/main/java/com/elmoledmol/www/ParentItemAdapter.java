package com.elmoledmol.www;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ViewHolder>{
    private List<ChildItem> ChildItemList;


    public ParentItemAdapter(List<ChildItem>ChildItemList ) {
        this.ChildItemList = ChildItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(ChildItemList.get(position).getImagemodel()).resize(400,400).into(holder.model);
        holder.itemName.setText(ChildItemList.get(position).getProductname());
        holder.discount.setText(ChildItemList.get(position).getDiscount());
        holder.newPrice.setText(ChildItemList.get(position).getNewPrice());
        holder.oldPrice.setText(ChildItemList.get(position).getOldPrice());
        float x = ChildItemList.get(position).getPrice() * (100 - ChildItemList.get(position).getPercentage()) / 100;
        if (ChildItemList.get(position).getPercentage() == 0) {
            holder.linearLayout.setVisibility(View.GONE);
            holder.offer.setVisibility(View.GONE);
            holder.newPrice.setText("EGP " + String.valueOf(ChildItemList.get(position).getPrice()));
        } else {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.offer.setVisibility(View.VISIBLE);
            holder.discount.setText(String.valueOf(ChildItemList.get(position).getPercentage() + "%"));
            holder.newPrice.setText("EGP " + String.valueOf(x));
            holder.oldPrice.setText("EGP " + String.valueOf(ChildItemList.get(position).getPrice()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), productInformation.class);
                intent.putExtra("productName", ChildItemList.get(position).getProductname());
                intent.putExtra("rate", 5);
                intent.putExtra("price", x);
                intent.putExtra("mainProductId",ChildItemList.get(position).getMainid());
                intent.putExtra("brandsId",ChildItemList.get(position).getBrandid());

                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ChildItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView discount, itemName, newPrice, oldPrice;
        ImageView model,logo,offer;
        LinearLayout linearLayout, cardFrame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            discount = itemView.findViewById(R.id.mypercent);
            itemName = itemView.findViewById(R.id.textView);
            newPrice = itemView.findViewById(R.id.textView13);
            oldPrice = itemView.findViewById(R.id.textView14);
            model=itemView.findViewById(R.id.imageView8);
            offer=itemView.findViewById(R.id.imageView12);
            linearLayout=itemView.findViewById(R.id.linearLayout);


        }
    }
}
