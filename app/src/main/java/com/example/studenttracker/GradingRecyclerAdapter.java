package com.example.studenttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GradingRecyclerAdapter extends RecyclerView.Adapter<GradingRecyclerAdapter.ViewHolder> {
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Students> studentsArrayList;

    public GradingRecyclerAdapter(Context mContext, ArrayList<Students> studentsArrayList) {
        this.mContext = mContext;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public GradingRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_grading_student_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//TextView
        holder.textView.setText(studentsArrayList.get(position).getName());

        Glide.with(mContext).load(studentsArrayList.get(position).getImageUrl()).into(holder.imageView);


    }
    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button passButton;
        Button failButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);


//            passButton.setVisibility(View.GONE);
//            failButton.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();

                    }
                }
            });
        }
    }
}