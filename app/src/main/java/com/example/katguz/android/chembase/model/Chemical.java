package com.example.katguz.android.chembase.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chemical {

    @SerializedName("PropertyTable")
@Expose
private PropertyTable propertyTable;

    public PropertyTable getPropertyTable() {
        return propertyTable;
    }

    public void setPropertyTable(PropertyTable propertyTable) {
        this.propertyTable = propertyTable;
    }


    public String toString(String iupacName) {
        return iupacName;
    }
}
