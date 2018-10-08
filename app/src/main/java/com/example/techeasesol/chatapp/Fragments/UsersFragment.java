package com.example.techeasesol.chatapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import com.example.techeasesol.chatapp.R;
import com.example.techeasesol.chatapp.models.Users;
import com.example.techeasesol.chatapp.utils.AlertUtils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UsersFragment extends Fragment {
    AlertDialog alertDialog;
    private Toolbar mToolbar;
    RecyclerView rvUserList;
    private FirebaseRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        getActivity().setTitle("All Users");

        DatabaseReference mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        rvUserList = view.findViewById(R.id.user_list);
        rvUserList.setHasFixedSize(true);
        rvUserList.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(mUserDatabase, new SnapshotParser<Users>() {
                            @NonNull
                            @Override
                            public Users parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Users(snapshot.child("name").getValue().toString());
                            }
                        })
                        .build();

       adapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Users model) {
              holder.setName(model.getName());
            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_users_layout, parent, false);

                return new UsersViewHolder(view);
            }
           @Override
           public void onDataChanged() {
               rvUserList.setAdapter(adapter);
               adapter.notifyDataSetChanged();
           }
        };



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void  setName(String name){
            TextView userName = mView.findViewById(R.id.name);
            userName.setText(name);
        }
    }
}
