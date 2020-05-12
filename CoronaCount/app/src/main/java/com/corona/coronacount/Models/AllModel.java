package com.corona.coronacount.Models;

import com.google.gson.annotations.SerializedName;

public class AllModel {
    @SerializedName("updated")
    private long updated;

    @SerializedName("cases")
    private long cases;

    @SerializedName("todayCases")
    private long todayCases;

    @SerializedName("deaths")
    private long deaths;

    @SerializedName("todayDeaths")
    private long todayDeaths;

    @SerializedName("recovered")
    private long recovered;

    @SerializedName("active")
    private long active;

    @SerializedName("critical")
    private long critical;

    @SerializedName("casesPerOneMillion")
    private Double casesPerOneMillion;

    @SerializedName("deathsPerOneMillion")
    private Double deathsPerOneMillion;

    @SerializedName("tests")
    private  long tests;

    @SerializedName("testsPerOneMillion")
    private Double testsPerOneMillion;

    @SerializedName("affectedCountries")
    private long affectedCountries;


    public long getUpdated() {
        return updated;
    }

    public long getCases() {
        return cases;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public long getDeaths() {
        return deaths;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getActive() {
        return active;
    }

    public long getCritical() {
        return critical;
    }

    public Double getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public Double getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public long getTests() {
        return tests;
    }

    public Double getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public long getAffectedCountries() {
        return affectedCountries;
    }
}
