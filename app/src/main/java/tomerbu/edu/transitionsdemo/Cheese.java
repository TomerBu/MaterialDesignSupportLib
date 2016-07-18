package tomerbu.edu.transitionsdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tomerbuzaglo on 15/07/2016.
 * Copyright 2016 tomerbuzaglo. All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class Cheese implements Parcelable {
    private String description;
    private int imageResID;


    public Cheese(String description, int imageResID) {
        this.description = description;
        this.imageResID = imageResID;
    }

    protected Cheese(Parcel in) {
        this.description = in.readString();
        this.imageResID = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeInt(this.imageResID);
    }

    public static final Parcelable.Creator<Cheese> CREATOR = new Parcelable.Creator<Cheese>() {
        @Override
        public Cheese createFromParcel(Parcel source) {
            return new Cheese(source);
        }

        @Override
        public Cheese[] newArray(int size) {
            return new Cheese[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }


}
