package com.example.proektn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatingProfile extends AppCompatActivity {
    private EditText text1;
    private EditText text2;
    private Button button;
    private Button button1;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_profile);
        spinner = (Spinner) findViewById(R.id.spinner);
        text1=findViewById(R.id.editText);
        text2=findViewById(R.id.editText3);
        button = findViewById(R.id.button3);
        button1 = findViewById(R.id.button4);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        final String userId = intent.getStringExtra("id");

        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Users users = documentSnapshot.toObject(Users.class);
               // if(users.getName()!=null){ }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = new Users(text1.getText().toString(),text2.getText().toString(),userId,spinner.getSelectedItem().toString());
                if(userId != null) {
                    db.collection("Üsers").document(userId).set(users);
                }else{
                    Toast.makeText(CreatingProfile.this,"null",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
