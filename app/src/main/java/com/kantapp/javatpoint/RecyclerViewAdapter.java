package com.kantapp.javatpoint;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Android-1 on 10/23/2017.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    ArrayList tutorialList;
    public RecyclerViewAdapter(ArrayList tutorialList)
    {
        this.tutorialList=tutorialList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.textRow.setText(tutorialList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return tutorialList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textRow;

        public ViewHolder(View itemView) {
            super(itemView);
            textRow=itemView.findViewById(R.id.textRow);
        }
    }
}
