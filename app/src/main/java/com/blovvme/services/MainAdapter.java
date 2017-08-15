package com.blovvme.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Blovvme on 8/14/17.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<String> array;
    public MainAdapter (ArrayList<String> randomStringList) {
        array = randomStringList;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.array_list_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(array.get(position));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

        }
    }
}
