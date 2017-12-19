package com.example.katguz.android.chembase;

import android.app.Application;

import com.example.katguz.android.chembase.di.AppComponent;
import com.example.katguz.android.chembase.di.AppModule;
import com.example.katguz.android.chembase.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kasiaguzik on 25.06.17.
 */

public class App extends Application {

    private AppComponent appComponent;

    private static App instance = new App();

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initDagger();
        Stetho.initializeWithDefaults(this);
        buildStetho();


    }


    public static App getInstance() {
        return instance;
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        Realm realm = Realm.getDefaultInstance();

    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    private void buildStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this).
                enableDumpapp(Stetho.defaultDumperPluginsProvider(this)).
                enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).
                build());
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
