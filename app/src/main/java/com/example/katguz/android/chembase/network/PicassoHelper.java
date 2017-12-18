package com.example.katguz.android.chembase.network;

import android.content.Context;
import android.net.Uri;

import com.example.katguz.android.chembase.model.Property;
import com.example.katguz.android.chembase.model.events.HideProgress;
import com.example.katguz.android.chembase.model.events.ShowProgress;
import com.example.katguz.android.chembase.utils.PrefsManager;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import static com.example.katguz.android.chembase.network.ApiClient.BASE_URL;

/**
 * Created by Kasia on 12/18/2017.
 */

public class PicassoHelper implements PicassoService {

    private String url;
    private Property property;
    private String urlStringParametrized;
    PrefsManager prefsManager;
    Context context;

    @Inject
    public PicassoHelper(PrefsManager prefsManager, Context context) {
        this.prefsManager = prefsManager;
        this.context = context;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public void setUrlPath() {
        prefsManager= new PrefsManager(context);
        property = new Property();

        urlStringParametrized = BASE_URL + "compound/cid/" + Integer.valueOf(prefsManager.getUserCidValueQuery()) + "/PNG";

        url = Uri.parse(urlStringParametrized)
                .buildUpon()
                .build()
                .toString();
    }





}
