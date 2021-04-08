package com.elmoledmol.www;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoucherFragment extends Fragment {
    RecyclerView recyclerView;
    voucheradapter voucheradapter;
    ImageView back;
    JsonArrayRequest request;
    RequestQueue requestQueue;
    ArrayList<vouchersmodel> list = new ArrayList<>();
    String url = "http://clothesshopapi2.azurewebsites.net/api/Coupons/List";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);
        recyclerView = view.findViewById(R.id.voucher);
        back = view.findViewById(R.id.backAddAddresses);
//        list.add(new vouchersmodel("Boutique Discount", "16321#223", "Valid until February 2020", "20%"));
//        list.add(new vouchersmodel("Boutique Discount", "16321#223", "Valid until February 2020", "20%"));



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("checkbox3",0);
        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.setCancelable(true);
                    mProgressDialog.dismiss();
                } else {
                    mProgressDialog.setCancelable(false);
                }

                JSONObject jsonObject = null;
                list.clear();

                for (int i=0; i<response.length();i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String time = jsonObject.getString("endAt");
                        String percentage = String.valueOf(jsonObject.getInt("percentage"));
                        String code  = jsonObject.getString("code");
                        list.add(new vouchersmodel(name,code,time.substring(0,10),percentage));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                voucheradapter = new voucheradapter(list,getContext());
                recyclerView.setAdapter(voucheradapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer "+sharedPreferences.getString("acesstoken",null));
                return headers;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment2, new settingfragment()).commit();
            }
        });

        return view;
    }
}