package com.example.loginandsqldemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;


public class SearchFragment extends Fragment {

    EditText search_name;
    ImageView search_image;
    String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        search_name=view.findViewById(R.id.search_name);
        search_image=view.findViewById(R.id.search_image);
        search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String select_medicine=search_name.getText().toString();//获取文本框的值
                WebView webView=view.findViewById(R.id.webview);
                url="https://www.baidu.com";
                webView.loadUrl(url);
                webView.getSettings().setUseWideViewPort(true);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setJavaScriptEnabled(true);

               webView.setWebViewClient(new WebViewClient(){
                   @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(url);
                        return  true;
                    }
                });
            }
        });
        return view;
    }




}
