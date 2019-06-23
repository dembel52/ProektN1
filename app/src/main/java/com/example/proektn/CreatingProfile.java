package com.example.proektn;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreatingProfile extends AppCompatActivity {
    private EditText text1;

    private static final int RC_IMAGE_PICKER = 123;

    FirebaseStorage storage;
    StorageReference userImageStorageReferense;
    StorageReference imageReference;
    Uri selectedImageUrl;
    Uri downloadUri;

    private Button button;
    private Button button1;
    private Spinner spinner;


    int myYear = 1990;
    int myMonth = 01;
    int myDay = 01;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_profile);

        storage = FirebaseStorage.getInstance();
        userImageStorageReferense = storage.getReference().child("user_img");

        spinner = findViewById(R.id.spinner);
        text1 = findViewById(R.id.editText);

        button = findViewById(R.id.button3);
        button1 = findViewById(R.id.button4);

        //button.setEnabled(false);

        tvDate = findViewById(R.id.tvDate);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        final String userId = intent.getStringExtra("id");

        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Users users = documentSnapshot.toObject(Users.class);
                if(users.getName()!=null){
                    Intent intent = new Intent(CreatingProfile.this, ListOfDating.class);

                    startActivity(intent);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

            }
        });


        //if(downloadUri!=null){button.setEnabled(true);}

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imgStorage();
                Users users = new Users(text1.getText().toString(),(myYear+1),myMonth,myDay,userId,spinner.getSelectedItem().toString(),R.drawable.ic_mood_black_24dp,downloadUri.toString());
                //users.setAvatarUserUrl(downloadUri);
                Toast.makeText(CreatingProfile.this,"users",Toast.LENGTH_SHORT).show();
                if(userId != null) {
                    db.collection("Users").document(userId).set(users);
                    Intent intent = new Intent(CreatingProfile.this, ListOfDating.class);

                    startActivity(intent);
                }else{
                    Toast.makeText(CreatingProfile.this,"null",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onclick(View view) {
        // получаем текущую дату
        /*
        final Calendar cal = Calendar.getInstance();
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH);
        myDay = cal.get(Calendar.DAY_OF_MONTH);
        */
        // инициализируем диалог выбора даты текущими значениями
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                        myYear = year-1;
                        myMonth = monthOfYear+1;
                        myDay = dayOfMonth;
                        tvDate.setText(editTextDateParam);
                    }
                }, myYear, myMonth, myDay);
        datePickerDialog.show();
    }

    public void onclickimg(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent,"выберете фото"),RC_IMAGE_PICKER);
        //imgStorage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_IMAGE_PICKER && resultCode == RESULT_OK){
            selectedImageUrl = data.getData();
            imageReference = userImageStorageReferense.child(selectedImageUrl.getLastPathSegment());
            imgStorage();
        }
    }
    public void imgStorage(){
        UploadTask uploadTask = imageReference.putFile(selectedImageUrl);


        uploadTask = imageReference.putFile(selectedImageUrl);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    Toast.makeText(CreatingProfile.this,"null1",Toast.LENGTH_SHORT).show();
                    throw task.getException();

                }
                Toast.makeText(CreatingProfile.this,"null",Toast.LENGTH_SHORT).show();
                // Continue with the task to get the download URL
                return imageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    if(downloadUri!=null){button.setEnabled(true);}
                    Toast.makeText(CreatingProfile.this,"ne null",Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failures
                    Toast.makeText(CreatingProfile.this,"ne null1",Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        });
    }
}
