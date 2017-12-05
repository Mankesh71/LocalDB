package com.test.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maik on 4/10/2016.
 */

@SuppressWarnings("DefaultFileTemplate")
public class TestModel implements Parcelable {

    private String id = "", strFullName = "", strEmailId = "", strMobileNumber = "", strPassword = "";

    public TestModel() {}

    public TestModel(String id, String strFullName, String strEmailId, String strMobileNumber, String strPassword) {
        this.id = id;
        this.strFullName = strFullName;
        this.strEmailId = strEmailId;
        this.strMobileNumber = strMobileNumber;
        this.strPassword = strPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrFullName() {
        return strFullName;
    }

    public void setStrFullName(String strFullName) {
        this.strFullName = strFullName;
    }

    public String getStrEmailId() {
        return strEmailId;
    }

    public void setStrEmailId(String strEmailId) {
        this.strEmailId = strEmailId;
    }

    public String getStrMobileNumber() {
        return strMobileNumber;
    }

    public void setStrMobileNumber(String strMobileNumber) {
        this.strMobileNumber = strMobileNumber;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.strFullName);
        dest.writeString(this.strEmailId);
        dest.writeString(this.strMobileNumber);
        dest.writeString(this.strPassword);
    }

    private TestModel(Parcel in) {
        this.id = in.readString();
        this.strFullName = in.readString();
        this.strEmailId = in.readString();
        this.strMobileNumber = in.readString();
        this.strPassword = in.readString();
    }

    public static final Parcelable.Creator<TestModel> CREATOR = new Parcelable.Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel source) {
            return new TestModel(source);
        }

        @Override
        public TestModel[] newArray(int size) {
            return new TestModel[size];
        }
    };
}
