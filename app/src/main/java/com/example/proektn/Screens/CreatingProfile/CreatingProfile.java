package com.example.proektn.Screens.CreatingProfile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;

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

import com.example.proektn.Screens.Poisk.Poisk;
import com.example.proektn.R;
import com.example.proektn.Screens.Accountancy.Accountancy;
import com.example.proektn.Screens.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.ParseException;
import java.util.Date;

public class CreatingProfile extends  AppCompatActivity implements CreatingProfileView{

    private static final int RC_IMAGE_PICKER = 123;

    FirebaseStorage storage;
    StorageReference userImageStorageReferense;
    StorageReference imageReference;
    Uri selectedImageUrl;
    Uri downloadUri;
    UploadTask uploadTask;
    private EditText nameUser;
    private Button button;
    private Spinner spinnerSemPolo;
    private Spinner spinnerCelZnskom;
    private Spinner spinnerVozrostOt;
    private Spinner spinnerVozrostDo;
    private RadioGroup polUser;
    private RadioGroup radioGroupDeti;
    private RadioGroup radioGrypPolZnackom;
    private String userPol;
    private String userDeti;
    private String userPolZnackom;
    long millis;

    private CreatingProfilePresenter presenter;

    private int vozrostOt;
    private int vozrostDo;

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

        Intent intent = getIntent();
        final String userId = intent.getStringExtra("id");

        presenter.loadData(userId);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                vozrostOt = Integer.parseInt(spinnerVozrostOt.getSelectedItem().toString());
                vozrostDo = Integer.parseInt(spinnerVozrostDo.getSelectedItem().toString());

                SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");//задаю формат даты
                String dateInString = myDay+" "+myMonth+" "+myYear;// "29 11 2015";//создаю строку по заданному формату
                try {
                    Date date = formatter.parse(dateInString);//создаю дату через
                     millis = date.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Users users = new Users(nameUser.getText().toString(),(myYear+1),myMonth,myDay,userId,userPol,downloadUri+"",
                        userDeti,userPolZnackom,spinnerSemPolo.getSelectedItem().toString(),
                        spinnerCelZnskom.getSelectedItem().toString(), vozrostOt, vozrostDo, millis);

                Toast.makeText(CreatingProfile.this,"users",Toast.LENGTH_SHORT).show();
                if(userId != null) {
                    presenter.saveData(userId, users);

                    Intent intent = new Intent(CreatingProfile.this, Poisk.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(CreatingProfile.this,"null",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void showData(Users users) {
        if(users.getName()!=null){
            Intent intent = new Intent(CreatingProfile.this, Poisk.class);

            startActivity(intent);
        }
    }

    @Override
    public void showImage(Uri downloadUri) {
        this.downloadUri = downloadUri;

        proverka();
        Toast.makeText(CreatingProfile.this,"ne null",Toast.LENGTH_SHORT).show();
    }

    private void proverka(){
        Toast.makeText(CreatingProfile.this,"proverka",Toast.LENGTH_SHORT).show();
        vozrostOt = Integer.parseInt(spinnerVozrostOt.getSelectedItem().toString());
        vozrostDo = Integer.parseInt(spinnerVozrostDo.getSelectedItem().toString());

        if(nameUser.getText().length() >= 2 && userPol!=null && userDeti!=null && userPolZnackom!=null && downloadUri!=null && vozrostOt <= vozrostDo) {
            Toast.makeText(CreatingProfile.this,"proverka1",Toast.LENGTH_SHORT).show();
            button.setEnabled(true);

        }

    }


    private void initilizetion() {
        storage = FirebaseStorage.getInstance();
        userImageStorageReferense = storage.getReference().child("user_img");

        spinnerSemPolo = findViewById(R.id.spinnerSemPolo);
        spinnerCelZnskom = findViewById(R.id.spinnerCelZnskom);
        spinnerVozrostOt = findViewById(R.id.spinnerVozrostOt);
        spinnerVozrostDo = findViewById(R.id.spinnerVozrostDo);

        nameUser = findViewById(R.id.editTextNameUser);

        button = findViewById(R.id.buttonSaveProfilUser);

        polUser = findViewById(R.id.radioGrypPolPoisk);
        radioGroupDeti = findViewById(R.id.radioGrypDeti);
        radioGrypPolZnackom = findViewById(R.id.radioGrypPolZnackom);
        tvDate = findViewById(R.id.dataUser);

        presenter = new CreatingProfilePresenter(this);
    }

    private void radioButtonGryp() {
        polUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case -1: userPol=null;

                        break;
                    case R.id.polManPoisk: userPol="Мужской";

                        break;
                    case R.id.polGerlPoisk: userPol="Женский";
                        break;
                    default:
                        break;
                }

                proverka();
            }
        });

        radioGroupDeti.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case -1: userDeti=null;
                        break;
                    case R.id.radioButtonDa: userDeti="Да";
                        Toast.makeText(CreatingProfile.this,"deti",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonNo: userDeti="Нет";
                        break;
                }
                proverka();
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
                proverka();
            }
        });

    }
// дата рождения
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
    //Дата рождения

//  Загрузка фото
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
            uploadTask = imageReference.putFile(selectedImageUrl);
            presenter.saveImage( imageReference, uploadTask);
        }
    }

// Загрузка фото

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
                startActivity(new Intent(CreatingProfile.this, Accountancy.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    //  Меню
}
