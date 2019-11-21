package com.example.proektn.Screens.CreatingProfile;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.proektn.Screens.Users;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreatingProfilePresenter {

    public CreatingProfilePresenter(CreatingProfileView view) {
        this.view = view;
    }

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseStorage storage;


    private CreatingProfileView view;

    public void saveData(String userId, Users users){
        db.collection("Users").document(userId).set(users);
    }

    public void loadData(String userId){
        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Users users = documentSnapshot.toObject(Users.class);

                view.showData(users);
            }
        });
    }

    public void saveImage(final StorageReference imageReference, final UploadTask uploadTask){
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {

                    throw task.getException();

                }

                // Continue with the task to get the download URL
                return imageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {

                   view.showImage(task.getResult());

                } else {
                    // Handle failures

                    // ...
                }
            }
        });
    }
}
