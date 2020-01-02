package com.example.proektn.Screens.Acquaintance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.proektn.Adapters.AcquaintanceAdapter;
import com.example.proektn.Adapters.ListOfDatingUserAdapter;
import com.example.proektn.R;
import com.example.proektn.Screens.ListOfDating.ListOfDating;
import com.example.proektn.Screens.ProfileUser.ProfileUser;
import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Acquaintance extends AppCompatActivity {

    private ArrayList<Users> usersArrayList= new ArrayList<>();
    private RecyclerView userRecyclerView;
    private AcquaintanceAdapter userAdapter;
    private RecyclerView.LayoutManager userLayoutManager;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquaintance);

        Query capitalCities = db.collection("Users");



        capitalCities.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments() ) {

                        Users users = document.toObject(Users.class);
                        usersArrayList.add(users);


                        //Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                    buildRecyclerView();
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());

                }
            }
        });

        //buildRecyclerView();
    }
    private void buildRecyclerView() {
        userRecyclerView = findViewById(R.id.recyclerViewZnac);
        userRecyclerView.setHasFixedSize(true);
     //   userRecyclerView.addItemDecoration(new DividerItemDecoration(
               // userRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
        userLayoutManager = new LinearLayoutManager(this);
        userAdapter = new AcquaintanceAdapter(usersArrayList);
        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(userAdapter);
// свайп
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(userAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(userRecyclerView);
// свайп
        userAdapter.setOnUserClickListener(new AcquaintanceAdapter.OnAcquaintanceClickListener() {
            @Override
            public void onAcquaintanceClick(int position) {
                Toast.makeText(Acquaintance.this,"rty",Toast.LENGTH_SHORT).show();
            }


        });
    }
}
