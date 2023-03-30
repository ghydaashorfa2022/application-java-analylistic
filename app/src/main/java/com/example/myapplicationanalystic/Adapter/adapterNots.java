package com.example.myapplicationanalystic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.myapplicationanalystic.model.nots;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterNots extends RecyclerView.Adapter<adapterNots.ViewHolder> {
    private List<nots> items;
    private LayoutInflater mInflater;
    private ItemClickListener2 itemClickListener2;
    Context context;

    public adapterNots(Context context, List<nots> data, ItemClickListener2 onClick2) {
        this.mInflater = LayoutInflater.from(context);
        this.items = data;
        this.itemClickListener2 = onClick2;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nots, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     //   holder.note.setText(items.get(position).getHeader());
        holder.notbody.setText(items.get(position).getbody());
        Picasso.with(context).load(items.get(position).getImage()).fit().centerInside().into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener2.onItemClick2(holder.getAdapterPosition(), items.get(position).getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, com.example.myapplicationanalystic.Adapter.ViewHolder {
        public TextView note;
        public TextView notbody;
        public LinearLayout card;
        public ImageView image;
        public ViewHolder(View view) {
            super(view);
          //  this.note = itemView.findViewById(R.id.not);
            this.card = itemView.findViewById(R.id.cardnots);
            this.notbody=itemView.findViewById(R.id.notbody);
            this.image= itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {


        }


    }

    nots getItem(int id) {
        return items.get(id);
    }


    public interface ItemClickListener2 {
        void onItemClick2(int position, String id);
    }

}
