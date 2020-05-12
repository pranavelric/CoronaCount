package com.corona.coronacount.Fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.corona.coronacount.Models.AllModel;
import com.corona.coronacount.R;
import com.corona.coronacount.Utils.Functions;
import com.corona.coronacount.WebServices.APIClient;
import com.corona.coronacount.WebServices.ApiInterface;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jpvs0101.currencyfy.Currencyfy.currencyfy;

public class EarthStatusFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.lastupdated)
    TextView lastUpdated;

    @BindView(R.id.total_count_text_view)
    TextView totalCount;
    @BindView(R.id.coronaCasesCount)
    TextView coronaCasesCount;
    @BindView(R.id.newCasesCount)
    TextView newCasesCount;
    @BindView(R.id.casesPerMillionCount)
    TextView casesPerMillionCount;
    @BindView(R.id.expandableView)
    ExpandableLayout expandableView;
    @BindView(R.id.arrowBtn)
    Button arrowBtn;


    @BindView(R.id.total_death_count_text_view)
    TextView deathCount;
    @BindView(R.id.deathCasesCount)
    TextView deathCasesCount;
    @BindView(R.id.newDeathsCount)
    TextView newdeathsCount;
    @BindView(R.id.deathsPerMillionCount)
    TextView deathsPerMillionCount;
    @BindView(R.id.expandableViewDeaths)
    ExpandableLayout expandableLayoutDeaths;
    //    ConstraintLayout expandableViewDeaths;
    @BindView(R.id.arrowBtndeath)
    Button arrowBtndeath;
    @BindView(R.id.cardViewdeaths)
    CardView cardViewdeaths;


    @BindView(R.id.recoveredCasesCount)
    TextView recoveryCount;
    @BindView(R.id.total_recovered_count_text_view)
    TextView totalRecoveredCount;
    @BindView(R.id.activeCount)
    TextView deathCountP;
    @BindView(R.id.criticalCount)
    TextView casesClosedCount;
    @BindView(R.id.arrowBtnrecovered)
    Button arrowBtnRecovered;
    @BindView(R.id.expandable_layout_recovered)
    ExpandableLayout expandableLayoutRecovered;

    @BindView(R.id.pieChart1)
    PieChart chart;




    @BindView(R.id.testCount)
    TextView testCount;

    @BindView(R.id.testPerMillionCount)
    TextView testPerMillionCount;

    @BindView(R.id.affectedCountries)
    TextView affectedCountries;

    @BindView(R.id.arrowBtnInfo)
    Button arrowBtnInfo;

    @BindView(R.id.expandable_layout_info)
    ExpandableLayout expandableLayoutInfo;

    @BindView(R.id.nointernet)
    LinearLayout layout;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_earth_status, container, false);
        unbinder = ButterKnife.bind(this, v);
        if(!Functions.checkInternetConnection(getActivity())){
            Toast.makeText(getActivity(),"No InterNet Connection", Toast.LENGTH_SHORT).show();
            layout.setVisibility(View.VISIBLE);

        }
        else {
            if (layout.getVisibility() == View.VISIBLE)
                layout.setVisibility(View.GONE);
            arrowBtn.setOnClickListener(this);
            arrowBtndeath.setOnClickListener(this);
            arrowBtnRecovered.setOnClickListener(this);
            arrowBtnInfo.setOnClickListener(this);

            getAllData();
            setChart();


            Thread timer = new Thread() {
                @Override
                public void run() {
if(getActivity()!=null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences sp = (container.getContext()).getSharedPreferences("earthstatussharedprefs", 0);

                            boolean isFirstStart = sp.getBoolean("keyearth", true);

                            if (isFirstStart) {
                                expandableView.expand();
                                SharedPreferences.Editor e = sp.edit();

                                e.putBoolean("keyearth", false);
                                e.commit();
                            }

                        }
                    });
                }
            };
            timer.start();


        }




        return v;
    }



    private void setChart() {
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(true);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.98f);
        chart.setClickable(true);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(40f);
        chart.setTransparentCircleRadius(45f);

        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        chart.animateX(10000);
        chart.animateY(10000);

        chart.getDescription().setText("");
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.setCenterTextColor(Color.BLACK);
        chart.setCenterText("COVID-19");
        chart.setDrawCenterText(true);
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTextSize(14f);

    }

    private void getAllData() {
        ApiInterface apiInterface = APIClient.createService(ApiInterface.class);
        Call<AllModel> call = apiInterface.getAll();
        call.enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                AllModel model;
                ArrayList<PieEntry> entries = new ArrayList<>();

                if (response.isSuccessful()) {
                    model = response.body();

                    String totalCases = currencyfy (model.getCases(), false, false);
                    String deaths = currencyfy(model.getDeaths(),false,false);
                    String totalRecovered = currencyfy(model.getRecovered(),false,false);
                    String newCase = currencyfy(model.getTodayCases(),false,false);
                    String casesPerMillion = currencyfy(model.getCasesPerOneMillion(),true,false);
                    String newDeaths = currencyfy(model.getTodayDeaths(),false,false);
                    String deathPerMillion = currencyfy(model.getDeathsPerOneMillion(),true,false);
                    String critical = String.valueOf(model.getCritical());
                    String active = String.valueOf(model.getActive());
                    String totalRecovery = currencyfy(model.getRecovered(),false,false) + " (" + (int) ((((double) (model.getRecovered())) / ((double) model.getRecovered() + (double) model.getDeaths())) * 100) + "%)";
                    String closedcases = currencyfy((model.getRecovered() + model.getDeaths()),false,false);
                    String deathpercen = currencyfy(model.getDeaths(),false,false) + " (" + (int) ((((double) model.getDeaths()) / ((double) model.getRecovered() + (double) model.getDeaths())) * 100) + "%)";

                    String tests= currencyfy(model.getTests(),false,false);
                    String testPerMillion = currencyfy(model.getTestsPerOneMillion(),true,false);
                    String countriesAffected = currencyfy(model.getAffectedCountries(),false,false);

                    String lastupdatedOn = Functions.timeStampToDate(model.getUpdated());

                    if(totalCount!=null)
                    totalCount.setText(totalCases);
                    if( coronaCasesCount!=null)
                    coronaCasesCount.setText(totalCases);
                    if(newCasesCount!=null)
                    newCasesCount.setText(newCase);
                    if( casesPerMillionCount!=null)
                    casesPerMillionCount.setText(casesPerMillion);
                    if(deathCount!=null)
                    deathCount.setText(deaths);
                    if( deathCasesCount!=null)
                    deathCasesCount.setText(deaths);
                    if(newdeathsCount!=null)
                    newdeathsCount.setText(newDeaths);
                    if(deathsPerMillionCount!=null)
                    deathsPerMillionCount.setText(deathPerMillion);

                    if(recoveryCount!=null)
                    recoveryCount.setText(totalRecovery);
                    if(totalRecoveredCount!=null)
                    totalRecoveredCount.setText(totalRecovered);
                    if( casesClosedCount!=null)
                        casesClosedCount.setText(closedcases);
                    if(deathCountP!=null)
                    deathCountP.setText(deathpercen);

                    if( testCount!=null)
                    testCount.setText(tests);
                    if( testPerMillionCount!=null)
                    testPerMillionCount.setText(testPerMillion);
                    if(affectedCountries!=null)
                    affectedCountries.setText(countriesAffected);

                    entries.add(new PieEntry(model.getCases(), "Existing"));
                    entries.add(new PieEntry(model.getDeaths(), "Deaths"));
                    entries.add(new PieEntry(model.getRecovered(), "Recovered"));

                    PieDataSet dataSet = new PieDataSet(entries, "");
                    dataSet.setDrawIcons(false);
                    dataSet.setSliceSpace(3f);
                    dataSet.setIconsOffset(new MPPointF(0, 40));
                    dataSet.setSelectionShift(15f);

                    dataSet.setColors(Color.parseColor("#F18527"), Color.parseColor("#E60F0F"), Color.parseColor("#35DA27"));
                    PieData data = new PieData(dataSet);
                    data.setValueFormatter(new PercentFormatter(chart));
                    data.setValueTextSize(11f);
                    data.setValueTextColor(Color.WHITE);
                   if(chart!=null) {
                       chart.setData(data);
                       chart.invalidate();
                   }
if(lastUpdated!=null)
                    lastUpdated.append( lastupdatedOn );

                } else {

                    Toast.makeText(getActivity(), "Some error occured please try again after some time or Check your internet connection", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<AllModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Some error occured please try again after some time or\n Check your internet connection", Toast.LENGTH_LONG).show();

            }
        });

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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.arrowBtn) {

            expandableView.toggle();
            if (expandableView.isExpanded()) {
                arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }
        if (v.getId() == R.id.arrowBtndeath) {

            expandableLayoutDeaths.toggle();
            if (expandableLayoutDeaths.isExpanded()) {
                arrowBtndeath.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowBtndeath.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }
        if (v.getId() == R.id.arrowBtnrecovered) {
            expandableLayoutRecovered.toggle();
            if (expandableLayoutRecovered.isExpanded()) {
                arrowBtnRecovered.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowBtnRecovered.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }
if(v.getId()==R.id.arrowBtnInfo){
    expandableLayoutInfo.toggle();
    if (expandableLayoutInfo.isExpanded()) {
        arrowBtnInfo.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
    } else {
        arrowBtnInfo.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
    }
}
    }

}

