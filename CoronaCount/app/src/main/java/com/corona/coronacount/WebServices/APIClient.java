package com.corona.coronacount.WebServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL="https://corona.lmao.ninja/v2/";
    private static Retrofit retrofit = null;

    private  static Gson gson=new GsonBuilder().create();

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build();


    public  static <T> T createService(Class<T> serviceClass){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit.create(serviceClass);
    }

}
