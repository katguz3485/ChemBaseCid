package com.example.katguz.android.chembase.ui.chemicals;


import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katguz.android.chembase.R;
import com.example.katguz.android.chembase.model.Property;
import com.example.katguz.android.chembase.model.PropertyTable;
import com.example.katguz.android.chembase.model.events.HideProgress;
import com.example.katguz.android.chembase.model.events.ShowProgress;
import com.example.katguz.android.chembase.network.ApiClient;
import com.example.katguz.android.chembase.network.PicassoHelper;
import com.example.katguz.android.chembase.utils.PrefsManager;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ChemicalsAdapter extends RecyclerView.Adapter<ChemicalsAdapter.ChemicalsHolder> {

    private List<Property> properties = new ArrayList<>();
    PropertyTable propertyTable = new PropertyTable();
    private PrefsManager prefsManager;
    private PicassoHelper helper;
    private ApiClient apiClient;


    @Inject
    Context context;

    @Inject
    ChemicalsPresenter presenter = new ChemicalsPresenter(apiClient);

    @Inject
    public ChemicalsAdapter(Context context) {
        this.context = context;
        prefsManager = new PrefsManager(this.context);
    }


    public void setData(List<Property> data) {
        propertyTable.getProperties();
        properties.clear();
        properties.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public ChemicalsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Timber.e("onCreateViewHolder");
        return new ChemicalsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));

    }

    @Override
    public void onBindViewHolder(ChemicalsHolder holder, int position) {
        Timber.e("onBindViewHolder");
        holder.setChemical(properties.get(position));

    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class ChemicalsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chemicalFormula)
        ImageView chemicalFormula;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.cidNumber)
        TextView cidNumber;

        @BindView(R.id.molecularWeight)
        TextView molcular_weight;


        public ChemicalsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setChemical(Property property) {
            helper = new PicassoHelper(prefsManager, context);
            Timber.e("setChemical");
            name.setText(property.getIUPACName());
            cidNumber.setText(property.getCID());
            molcular_weight.setText(property.getMolecularFormula());
            setPicassoPicture();

        }


        public void setPicassoPicture() {
            helper.setUrlPath();
            EventBus.getDefault().post(new ShowProgress());
            Picasso.with(context)
                    .load(helper.getUrl())
                    .into(chemicalFormula, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            EventBus.getDefault().post(new HideProgress());
                        }

                        @Override
                        public void onError() {
                            presenter.view.showErrorMessage();

                        }
                    });

        }


    }


}