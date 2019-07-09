package com.example.proektn;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
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

import java.util.ArrayList;

public class CreatingProfile extends AppCompatActivity {

    private static final int RC_IMAGE_PICKER = 123;

    FirebaseStorage storage;
    StorageReference userImageStorageReferense;
    StorageReference imageReference;
    Uri selectedImageUrl;
    Uri downloadUri;
    UploadTask uploadTask;
    private EditText nameUser;
    private Button button;
    private Button button1;
    private Spinner spinnerSemPolo;
    private Spinner spinnerCelZnskom;
    private RadioGroup polUser;
    private RadioGroup radioGroupDeti;
    private RadioGroup radioGrypPolZnackom;
    private String userPol;
    private String userDeti;
    private String userPolZnackom;

    int myYear = 1990;
    int myMonth = 01;
    int myDay = 01;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_profile);

        initilizetion();
        radioButtonGryp();

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Users users = new Users(nameUser.getText().toString(),(myYear+1),myMonth,myDay,userId,userPol,downloadUri+"",
                        userDeti,userPolZnackom,spinnerSemPolo.getSelectedItem().toString(),spinnerCelZnskom.getSelectedItem().toString());
                ArrayList list = new ArrayList();
                list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
                list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
                list.add("https://firebasestorage.googleapis.com/v0/b/proectn-c7134.appspot.com/o/user_img%2Fimage%3A3893?alt=media&token=ef73c55d-64e3-466e-8ec5-cf3465cb15c2");
                users.setListPhotoUri(list);
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

    private void initilizetion() {
        storage = FirebaseStorage.getInstance();
        userImageStorageReferense = storage.getReference().child("user_img");

        spinnerSemPolo = findViewById(R.id.spinnerSemPolo);
        spinnerCelZnskom = findViewById(R.id.spinnerCelZnskom);

        nameUser = findViewById(R.id.editTextNameUser);

        button = findViewById(R.id.buttonSaveProfilUser);
        button1 = findViewById(R.id.button4);

        polUser = findViewById(R.id.radioGrypPol);
        radioGroupDeti = findViewById(R.id.radioGrypDeti);
        radioGrypPolZnackom = findViewById(R.id.radioGrypPolZnackom);
        tvDate = findViewById(R.id.dataUser);
    }

    private void radioButtonGryp() {
        polUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case -1: userPol=null;
                        break;
                    case R.id.polManUser: userPol="Мужской";
                        break;
                    case R.id.polGerlUser: userPol="Женский";
                        break;
                }
            }
        });

        radioGroupDeti.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case -1: userDeti=null;
                        break;
                    case R.id.radioButtonDa: userDeti="Да";
                        break;
                    case R.id.radioButtonNo: userDeti="Нет";
                        break;
                }
            }
        });

        radioGrypPolZnackom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case -1: userPolZnackom=null;
                        break;
                    case R.id.radioButtonManUser: userPolZnackom="Мужчиной";
                        break;
                    case R.id.radioButtonGerlUser: userPolZnackom="Женщиной";
                        break;
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
                    Toast.makeText(CreatingProfile.this,"n null",Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        });
    }

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
                startActivity(new Intent(CreatingProfile.this,Accountancy.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
