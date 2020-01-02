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
import java.util.Collections;

public class AcquaintanceAdapter extends RecyclerView.Adapter<AcquaintanceAdapter.UserViewHolder> {
    private ArrayList<Users> users;
    private OnAcquaintanceClickListener listener;

    public interface OnAcquaintanceClickListener{
        void onAcquaintanceClick(int position);
    }

    public void setOnUserClickListener(AcquaintanceAdapter.OnAcquaintanceClickListener listener){
        this.listener = listener;
    }

    public AcquaintanceAdapter(ArrayList<Users> users){
        this.users = users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.acquaintance_res_view,viewGroup,false);

        return new AcquaintanceAdapter.UserViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        Users currentUser;
        currentUser = users.get(i);

        Glide.with(userViewHolder.avatarImageView.getContext())
                .load(currentUser.getAvatarUserUrl())
                .into(userViewHolder.avatarImageView);


        //final Calendar cal = Calendar.getInstance();
        //int myYear = cal.get(Calendar.YEAR);
       // int myMonth = cal.get(Calendar.MONTH);
       // int myDay = cal.get(Calendar.DAY_OF_MONTH);

        userViewHolder.userNameTextView.setText(currentUser.getName());

    }

// свайп
    public void onItemDismiss(int position) {
        users.remove(position);
        notifyItemRemoved(position);
    }


    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(users, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(users, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
// свайп

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView avatarImageView;
        TextView userNameTextView;

        UserViewHolder(@NonNull View itemView, final AcquaintanceAdapter.OnAcquaintanceClickListener listener) {
            super(itemView);

            avatarImageView = itemView.findViewById(R.id.imageViewPhotoZnac);
            userNameTextView = itemView.findViewById(R.id.textViewName);

            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onAcquaintanceClick(position);
                        }

                    }

                }
            });
        }
    }
}
