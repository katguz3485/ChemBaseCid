package com.example.katguz.android.chembase.ui.chemicals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.katguz.android.chembase.App;
import com.example.katguz.android.chembase.R;
import com.example.katguz.android.chembase.ui.dashboard.DashboardActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChemicalsFragment extends Fragment implements ChemicalsMvpView {

    public static final String TAG = ChemicalsFragment.class.getClass().getName();

    public ChemicalsFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(TAG, id);
        ChemicalsFragment fragment = new ChemicalsFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Inject
    ChemicalsPresenter presenter;


    @BindView(R.id.chemicals_recycler)
    RecyclerView recyclerView;


    private ChemicalsAdapter adapter;


    @Override
    public void onStart() {
        super.onStart();
        presenter.starDownloadData();
        // presenter.getImage();

    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chemicals, container, false);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this, view);
        presenter.attach(this);
        ((DashboardActivity) getActivity()).getSupportActionBar().setTitle(R.string.chemicals_title);
        adapter = new ChemicalsAdapter(getContext());
        setChemicalAdapter(adapter);
        presenter.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }


    @Override
    public void setChemicalAdapter(ChemicalsAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage() {

        Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();

    }


}
