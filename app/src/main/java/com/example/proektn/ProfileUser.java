package com.example.proektn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileUser extends AppCompatActivity {

     Users users;
    public ImageView avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        avatarImage = findViewById(R.id.imageViewAvatar);

        Intent intent=getIntent();

        final String userId = intent.getStringExtra("UserId");
        Toast.makeText(ProfileUser.this,userId,Toast.LENGTH_SHORT).show();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users = documentSnapshot.toObject(Users.class);

                if(users.getAvatarUserUrl()!=null) {
                    Glide.with(avatarImage.getContext())
                            .load(users.getAvatarUserUrl())
                            .into(avatarImage);
                }
            }
        });

    }
}
