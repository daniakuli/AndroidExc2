package com.example.androidexc2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    public String name;
    public String id;
    public String avatarUrl;
    public String phone;
    public String address;
    public Boolean cb;

    public Student(String name, String id, String avatarUrl, String phone, String address ,Boolean cb) {
        this.name = name;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.address = address;
        this.cb = cb;
    }

    protected Student(Parcel in) {
        name = in.readString();
        id = in.readString();
        avatarUrl = in.readString();
        phone = in.readString();
        address = in.readString();
        byte tmpCb = in.readByte();
        cb = tmpCb == 0 ? null : tmpCb == 1;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(avatarUrl);
        parcel.writeString(phone);
        parcel.writeString(address);
        parcel.writeByte((byte) (cb == null ? 0 : cb ? 1 : 2));
    }
}
