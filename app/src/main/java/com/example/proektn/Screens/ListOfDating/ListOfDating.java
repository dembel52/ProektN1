package com.example.proektn.Screens.ListOfDating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.proektn.Screens.ProfileUser.ProfileUser;
import com.example.proektn.R;
import com.example.proektn.Adapters.ListOfDatingUserAdapter;
import com.example.proektn.Screens.Users;

import java.util.ArrayList;

public class ListOfDating extends AppCompatActivity implements ListOfDatingView {

    private ArrayList<Users> usersArrayList;
    private RecyclerView userRecyclerView;
    private ListOfDatingUserAdapter userAdapter;
    private RecyclerView.LayoutManager userLayoutManager;
    private String poiskPol;
    private String celZnak;
    private long poiskVoz;
    private long poiskVoz1;

    private ListOfDatingPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_dating);

        presenter = new ListOfDatingPresenter(this);

        usersArrayList = new ArrayList<>();

        Intent intent = getIntent();
        poiskPol = intent.getStringExtra("poiskPol");
        celZnak = intent.getStringExtra("celZnak");
        poiskVoz = intent.getLongExtra("poiskVoz",5);
        poiskVoz1 = intent.getLongExtra("poiskVoz1",5);

        presenter.loadData( poiskPol, celZnak, poiskVoz,poiskVoz1);

        buildRecyclerView();

    }

    private void buildRecyclerView() {
        userRecyclerView = findViewById(R.id.userListRecyclerView);
        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.addItemDecoration(new DividerItemDecoration(
                userRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
        userLayoutManager = new LinearLayoutManager(this);
        userAdapter = new ListOfDatingUserAdapter(usersArrayList);
        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(userAdapter);

        userAdapter.setOnUserClickListener(new ListOfDatingUserAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(int position) {
                Toast.makeText(ListOfDating.this,usersArrayList.get(0).getAvatarUserUrl(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOfDating.this, ProfileUser.class);
                intent.putExtra("UserId",usersArrayList.get(position).getId());

                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(ArrayList<Users> users) {
        Toast.makeText(ListOfDating.this,"цикл",Toast.LENGTH_SHORT).show();
        usersArrayList=users;
        userAdapter.setUsers(users);
        userAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError() {
        Toast.makeText(ListOfDating.this,"null цикл",Toast.LENGTH_SHORT).show();
    }
}
