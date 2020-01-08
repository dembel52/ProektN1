package com.example.proektn.Screens.Acquaintance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proektn.Adapters.AcquaintanceAdapter;
import com.example.proektn.Adapters.AcquaintanceVipUserAdapter;
import com.example.proektn.Adapters.ListOfDatingUserAdapter;
import com.example.proektn.Adapters.ProfileUsersPhotoAdapter;
import com.example.proektn.R;
import com.example.proektn.Screens.Accountancy.Accountancy;
import com.example.proektn.Screens.CreatingProfile.CreatingProfile;
import com.example.proektn.Screens.ListOfDating.ListOfDating;
import com.example.proektn.Screens.Poisk.Poisk;
import com.example.proektn.Screens.ProfileUser.ProfileUser;
import com.example.proektn.Screens.ProfileUser.ProfileUserPresenter;
import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Acquaintance extends AppCompatActivity implements AcquaintanceView{

    private ArrayList<Users> usersArrayList= new ArrayList<>();
    private RecyclerView userRecyclerView;
    private AcquaintanceAdapter userAdapter;
    private RecyclerView.LayoutManager userLayoutManager;
    private ImageView menuPoisk;

    public ImageView avatarVipImage;
    private ArrayList<Users> usersVipList= new ArrayList();
    private RecyclerView userVipRecyclerView;
    private AcquaintanceVipUserAdapter photoVipAdapter;
    private RecyclerView.LayoutManager userVipLayoutManager;
    private AcquaintancePresenter presenter;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquaintance);

        menuPoisk = findViewById(R.id.imageViewMenuPoisk);

        presenter = new AcquaintancePresenter(this);
        avatarVipImage = findViewById(R.id.imageViewAvatar);
        presenter.loadData();

        menuPoisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acquaintance.this, Poisk.class);
                startActivity(intent);
            }
        });

        Query capitalCities = db.collection("Users");

        vipRecyclerView();

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
    private void vipRecyclerView() {
        userVipRecyclerView = findViewById(R.id.recyclerVipView);
        userVipRecyclerView.setHasFixedSize(true);
        userVipRecyclerView.addItemDecoration(new DividerItemDecoration(
                userVipRecyclerView.getContext(),DividerItemDecoration.HORIZONTAL));
        userVipLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        photoVipAdapter = new AcquaintanceVipUserAdapter(usersVipList);

        userVipRecyclerView.setLayoutManager(userVipLayoutManager);
        userVipRecyclerView.setAdapter(photoVipAdapter);
        photoVipAdapter.setOnAcquaintanceVipClickListener(new AcquaintanceVipUserAdapter.OnAcquaintanceVipClickListener() {
            @Override
            public void onAcquaintanceVipClick(int position) {
                Intent intent = new Intent(Acquaintance.this, ProfileUser.class);
                intent.putExtra("UserId",usersArrayList.get(position).getId());

                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(ArrayList<Users> users) {

        usersVipList = users;
        photoVipAdapter.setUsers(users);
        photoVipAdapter.notifyDataSetChanged();

    }


    //  Меню

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Acquaintance.this, Accountancy.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    //  Меню
}
