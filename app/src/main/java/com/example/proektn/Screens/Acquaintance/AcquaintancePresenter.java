package com.example.proektn.Screens.Acquaintance;

import com.example.proektn.Screens.ProfileUser.ProfileUserView;
import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AcquaintancePresenter {
    public AcquaintancePresenter(AcquaintanceView view) {
        this.view = view;
    }
    private ArrayList<Users> usersArrayList = new ArrayList<>();
    private AcquaintanceView view;
    public void loadData(){

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query capitalCities = db.collection("Users");



        capitalCities.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments() ) {

                        Users users = document.toObject(Users.class);
                        usersArrayList.add(users);
                        view.showData(usersArrayList);

                        //Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());

                }
            }
        });
    }
}
