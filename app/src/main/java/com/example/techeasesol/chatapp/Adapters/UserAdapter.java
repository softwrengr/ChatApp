package com.example.techeasesol.chatapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techeasesol.chatapp.R;
import com.example.techeasesol.chatapp.models.Users;

import java.util.List;

/**
 * Created by eapple on 08/10/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<Users> mDataSet;
    private String mId;


    UserAdapter(List<Users> dataSet, String id) {
        mDataSet = dataSet;
        mId = id;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_users_layout, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Users chat = mDataSet.get(position);
        holder.mTextView.setText(chat.getName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * Inner Class for a recycler view
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(View v) {
            super(v);
            mTextView =  itemView.findViewById(R.id.name);
        }
    }
}
