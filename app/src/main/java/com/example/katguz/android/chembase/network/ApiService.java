package com.example.katguz.android.chembase.network;

import com.example.katguz.android.chembase.model.Chemical;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Oleg on 2017-07-03.
 */

public interface ApiService {

    @GET("compound/cid/{cidValue}/property/IUPACName,CanonicalSMILES,MolecularFormula,MolecularWeight,InChI,InChIKey/JSON")
    Call<Chemical> getChemicalDatda(@Path("cidValue")Integer cidValue);


}
