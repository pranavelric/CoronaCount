package com.corona.coronacount.WebServices;

import com.corona.coronacount.Models.AllCountries;
import com.corona.coronacount.Models.AllModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<AllModel> getAll();

    @GET("countries")
    Call<List<AllCountries>> getCountries();
}
