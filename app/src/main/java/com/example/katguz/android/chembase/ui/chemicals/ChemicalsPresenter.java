package com.example.katguz.android.chembase.ui.chemicals;

import android.content.Context;
import android.util.Log;

import com.example.katguz.android.chembase.model.Chemical;
import com.example.katguz.android.chembase.model.Property;
import com.example.katguz.android.chembase.model.events.HideProgress;
import com.example.katguz.android.chembase.model.events.ShowProgress;
import com.example.katguz.android.chembase.network.ApiClient;
import com.example.katguz.android.chembase.utils.PrefsManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class ChemicalsPresenter {


    private ApiClient apiClient;
    protected ChemicalsMvpView view;
    private ChemicalsAdapter adapter;
    private PrefsManager prefsManager;



    @Inject
    public Context context;

    public ChemicalsPresenter(ApiClient apiClient) {

    }


    public void setAdapter(ChemicalsAdapter adapter) {
        this.adapter = adapter;
    }

    @Inject
    public ChemicalsPresenter(ApiClient apiClient, PrefsManager prefsManager) {
        this.apiClient = apiClient;
        this.prefsManager = prefsManager;
    }


    public void attach(ChemicalsMvpView view) {
        this.view = view;
    }

    public void detach() {
        this.view = null;
    }


    public void starDownloadData() {
        EventBus.getDefault().post(new ShowProgress());
        Log.e("Prefs Manager", prefsManager.getUserCidValueQuery());

        apiClient.getService().getChemicalDatda(Integer.valueOf(prefsManager.getUserCidValueQuery())).enqueue(new Callback<Chemical>() {
            @Override
            public void onResponse(Call<Chemical> call, Response<Chemical> response) {
                if (response.isSuccessful()) {
                    List<Property> list = response.body().getPropertyTable().getProperties();
                    adapter.setData(list);
                    Timber.d("Response", response.body().getPropertyTable().getProperties().get(0).getCanonicalSMILES());

                } else {
                    view.showErrorMessage();
                    EventBus.getDefault().post(new HideProgress());
                }
                EventBus.getDefault().post(new HideProgress());

            }


            @Override
            public void onFailure(Call<Chemical> call, Throwable t) {
                EventBus.getDefault().post(new HideProgress());
                view.showErrorMessage();


            }
        });
    }


/*    public void getImage() {
        apiClient.getService().getImagePng(123).enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                  *//*  List<Property> list = response.body().getPropertyTable().getProperties();
                    adapter.setData(list);*//*

                    bm = BitmapFactory.decodeStream(response.body().byteStream());
                    // adapter.setBm(bm);
                    Timber.d("Bitmap");

                } else {
                    view.showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showErrorMessage();
            }
        });

    }*/
}
