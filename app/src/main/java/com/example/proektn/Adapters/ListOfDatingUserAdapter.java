package com.example.proektn.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proektn.R;
import com.example.proektn.Screens.Users;

import java.util.ArrayList;
import java.util.Calendar;

public class ListOfDatingUserAdapter extends RecyclerView.Adapter<ListOfDatingUserAdapter.UserViewHolder> {

    private ArrayList<Users> users;
    private OnUserClickListener listener;

    int myYear = 1990;
    int myMonth = 01;
    int myDay = 01;

    public interface OnUserClickListener{
        void onUserClick(int position);
    }

    public void setOnUserClickListener(OnUserClickListener listener){
        this.listener = listener;
    }

    public ListOfDatingUserAdapter(ArrayList<Users> users){
        this.users = users;
    }

    public void setUsers(ArrayList<Users> users) {
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


        final Calendar cal = Calendar.getInstance();
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH);
        myDay = cal.get(Calendar.DAY_OF_MONTH);

        userViewHolder.userNameTextView.setText(currentUser.getName()+" "+(myYear-currentUser.getMyYear()));

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
