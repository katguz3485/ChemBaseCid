package com.example.katguz.android.chembase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sda on 05.07.17.
 */

public class PropertyTable {

    @SerializedName("Properties")
    @Expose
    private List<Property> properties;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}

