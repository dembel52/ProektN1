package com.example.proektn.Screens.Accountancy;

import com.example.proektn.Screens.CreatingProfile.CreatingProfileView;
import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class AccountancyPresenter {


    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CreatingProfileView view;
    public void loadData(String userId){
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
