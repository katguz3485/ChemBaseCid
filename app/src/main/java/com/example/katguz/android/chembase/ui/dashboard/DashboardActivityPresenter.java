package com.example.katguz.android.chembase.ui.dashboard;

import com.example.katguz.android.chembase.network.ApiClient;
import com.example.katguz.android.chembase.utils.PrefsManager;

import javax.inject.Inject;

/**
 * Created by Kasia on 2017-09-25.
 */

class DashboardActivityPresenter {

    private DashboardActivityMvpView view;
    private ApiClient apiClient;
    private PrefsManager prefsManager;

    @Inject
    public DashboardActivityPresenter(ApiClient apiClient, PrefsManager prefsManager) {
        this.apiClient = apiClient;
        this.prefsManager = prefsManager;
    }

    public void attachView(DashboardActivityMvpView view) {
        this.view = view;
    }


    public void detachView() {
        this.view = null;
    }


    public void setUpEditText(String cidNumber) {
        if (cidNumber.isEmpty() || (!cidNumber.matches("-?\\d+(\\.\\d+)?"))) {
            view.showErrorMessage();
        } else if (!cidNumber.equals(prefsManager.getUserCidValueQuery()) || cidNumber.equals(null)) {
            prefsManager.saveUserQuery(cidNumber);
            // view.saveNumber(cidNumber);
        }
    }
}
