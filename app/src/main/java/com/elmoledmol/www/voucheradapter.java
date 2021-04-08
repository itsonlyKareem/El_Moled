package com.elmoledmol.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class voucheradapter extends RecyclerView.Adapter<voucheradapter.mh>{
ArrayList<vouchersmodel>list;
Context context;

    public voucheradapter(ArrayList<vouchersmodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public voucheradapter.mh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.voucherscard,parent,false);
        return new mh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull voucheradapter.mh holder, int position) {
holder.title.setText(list.get(position).getTitle());
        holder.code.setText(list.get(position).getCode());
        holder.date.setText("Valid until "+list.get(position).getDate());
        holder.percentage.setText(list.get(position).getPercentage()+"%");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
class mh extends RecyclerView.ViewHolder{
        TextView title,code,date,percentage;
    public mh(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.offertitle);
        code=itemView.findViewById(R.id.offercode);
        date=itemView.findViewById(R.id.offerdate);
        percentage=itemView.findViewById(R.id.offerpercentage);

    }
}
}

