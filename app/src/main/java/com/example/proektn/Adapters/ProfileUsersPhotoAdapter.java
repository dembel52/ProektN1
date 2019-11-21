package com.example.proektn.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.proektn.R;

import java.util.ArrayList;

public class ProfileUsersPhotoAdapter extends RecyclerView.Adapter<ProfileUsersPhotoAdapter.MyViewHolder> {

    private ArrayList list;
    private OnPhotoClickListener listener;

    public interface OnPhotoClickListener{
        void onPhotoClick(int position);
    }
    public void setOnPhotoClickListener(OnPhotoClickListener listener){
        this.listener = listener;
    }
    public ProfileUsersPhotoAdapter(ArrayList<String> list){
        this.list=list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_item,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //Users currentUser = users.get(i);


        Glide.with(myViewHolder.imagePhoto.getContext())
                .load(list.get(i))
                .into(myViewHolder.imagePhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagePhoto;

        public MyViewHolder(@NonNull View itemView, final OnPhotoClickListener listener) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.image_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onPhotoClick(position);
                        }
                    }
                }
            });
        }
    }
}
