package com.example.techeasesol.chatapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.techeasesol.chatapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterationFragment extends Fragment {
    EditText etDisplayName, etEmail, etPassword;
    Button btnCreateAcc;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registeration, container, false);
        mAuth = FirebaseAuth.getInstance();
        etDisplayName = view.findViewById(R.id.userdisplayname);
        etEmail = view.findViewById(R.id.email);
        etPassword = view.findViewById(R.id.password);
        btnCreateAcc = view.findViewById(R.id.create_acc);

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayName = etDisplayName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                register_user(displayName, email, password);
            }
        });
        return view;
    }

    public void register_user(final String displayName, String email, String password){
       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                  String uid = current_user.getUid();
                  mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                  HashMap<String, String> userMap = new HashMap<>();
                  userMap.put("name", displayName);
                  userMap.put("status", "Hi there, I'm using chat app");
                  userMap.put("image", "default");
                  userMap.put("thumb_image", "default");
                  mDatabase.setValue(userMap);
               Fragment fragment = new LoginFragment();
               getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
              }
              else {
                  Toast.makeText(getActivity(), "you got some error", Toast.LENGTH_SHORT).show();
              }
           }
       });
    }
}
