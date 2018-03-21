package com.example.techeasesol.chatapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.techeasesol.chatapp.Fragments.ChatFragment;
import com.example.techeasesol.chatapp.Fragments.FriendsFragment;
import com.example.techeasesol.chatapp.Fragments.RequestFragment;

/**
 * Created by ak603 on 3/20/2018.
 */

public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;
            case 1:
                ChatFragment chatFragmet = new ChatFragment();
                return chatFragmet;
            case 2:
                FriendsFragment friendFragment = new FriendsFragment();
                return friendFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Request";
            case 1:
                return "Chat";
            case 2:
                return "Friends";
                default:
                    return null;
        }
    }
}
