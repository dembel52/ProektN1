package com.example.proektn.Screens.ProfileUser;

import com.bumptech.glide.Glide;
import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileUserPresenter {
    public ProfileUserPresenter(ProfileUserView view) {
        this.view = view;
    }

    private ProfileUserView view;
    public void loadData(final String userId){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               Users users = documentSnapshot.toObject(Users.class);

                view.showData(users);
            }
        });
    }
}
