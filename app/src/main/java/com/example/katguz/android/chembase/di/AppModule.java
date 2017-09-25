package com.example.katguz.android.chembase.di;

import android.content.Context;

import com.example.katguz.android.chembase.network.ApiClient;
import com.example.katguz.android.chembase.utils.PrefsManager;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by kasiaguzik on 02.07.17.
 */
@dagger.Module
public class AppModule {


    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    PrefsManager providesPrefsManager(Context context) {
        return new PrefsManager(context);
    }

    @Provides
    @Singleton
    ApiClient providesApiClient(PrefsManager prefsManager) {

        return new ApiClient(prefsManager);
    }


   /* @Provides
    @Singleton
    ChemicalsPresenter providesPresenter(PrefsManager prefsManager) {

        return new ChemicalsPresenter(providesApiClient(prefsManager));
    }*/
}



