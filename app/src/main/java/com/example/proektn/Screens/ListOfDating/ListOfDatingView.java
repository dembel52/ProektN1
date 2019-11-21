package com.example.proektn.Screens.ListOfDating;

import com.example.proektn.Screens.Users;

import java.util.ArrayList;

public interface ListOfDatingView {
    void showData(ArrayList<Users> users);
    void showError();
}
