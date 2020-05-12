package com.corona.coronacount.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AllCountries implements Serializable {
    @SerializedName("updated")
    private long updated;

    @SerializedName("country")
    private String countryName;

    @SerializedName("countryInfo")
    private CountryInfo countryInfo;


    @SerializedName("cases")
    private long cases;

    @SerializedName("todayCases")
    private long toadCases;

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
    private double casesPerOneMillion;

    @SerializedName("deathsPerOneMillion")
    private double deathsPerOneMillion;

    @SerializedName("tests")
    private long tests;

    @SerializedName("testsPerOneMillion")
    private double testsPerOneMillion;

    @SerializedName("continent")
    private String continent;

    public long getUpdated() {
        return updated;
    }

    public String getCountryName() {
        return countryName;
    }

    public long getCases() {
        return cases;
    }

    public long getToadCases() {
        return toadCases;
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

    public double getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public double getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public long getTests() {
        return tests;
    }

    public double getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public String getContinent() {
        return continent;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }




    public class CountryInfo implements Serializable{
        @SerializedName("_id")
        private int _id;

        @SerializedName("iso2")
        private String iso2;

        @SerializedName("iso3")
        private String iso3;

        @SerializedName("lat")
        private double lat;

        @SerializedName("long")
        private double longitude;

        @SerializedName("flag")
        private String flag;

        public int get_id() {
            return _id;
        }

        public String getIso2() {
            return iso2;
        }

        public String getIso3() {
            return iso3;
        }

        public double getLat() {
            return lat;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getFlag() {
            return flag;
        }
    }


}
