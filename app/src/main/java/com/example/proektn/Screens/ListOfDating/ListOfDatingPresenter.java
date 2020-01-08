package com.example.proektn.Screens.ListOfDating;

import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListOfDatingPresenter {

    private ListOfDatingView view;
    private ArrayList<Users> usersArrayList = new ArrayList<>();

    public ListOfDatingPresenter(ListOfDatingView view) {
        this.view = view;
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void loadData(String poiskPol, String celZnak, long poiskVoz, long poiskVoz1){

        Query capitalCities = db.collection("Users");
           /*     .whereEqualTo("pol", poiskPol)

               .whereGreaterThan("mikroVozrast", poiskVoz).whereLessThan("mikroVozrast", poiskVoz1)
               .whereEqualTo("celZnackom",celZnak)
               .whereEqualTo("userDeti","Нет");

*/

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
                        view.showError();
                }
            }
        });
    }
}
