package com.example.techeasesol.chatapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.techeasesol.chatapp.Adapters.SectionPagerAdapter;
import com.example.techeasesol.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment   {
    FirebaseAuth mAuth;
    private Toolbar mtoolbar;
    private ViewPager viewPager;
    TabLayout tabLayout;
    private SectionPagerAdapter sectionPagerAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = view.findViewById(R.id.main_tabs);
        mAuth = FirebaseAuth.getInstance();
        mtoolbar =  view.findViewById(R.id.main_page_toolbar);
        viewPager = view.findViewById(R.id.viewpager);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Chat App");

        sectionPagerAdapter = new SectionPagerAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager());
        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
          sendToRegistration();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.main_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.main_logout_btn) {
            FirebaseAuth.getInstance().signOut();
            Fragment fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
        }
        else if(item.getItemId() == R.id.setting){
            Fragment setting = new SettingFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, setting).addToBackStack("").commit();
        }
        else if(item.getItemId() == R.id.create_acc){
            Fragment registration = new RegisterationFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, registration).addToBackStack("").commit();
        }
        else if(item.getItemId() == R.id.all_users){
            Fragment registration = new UsersFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, registration).addToBackStack("").commit();
        }
        return super.onOptionsItemSelected(item);
    }
    public void sendToRegistration(){
        Fragment fragment = new RegisterationFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
    }
}
