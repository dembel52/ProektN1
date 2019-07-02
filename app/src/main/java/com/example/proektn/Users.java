package com.example.proektn;

import android.net.Uri;

public class Users {
    private String name;
    private int myYear ;
    private int myMonth ;
    private int myDay ;
    private String id;
    private String pol;
    private int avatarMockUpResorse;
    private String avatarUserUrl;

    public Users() {
    }

    public Users(String name, int myYear, int myMonth, int myDay, String id, String pol, int avatarMockUpResorse, String avatarUserUrl) {
        this.name = name;
        this.myYear = myYear;
        this.myMonth = myMonth;
        this.myDay = myDay;
        this.id = id;
        this.pol = pol;
        this.avatarMockUpResorse = avatarMockUpResorse;
        this.avatarUserUrl = avatarUserUrl;
    }

    public int getAvatarMockUpResorse() {
        return avatarMockUpResorse;
    }

    public void setAvatarMockUpResorse(int avatarMockUpResorse) {
        this.avatarMockUpResorse = avatarMockUpResorse;
    }

    public int getMyYear() {
        return myYear;
    }

    public String getAvatarUserUrl() {
        return avatarUserUrl;
    }

    public void setAvatarUserUrl(String avatarUserUrl) {
        this.avatarUserUrl = avatarUserUrl;
    }

    public void setMyYear(int myYear) {
        this.myYear = myYear;
    }

    public int getMyMonth() {
        return myMonth;
    }

    public void setMyMonth(int myMonth) {
        this.myMonth = myMonth;
    }

    public int getMyDay() {
        return myDay;
    }

    public void setMyDay(int myDay) {
        this.myDay = myDay;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
