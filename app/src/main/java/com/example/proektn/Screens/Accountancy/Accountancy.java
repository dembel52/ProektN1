package com.example.proektn.Screens.Accountancy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proektn.Screens.CreatingProfile.CreatingProfile;
import com.example.proektn.R;
import com.example.proektn.Screens.Poisk.Poisk;
import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Accountancy extends AppCompatActivity implements AccountancyView{

    private FirebaseAuth auth;
    private EditText text1;
    private EditText text2;
    private Button button;
    private Button button2;
    private TextView textV;
    private TextView textV2;
    private String mVerificationId;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public AccountancyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountancy);



        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        textV = findViewById(R.id.textNameUser);
        textV2 = findViewById(R.id.textView4);


        verificvation();

        auth = FirebaseAuth.getInstance();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();

                text1.setVisibility(View.GONE);
                textV.setVisibility(View.GONE);
                button.setVisibility(View.GONE);

                text2.setVisibility(View.VISIBLE);
                textV2.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Accountancy.this,mVerificationId,Toast.LENGTH_SHORT).show();
                Toast.makeText(Accountancy.this,text2.getText().toString(),Toast.LENGTH_SHORT).show();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, text2.getText().toString());
                // [END verify_with_code]
                signInWithPhoneAuthCredential(credential);
            }
        });



        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(Accountancy.this, CreatingProfile.class);
            intent.putExtra("id",auth.getUid());

            startActivity(intent);
        }
    }
    @Override
    public void showData(Users users) {
        if(users.getName()!=null){
            Intent intent = new Intent(Accountancy.this, Poisk.class);

            startActivity(intent);
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(Accountancy.this,"rrr",Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();

                            Intent intent = new Intent(Accountancy.this, CreatingProfile.class);
                            intent.putExtra("id",user.getUid());
                            startActivity(intent);
                            // ...
                        } else {
                            Toast.makeText(Accountancy.this,"ttt",Toast.LENGTH_SHORT).show();
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void verificvation() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                //Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                //Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };
    }

    private void loginUser(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                text1.getText().toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }


}
