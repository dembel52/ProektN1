package com.example.proektn;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<Users> users;
    private OnUserClickListener listener;

    public interface OnUserClickListener{
        void onUserClick(int position);
    }

    public void setOnUserClickListener(OnUserClickListener listener){
        this.listener = listener;
    }

    public UserAdapter(ArrayList<Users> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item,viewGroup,false);
        UserViewHolder viewHolder = new UserViewHolder(view,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        Users currentUser = users.get(i);

        Glide.with(userViewHolder.avatarImageView.getContext())
                .load(currentUser.getAvatarUserUrl())
                .into(userViewHolder.avatarImageView);

        userViewHolder.userNameTextView.setText(currentUser.getName()+" 30");

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        public ImageView avatarImageView;
        public TextView userNameTextView;

        public UserViewHolder(@NonNull View itemView, final OnUserClickListener listener) {
            super(itemView);

            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);

            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onUserClick(position);
                        }

                    }

                }
            });
        }
    }
}
