package com.example.katguz.android.chembase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sda on 05.07.17.
 */

public class Property {

    @SerializedName("CID")
    @Expose
    private String cID;
    @SerializedName("MolecularFormula")
    @Expose
    private String molecularFormula;
    @SerializedName("MolecularWeight")
    @Expose
    private Double molecularWeight;
    @SerializedName("CanonicalSMILES")
    @Expose
    private String canonicalSMILES;
    @SerializedName("InChI")
    @Expose
    private String inChI;
    @SerializedName("IUPACName")
    @Expose
    private String iUPACName;


    public String getCID() {
        return cID;
    }

    public void setCID(String cID) {
        this.cID = cID;
    }

    public String getMolecularFormula() {
        return molecularFormula;
    }

    public void setMolecularFormula(String molecularFormula) {
        this.molecularFormula = molecularFormula;
    }

    public Double getMolecularWeight() {
        return molecularWeight;
    }

    public void setMolecularWeight(Double molecularWeight) {
        this.molecularWeight = molecularWeight;
    }

    public String getCanonicalSMILES() {
        return canonicalSMILES;
    }

    public void setCanonicalSMILES(String canonicalSMILES) {
        this.canonicalSMILES = canonicalSMILES;
    }

    public String getInChI() {
        return inChI;
    }

    public void setInChI(String inChI) {
        this.inChI = inChI;
    }

    public String getIUPACName() {
        return iUPACName;
    }

    public void setIUPACName(String iUPACName) {
        this.iUPACName = iUPACName;
    }
}

