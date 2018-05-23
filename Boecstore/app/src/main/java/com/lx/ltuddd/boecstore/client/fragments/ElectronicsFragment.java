package com.lx.ltuddd.boecstore.client.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.AdapterElectronics;
import com.lx.ltuddd.boecstore.client.interfaces.OnLoadSearchView;
import com.lx.ltuddd.boecstore.client.objects.Electronics;

import java.util.ArrayList;

public class ElectronicsFragment extends Fragment implements OnLoadSearchView {
    private ArrayList<Electronics> ls = new ArrayList<>();
    RecyclerView rc_view;
    private AdapterElectronics adapterElectronics;
    private DatabaseReference mDatabase;

    public ElectronicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference("item");
        init(v);
        return v;
    }

    public void init(View v) {
        rc_view = (RecyclerView) v.findViewById(R.id.rc_items);
        adapterElectronics = new AdapterElectronics(getContext(), ls);
        rc_view.setLayoutManager(new GridLayoutManager(getContext(), 3));

        rc_view.setAdapter(adapterElectronics);
        adapterElectronics.notifyDataSetChanged();
        loadData();

    }

    public void loadData() {
        mDatabase.orderByChild("type").equalTo(2).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //lang nghe su kien khi 1 cay con add vao
                String id, name, des, url;
                Double price;
                float saleoff;
                id = dataSnapshot.child("id").getValue().toString();
                name = dataSnapshot.child("name").getValue().toString();
                des = dataSnapshot.child("description").getValue().toString();
                url = dataSnapshot.child("urlImage").getValue().toString();
                price = Double.valueOf(dataSnapshot.child("price").getValue().toString());
                saleoff = Float.valueOf(dataSnapshot.child("saleOff").getValue().toString());
                ls.add(new Electronics(id, name, price, saleoff, des, url.split(";")));
                adapterElectronics.setLs(ls);
                adapterElectronics.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //lang nghe su kien khi child trong nhanh co su thay doi
                //ta ve snapshot thay doi
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // 1 cay con bi xoa
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                          1 cay con bi di chuyen
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onLoadSearchView(String key) {
        adapterElectronics.getFilter().filter(key);
    }
}
