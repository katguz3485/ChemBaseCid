package com.example.katguz.android.chembase.ui.chemicals;


import android.content.Context;
import android.net.Uri;
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
import com.example.katguz.android.chembase.utils.PrefsManager;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.example.katguz.android.chembase.network.ApiClient.BASE_URL;

public class ChemicalsAdapter extends RecyclerView.Adapter<ChemicalsAdapter.ChemicalsHolder> {


    private List<Property> properties = new ArrayList<>();

    PropertyTable propertyTable = new PropertyTable();

    public static String url;


    private PrefsManager prefsManager;


    @Inject
    Context context;

    private ApiClient apiClient;


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

 /*   public void setBm() {
        presenter.getImage();
        notifyDataSetChanged();
    }
*/

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


            Timber.e("setChemical");
            name.setText(property.getIUPACName());
            cidNumber.setText(property.getCID());
            molcular_weight.setText(property.getMolecularFormula());
            // chemicalFormula.setImageBitmap(presenter.getImage());
            setPicassoPicture();


        }


        public void setUrlPath() {

            String urlStringParametrized = BASE_URL + "compound/cid/" + Integer.valueOf(prefsManager.getUserCidValueQuery()) + "/PNG";
            //String urlStr = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/{cidValue}/PNG";
            url = Uri.parse(urlStringParametrized)
                    .buildUpon()
                    .build()
                    .toString();
        }


        public void setPicassoPicture() {
            //public static final String BASE_URL = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/";
/*   @GET("compound/cid/{cidValue}/PNG")
    Call<ResponseBody> getImagePng(@Path("cidValue") Integer cidValue);
*/
            setUrlPath();
            EventBus.getDefault().post(new ShowProgress());

            Picasso.with(context)
                    .load(url)
                    .into(chemicalFormula, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            EventBus.getDefault().post(new HideProgress());
                        }

                        @Override
                        public void onError() {

                            //  presenter.view.showErrorMessage();

                        }
                    });

        }


    }


}