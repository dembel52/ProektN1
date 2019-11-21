package com.example.proektn.Screens;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private String name;
    private int myYear ;
    private int myMonth ;
    private int myDay ;
    private String id;
    private String pol;
    private String userDeti;
    private String userPolZnackom;
    private String semPolo;
    private String celZnackom;
    private int avatarMockUpResorse;
    private String avatarUserUrl;
    private int vozrostOt;
    private int vozrostDo;
    private long mikroVozrast;

    public Users() {
    }

    public Users(String name, int myYear, int myMonth, int myDay,
                 String id, String pol, String avatarUserUrl,
                 String userDeti, String userPolZnackom, String semPolo, String celZnackom,
                 int vozrostOt, int vozrostDo, long mikroVozrast) {
        this.name = name;
        this.myYear = myYear;
        this.myMonth = myMonth;
        this.myDay = myDay;
        this.id = id;
        this.pol = pol;
        this.userDeti = userDeti;
        this.userPolZnackom = userPolZnackom;
        this.semPolo = semPolo;
        this.celZnackom = celZnackom;
        this.vozrostOt = vozrostOt;
        this.vozrostDo = vozrostDo;
        this.mikroVozrast = mikroVozrast;

        this.avatarUserUrl = avatarUserUrl;
    }

    public long getMikroVozrast() {
        return mikroVozrast;
    }

    public void setMikroVozrast(long mikroVozrast) {
        this.mikroVozrast = mikroVozrast;
    }

    public String getUserDeti() {
        return userDeti;
    }

    public void setUserDeti(String userDeti) {
        this.userDeti = userDeti;
    }

    public String getUserPolZnackom() {
        return userPolZnackom;
    }

    public void setUserPolZnackom(String userPolZnackom) {
        this.userPolZnackom = userPolZnackom;
    }

    public String getSemPolo() {
        return semPolo;
    }

    public void setSemPolo(String semPolo) {
        this.semPolo = semPolo;
    }

    public String getCelZnackom() {
        return celZnackom;
    }

    public void setCelZnackom(String celZnackom) {
        this.celZnackom = celZnackom;
    }


    public int getVozrostOt() {
        return vozrostOt;
    }

    public void setVozrostOt(int vozrostOt) {
        this.vozrostOt = vozrostOt;
    }

    public int getVozrostDo() {
        return vozrostDo;
    }

    public void setVozrostDo(int vozrostDo) {
        this.vozrostDo = vozrostDo;
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
