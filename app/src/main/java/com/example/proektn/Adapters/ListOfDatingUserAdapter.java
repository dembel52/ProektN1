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
        return new UserViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        Users currentUser;
        currentUser = users.get(i);

        Glide.with(userViewHolder.avatarImageView.getContext())
                .load(currentUser.getAvatarUserUrl())
                .into(userViewHolder.avatarImageView);


        final Calendar cal = Calendar.getInstance();
        int myYear = cal.get(Calendar.YEAR);
        int myMonth = cal.get(Calendar.MONTH);
        int myDay = cal.get(Calendar.DAY_OF_MONTH);

        userViewHolder.userNameTextView.setText(currentUser.getName()+" "+(myYear -currentUser.getMyYear()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView avatarImageView;
        TextView userNameTextView;

         UserViewHolder(@NonNull View itemView, final OnUserClickListener listener) {
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
