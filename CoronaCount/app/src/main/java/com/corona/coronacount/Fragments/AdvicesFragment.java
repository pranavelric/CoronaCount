package com.corona.coronacount.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.corona.coronacount.R;
import com.corona.coronacount.Utils.Functions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdvicesFragment extends Fragment {
    @BindView(R.id.webViewadvices)
    WebView webViewAdvices;

    @BindView(R.id.nointernet)
    LinearLayout layout;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_advices, container, false);
        unbinder = ButterKnife.bind(this, v);
        if(!Functions.checkInternetConnection(getActivity())){
            Toast.makeText(getActivity(),"No InterNet Connection", Toast.LENGTH_SHORT).show();
        layout.setVisibility(View.VISIBLE);

        }
        else {
            if(layout.getVisibility()==View.VISIBLE)
            layout.setVisibility(View.GONE);

            webViewAdvices.loadUrl("file:///android_asset/advice.html");
            webViewAdvices.getSettings().setJavaScriptEnabled(true);
            webViewAdvices.setVerticalScrollBarEnabled(true);
            webViewAdvices.getSettings().setDomStorageEnabled(true);
            webViewAdvices.setWebChromeClient(new WebChromeClient());
            webViewAdvices.setWebViewClient(new WebViewClient());
        }
        return v;
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
