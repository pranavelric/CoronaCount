package com.corona.coronacount.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.corona.coronacount.Activities.GoogleMapss;
import com.corona.coronacount.Adaptors.WorldCasesAdapter;
import com.corona.coronacount.Models.AllCountries;
import com.corona.coronacount.R;
import com.corona.coronacount.Utils.Functions;
import com.corona.coronacount.WebServices.APIClient;
import com.corona.coronacount.WebServices.ApiInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldCasesFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.searchView)
    AppCompatEditText searchEditText;

    @BindView(R.id.img_map)
    ImageView imgMap;

    @BindView(R.id.nointernet)
    LinearLayout layout;
    Unbinder unbinder;
    List<AllCountries> countriesList;
    WorldCasesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_world_cases, container, false);
        unbinder = ButterKnife.bind(this, v);
        if(!Functions.checkInternetConnection(getActivity())){
            Toast.makeText(getActivity(),"No InterNet Connection", Toast.LENGTH_SHORT).show();
            layout.setVisibility(View.VISIBLE);

        }
        else {
            if (layout.getVisibility() == View.VISIBLE)
                layout.setVisibility(View.GONE);

            getCountriesData();
        }
        return v;
    }

    private void getCountriesData() {
        ApiInterface apiInterface = APIClient.createService(ApiInterface.class);
        Call<List<AllCountries>> call = apiInterface.getCountries();
        call.enqueue(new Callback<List<AllCountries>>() {
            @Override
            public void onResponse(Call<List<AllCountries>> call, Response<List<AllCountries>> response) {
                if (response.isSuccessful()) {
                    countriesList = response.body();
                    setAdapter(countriesList);

                } else {
                    Toast.makeText(getActivity(), "Some error occured please try again after some time ,\n Check your internet connection", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<AllCountries>> call, Throwable t) {
                Toast.makeText(getActivity(), "Some error occured please try again after some time \n Check your Internet connection" , Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setAdapter(List<AllCountries> countriesList) {
        if (countriesList.size() == 0) {
            Toast.makeText(getActivity(), "No Data found", Toast.LENGTH_LONG).show();
        } else {

            Collections.sort(countriesList, new Comparator<AllCountries>() {

                @Override
                public int compare(AllCountries o1, AllCountries o2) {
                    return o1.getCases() > o2.getCases() ? -1 : (o1.getCases() < o2.getCases()) ? 1 : 0;
                }
            });

            adapter = new WorldCasesAdapter(getActivity(), countriesList);
            if(recyclerView!=null){
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);}
            if(adapter!=null)
            adapter.notifyDataSetChanged();
            if(searchEditText!=null)
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());
                }
            });
if(imgMap!=null)
            imgMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GoogleMapss.class);
                    intent.putExtra("list", (Serializable) countriesList);
                    intent.putExtra("type", "Affected");
                    startActivity(intent);

                }
            });

        }
    }


    private void filter(String charString) {
        ArrayList<AllCountries> filteredList = new ArrayList<>();
        for (AllCountries row : countriesList) {
            if (row.getCountryName().toLowerCase().contains(charString.toLowerCase()) || row.getContinent().toLowerCase().contains(charString.toLowerCase())) {
                filteredList.add(row);
            }
        }
        adapter.filterList(filteredList);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
