package com.example.katguz.android.chembase.network;

import com.example.katguz.android.chembase.model.Chemical;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Oleg on 2017-07-03.
 */

public interface ApiService {

   /* @GET("Clients/Get")
    Call<List<Client>> getClients();*/


 /*  @GET("users/{username}")

   Call<User> getUser(@Path("username") String username);
*/



 /*   //https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/1,2,3,4,5/property/MolecularFormula,MolecularWeight,CanonicalSMILES/CSV
    @GET("compound/cid/{id}/property/IUPACName,CanonicalSMILES,MolecularFormula,MolecularWeight,InChI")
    Call<Chemical> getChemicalDatda(@Path("id") Integer id);*/

 /*   @GET("compound/cid/{cidValue}/PNG")
    Call<ResponseBody> getImagePng(@Path("cidValue") Integer cidValue);
*/
    @GET("compound/cid/{cidValue}/property/IUPACName,CanonicalSMILES,MolecularFormula,MolecularWeight,InChI,InChIKey/JSON")
    Call<Chemical> getChemicalDatda(@Path("cidValue")Integer cidValue);

 /*  @GET("compound/cid/{id}/property/IUPACName,CanonicalSMILES,MolecularFormula,MolecularWeight,InChI")
    Call<Chemical> getChemicalDatda(@Path("id") Integer id);*/

  /*  @GET("compound/cid/{cidValue}/PNG")
    Call<ResponseBody> getImagePng(@Path("cidValue") Integer cidValue);*/

}
