package com.corona.coronacount.Activities;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.corona.coronacount.Fragments.AdvicesFragment;
import com.corona.coronacount.Fragments.EarthStatusFragment;
import com.corona.coronacount.Fragments.FaqFragment;
import com.corona.coronacount.Fragments.SymtomsFragment;
import com.corona.coronacount.Fragments.WorldCasesFragment;
import com.corona.coronacount.R;
import com.corona.coronacount.Utils.Functions;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] permissionArrays = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET};
requestPermissions(permissionArrays,0);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_earth_status, R.id.nav_countrywise_status, R.id.nav_advices, R.id.nav_symptoms)
                .setDrawerLayout(drawer)
                .build();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        Functions.changeMainFragment(MainActivity.this, new EarthStatusFragment());

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("sharedprefs", 0);

                boolean isFirstStart = sp.getBoolean("key", true);



                if(isFirstStart) {
                    drawer.openDrawer(navigationView);
                    SharedPreferences.Editor e = sp.edit();

                    e.putBoolean("key", false);
                    e.commit();
                }
            }
        });

        t.start();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_earth_status:
                Functions.changeMainFragment(MainActivity.this, new EarthStatusFragment());
                break;
            case R.id.nav_countrywise_status:
                Functions.changeMainFragment(MainActivity.this, new WorldCasesFragment());
                break;
            case R.id.nav_advices:
                Functions.changeMainFragment(MainActivity.this, new AdvicesFragment());
                break;
            case R.id.nav_symptoms:
                Functions.changeMainFragment(MainActivity.this, new SymtomsFragment());
                break;

            case R.id.nav_faq:
                Functions.changeMainFragment(MainActivity.this, new FaqFragment());
                break;

            default:
                Functions.changeMainFragment(MainActivity.this, new EarthStatusFragment());
                break;
        }
        return false;
    }
}
