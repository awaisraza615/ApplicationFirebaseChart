package com.example.applicationfirebasechart;

public class InsertData
{
    public String mID,mName,mAddress,mAge;

    public InsertData(String mID, String mName, String mAddress, String mAge) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mAge = mAge;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public InsertData() {
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }
}
