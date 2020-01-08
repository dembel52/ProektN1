package com.example.proektn.Screens.CreatingProfile;

import android.net.Uri;

import com.example.proektn.Screens.Users;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public interface CreatingProfileView {

    void showImage( Uri downloadUri);

}
