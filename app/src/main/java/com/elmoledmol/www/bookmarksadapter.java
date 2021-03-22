package com.elmoledmol.www;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class bookmarksadapter extends RecyclerView.Adapter<bookmarksadapter.mh> {

    Context context;
    List<featuredinheret> list;

    public bookmarksadapter(Context context, List<featuredinheret> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public bookmarksadapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favoritecard, parent, false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookmarksadapter.mh holder, int position) {
        holder.product.setText(list.get(position).product);
        holder.price.setText(list.get(position).price);
        holder.hiddenprice.setText(list.get(position).hiddenprice);
        Picasso.get().load(list.get(position).getImagemodel()).resize(Integer.valueOf((int) (holder.itemView.getContext().getDisplay().getWidth()*1.5)),holder.itemView.getContext().getDisplay().getHeight()).into(holder.imageView);
//        if (ChildItemList.get(position).getPercentage() == 0) {
//            holder.linearLayout.setVisibility(View.GONE);
//            holder.offer.setVisibility(View.GONE);
//            holder.newPrice.setText("EGP " + String.valueOf(ChildItemList.get(position).getPrice()));
//        } else {
//            holder.linearLayout.setVisibility(View.VISIBLE);
//            holder.offer.setVisibility(View.VISIBLE);
//            holder.discount.setText(String.valueOf(ChildItemList.get(position).getPercentage() + "%"));
//            holder.newPrice.setText("EGP " + String.valueOf(x));
//            holder.oldPrice.setText("EGP " + String.valueOf(ChildItemList.get(position).getPrice()));
//        }

        if (list.get(position).price.equals(list.get(position).hiddenprice)) {
            holder.layout.setVisibility(View.GONE);
            holder.hiddenprice.setVisibility(View.GONE);
            holder.price.setText("EGP "+list.get(position).price);

        } else {
            holder.layout.setVisibility(View.VISIBLE);
            holder.hiddenprice.setVisibility(View.VISIBLE);
            holder.price.setText("EGP "+list.get(position).price);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Warning!")
                        .setMessage("Do you really want to delete this product from bookmarks?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(position);
                                notifyItemRemoved(position);
                                saveDataFavs(list);
                            }
                        })
                        .setNegativeButton(android.R.string.no,null)
                        .setIcon(R.drawable.logo_splash)
                        .show();
                return false;
            }
        });
    }
    private void saveDataFavs(List<featuredinheret> list) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Bookmarks", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("bookmark", json);
        editor.apply();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class mh extends RecyclerView.ViewHolder {
        TextView product, price, hiddenprice;
        ImageView imageView;
        LinearLayout layout;

        public mh(@NonNull View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.textView13);
            hiddenprice = itemView.findViewById(R.id.textView14);
            imageView = itemView.findViewById(R.id.imageView8);
            layout = itemView.findViewById(R.id.linearLayout);

        }
    }


}
