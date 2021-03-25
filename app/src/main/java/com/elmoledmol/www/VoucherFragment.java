package com.elmoledmol.www;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class VoucherFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<vouchersmodel> list=new ArrayList<>();
    voucheradapter voucheradapter;
    ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);
        recyclerView = view.findViewById(R.id.voucher);
        back=view.findViewById(R.id.backAddAddresses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list.add(new vouchersmodel("Boutique Discount", "16321#223", "Valid until February 2020", "20%"));
        list.add(new vouchersmodel("Boutique Discount", "16321#223", "Valid until February 2020", "20%"));
        voucheradapter=new voucheradapter(list,getContext());
        recyclerView.setAdapter(voucheradapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2,new settingfragment()).commit();
            }
        });

        return view;
    }
}