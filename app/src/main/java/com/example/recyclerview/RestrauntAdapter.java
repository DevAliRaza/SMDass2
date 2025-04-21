package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RestrauntAdapter extends RecyclerView.Adapter<RestrauntAdapter.ViewHolder> {
    ArrayList<Restraunt> restraunts;

    ItemSelected parentActivity;


    public interface ItemSelected {
        public void onItemClicked(int index);

    }

    public RestrauntAdapter(Context context, ArrayList<Restraunt> list) {
        parentActivity = (ItemSelected) context;
        restraunts = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvrating, tvname, tvlocation, tvphone, tvdescription;
        ImageView imgname, imglocation, imgphone, imgdescription, imgcall, imgmessgae;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvrating = itemView.findViewById(R.id.tvrating);
            tvdescription = itemView.findViewById(R.id.tvdescription);
            tvlocation = itemView.findViewById(R.id.tvlocation);
            tvname = itemView.findViewById(R.id.tvname);
            tvphone = itemView.findViewById(R.id.tvphone);
            imgname = itemView.findViewById(R.id.imgname);
            imglocation = itemView.findViewById(R.id.imglocation);
            imgphone = itemView.findViewById(R.id.imgphone);
            imgdescription = itemView.findViewById(R.id.imgdescription);
            imgcall = itemView.findViewById(R.id.imgcall);
            imgmessgae = itemView.findViewById(R.id.imgmessage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentActivity.onItemClicked(restraunts.indexOf((Restraunt) itemView.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(restraunts.get(position));
        holder.tvrating.setText(restraunts.get(position).getRating());
        holder.tvphone.setText(restraunts.get(position).getPhone());
        holder.tvname.setText(restraunts.get(position).getName());
        holder.tvlocation.setText(restraunts.get(position).getLocation());
        holder.tvdescription.setText(restraunts.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return restraunts.size();
    }

    public void sortByRatingDesc() {
        Collections.sort(restraunts, new Comparator<Restraunt>() {
            @Override
            public int compare(Restraunt item1, Restraunt item2) {
                // Assuming higher ratings should come first, hence the reverse order
                return Float.compare(Float.parseFloat(item2.getRating()), Float.parseFloat(item1.getRating()));
            }
        });
    }
}
