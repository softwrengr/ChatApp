package com.example.techeasesol.chatapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


public class LoginFragment extends Fragment {
    EditText etuserEmail, etuserPassword;
    Button btnLogin;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    private Toolbar mtoolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        auth = FirebaseAuth.getInstance();
        auth.getCurrentUser();
        mtoolbar =  view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Chat App");

        etuserEmail = view.findViewById(R.id.user_email);
        etuserPassword = view.findViewById(R.id.user_password);
        btnLogin = view.findViewById(R.id.login);
        auth.getCurrentUser();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        return view;
    }

    public void login() {
        String strEmail = etuserEmail.getText().toString().trim();
        String strPassword = etuserPassword.getText().toString().trim();
        auth.signInWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        Fragment fragment = new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        }
                        else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    });
    }

    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.main_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.create_acc) {
            FirebaseAuth.getInstance().signOut();
            Fragment fragment = new RegisterationFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
