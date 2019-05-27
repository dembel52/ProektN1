package com.example.proektn;

public class Users {
    private String name;
    private String voz;
    private String id;
    private String pol;
    private int avatarMockUpResorse;

    public Users() {
    }

    public Users(String name, String voz, String id, String pol, int avatarMockUpResorse) {
        this.name = name;
        this.voz = voz;
        this.id = id;
        this.pol = pol;
        this.avatarMockUpResorse = avatarMockUpResorse;
    }

    public int getAvatarMockUpResorse() {
        return avatarMockUpResorse;
    }

    public void setAvatarMockUpResorse(int avatarMockUpResorse) {
        this.avatarMockUpResorse = avatarMockUpResorse;
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

    public String getVoz() {
        return voz;
    }

    public void setVoz(String voz) {
        this.voz = voz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
