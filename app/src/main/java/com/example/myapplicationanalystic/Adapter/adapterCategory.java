package com.example.myapplicationanalystic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationanalystic.MainActivity;
import com.example.myapplicationanalystic.NotsActivity2;
import com.example.myapplicationanalystic.R;
import com.example.myapplicationanalystic.model.catagoryy;

import java.util.ArrayList;
import java.util.List;

public class adapterCategory  extends RecyclerView.Adapter<adapterCategory.ViewHolder> {
    private List<catagoryy> mData;
    private LayoutInflater mInflater;
    private MainActivity mClickListener;

    public adapterCategory(Context context, List<catagoryy> data, MainActivity onClick) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = onClick;
    }

    @NonNull
    @Override
    public adapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterCategory.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(mData.get(position).getName());
          holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(holder.getAdapterPosition(), mData.get(position).getId());
                Intent intent = new Intent(holder.itemView.getContext(),NotsActivity2.class);
                intent.putExtra("id",mData.get(position).getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    catagoryy getItem(int id) {
        return mData.get(id);
    }

    public interface ItemClickListene {
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;

        public LinearLayout card;

        public ViewHolder(View view) {
            super(view);
            this.name = itemView.findViewById(R.id.name);

            this.card = itemView.findViewById(R.id.card2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    private class ItemClickListener {
        public void onItemClick(int position, String id) {

        }
    }
}
