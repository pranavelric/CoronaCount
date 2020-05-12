package com.corona.coronacount.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.corona.coronacount.Models.AllCountries;
import com.corona.coronacount.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jpvs0101.currencyfy.Currencyfy.currencyfy;

public class WorldCasesAdapter extends RecyclerView.Adapter<WorldCasesAdapter.ViewHolder> {
    private Unbinder unbinder;
    private List<AllCountries> countriesList, countriesListfiltered;
    private Context context;
    public WorldCasesAdapter(Context context,List<AllCountries> countriesList) {
        this.countriesList = countriesList;
        this.countriesListfiltered = countriesList;
    this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.worldcase_row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllCountries allCountries = countriesListfiltered.get(position);

        holder.countryName.setText(allCountries.getCountryName());
        Glide
                .with(context.getApplicationContext())
                .load(allCountries.getCountryInfo().getFlag())
                .centerCrop()
                .into(holder.circleImageCountry);
        holder.countryContinent.setText(allCountries.getContinent());
        holder.coronaCasesCountInCountry.setText(currencyfy(allCountries.getCases(),false,false));
        holder.newCasesCountInCountry.setText(currencyfy(allCountries.getToadCases(),false,false));
        holder.deathCountInCountry.setText(currencyfy(allCountries.getDeaths(),false,false));




        holder.todaydeathCountInCountry.setText(currencyfy(allCountries.getTodayDeaths(),false,false));
        holder.recoveredCountInCountry.setText(currencyfy(allCountries.getRecovered(),false,false));
        holder.activeCountInCountry.setText(currencyfy(allCountries.getActive(),false,false));


    }

    @Override
    public int getItemCount() {
        return countriesListfiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.CountryName)
        TextView countryName;
        @BindView(R.id.country_continent_sub)
        TextView countryContinent;
        @BindView(R.id.circleImageCountry)
        ImageView circleImageCountry;

        @BindView(R.id.coronaCasesCountInCountry)
        TextView coronaCasesCountInCountry;
        @BindView(R.id.newCasesCountInCountry)
        TextView newCasesCountInCountry;

        @BindView(R.id.deathCountInCountry)
        TextView deathCountInCountry;
        @BindView(R.id.todaydeathCountInCountry)
        TextView todaydeathCountInCountry;

        @BindView(R.id.recoveredCountInCountry)
        TextView recoveredCountInCountry;

        @BindView(R.id.activeCountInCountry)
        TextView activeCountInCountry;

        @BindView(R.id.arrowBtnCountry)
        Button arrowBtnCountry;

        @BindView(R.id.expandableViewCountry)
        ExpandableLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder =  ButterKnife.bind(this,itemView);
            arrowBtnCountry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandableLayout.toggle();
                    if (expandableLayout.isExpanded()) {
                        arrowBtnCountry.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    } else {
                        arrowBtnCountry.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    }
                }
            });
        }


    }

public void filterList(ArrayList<AllCountries> filteredList){
        countriesListfiltered = filteredList;
        notifyDataSetChanged();
}

}
