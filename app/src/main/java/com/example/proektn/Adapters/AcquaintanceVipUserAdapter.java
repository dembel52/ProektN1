package com.example.proektn.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.proektn.R;
import com.example.proektn.Screens.Users;

import java.util.ArrayList;

public class AcquaintanceVipUserAdapter extends RecyclerView.Adapter<AcquaintanceVipUserAdapter.MyViewHolder>{
    private ArrayList<Users> list;

    private AcquaintanceVipUserAdapter.OnAcquaintanceVipClickListener listener;

    public interface OnAcquaintanceVipClickListener{
        void onAcquaintanceVipClick(int position);
    }
    public void setOnAcquaintanceVipClickListener(AcquaintanceVipUserAdapter.OnAcquaintanceVipClickListener listener){
        this.listener = listener;
    }
    public AcquaintanceVipUserAdapter(ArrayList<Users> list){
        this.list=list;
    }

    public void setUsers(ArrayList list) {
        this.list = list;
    }
    @NonNull
    @Override
    public AcquaintanceVipUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.vip_photo_item,viewGroup,false);
        AcquaintanceVipUserAdapter.MyViewHolder viewHolder = new AcquaintanceVipUserAdapter.MyViewHolder(view,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AcquaintanceVipUserAdapter.MyViewHolder myViewHolder, int i) {
        Users currentUser;
        currentUser = list.get(i);


        Glide.with(myViewHolder.imagePhoto)
                .load(currentUser.getAvatarUserUrl())
                .into(myViewHolder.imagePhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagePhoto;

        public MyViewHolder(@NonNull View itemView, final AcquaintanceVipUserAdapter.OnAcquaintanceVipClickListener listener) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.vipUserListItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onAcquaintanceVipClick(position);
                        }
                    }
                }
            });
        }
    }
}
