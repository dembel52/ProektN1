package com.example.proektn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListOfDating extends AppCompatActivity {




    private ArrayList<Users> usersArrayList;
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager userLayoutManager;
    DocumentReference docRef;
     FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_dating);

        usersArrayList = new ArrayList<>();
        

        attachUserDatabaseReferenceListener();

        buildRecyclerView();

    }

    private void attachUserDatabaseReferenceListener() {
        Query capitalCities = db.collection("Users").whereEqualTo("pol", "Мужской");


        capitalCities.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {

                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments() ) {

                        Users users=document.toObject(Users.class);


                        usersArrayList.add(users);
                        userAdapter.notifyDataSetChanged();

                        //Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());
                    Toast.makeText(ListOfDating.this,"null цикл",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void buildRecyclerView() {
        userRecyclerView = findViewById(R.id.userListRecyclerView);
        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.addItemDecoration(new DividerItemDecoration(
                userRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
        userLayoutManager = new LinearLayoutManager(this);
        userAdapter = new UserAdapter(usersArrayList);

        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(userAdapter);

        userAdapter.setOnUserClickListener(new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(int position) {
                Toast.makeText(ListOfDating.this,usersArrayList.get(0).getAvatarUserUrl(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOfDating.this, ProfileUser.class);
                intent.putExtra("UserId",usersArrayList.get(position).getId());

                startActivity(intent);
            }
        });
    }
}
