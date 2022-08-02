package com.example.aplikasiremote;

public class Remote {
    private int mId;
    private int mPower;
    private String mWaktu;

    public int getId() {
        return mId;
    }

    public Remote() {

    }

    public void setId(int id) {
        this.mId = id;
    }

    public Remote(int id) {
        mId = id;
    }

    public int getPower() {
        return mPower;
    }

    public void setPower(int power) {
        this.mPower = power;
    }

    public String getmWaktu() {
        return mWaktu;
    }

    public void setmWaktu(String mWaktu) {
        this.mWaktu = mWaktu;
    }
}
