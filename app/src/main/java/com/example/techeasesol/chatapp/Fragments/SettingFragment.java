package com.example.techeasesol.chatapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techeasesol.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SettingFragment extends Fragment {
    DatabaseReference mDataReference;
    FirebaseUser current_user;
    TextView displayName, status;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        displayName = view.findViewById(R.id.displayname);
        status = view.findViewById(R.id.status);
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = current_user.getUid();
        mDataReference = FirebaseDatabase.getInstance().getReference().child("users").child(current_uid);

        mDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String user_status = dataSnapshot.child("status").getValue().toString();
               // String image = dataSnapshot.child("image").getValue().toString();
               // String thumb_image = dataSnapshot.child("thum_image").getValue().toString();

                displayName.setText(name);
                status.setText(user_status);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
