package com.example.proektn.Screens.ProfileUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.proektn.Adapters.ProfileUsersPhotoAdapter;
import com.example.proektn.R;
import com.example.proektn.Screens.Users;


import java.util.ArrayList;

public class ProfileUser extends AppCompatActivity implements ProfileUserView{

    public ImageView avatarImage;
    private RecyclerView userRecyclerView;
    private ProfileUsersPhotoAdapter photoAdapter;
    private RecyclerView.LayoutManager userLayoutManager;
    private ProfileUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        presenter = new ProfileUserPresenter(this);
        avatarImage = findViewById(R.id.imageViewAvatar);

        Intent intent = getIntent();
        final String userId = intent.getStringExtra("UserId");
        presenter.loadData(userId);
        
        ArrayList list = new ArrayList();
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3932?alt=media&token=c9656806-c19b-42d5-8589-ddf295ba3351");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3932?alt=media&token=c9656806-c19b-42d5-8589-ddf295ba3351");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
        list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");

        userRecyclerView = findViewById(R.id.photoRecyclerView);
        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.addItemDecoration(new DividerItemDecoration(
                userRecyclerView.getContext(),DividerItemDecoration.HORIZONTAL));
        userLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        photoAdapter = new ProfileUsersPhotoAdapter(list);

        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(photoAdapter);

    }

    @Override
    public void showData(Users users) {
        if(users.getAvatarUserUrl()!=null) {
            Glide.with(avatarImage.getContext())
                    .load(users.getAvatarUserUrl())
                    .into(avatarImage);
        }
    }
}
