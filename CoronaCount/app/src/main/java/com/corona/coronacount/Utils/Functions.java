package com.corona.coronacount.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.corona.coronacount.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {

    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment )
                .commit();
    }

    public static  void changeMainFragmentWithBack(FragmentActivity fragmentActivity ,Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container,fragment)
                .commit();

    }

    public static String timeStampToDate(long timestamp){
        Date date = new Date(timestamp);
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
String dateString = f.format(date);
        return dateString;
    }






    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected());
    }

}
