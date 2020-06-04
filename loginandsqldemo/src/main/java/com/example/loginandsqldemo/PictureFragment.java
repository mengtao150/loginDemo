package com.example.loginandsqldemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;


public class PictureFragment extends Fragment {

public WebView chartsshow_web;
public ImageView search_image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_picture, container, false);
        chartsshow_web=view.findViewById(R.id.chartsshow);
        search_image=view.findViewById(R.id.search_image);
        search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartsshow_web.getSettings().setAllowFileAccess(true);
                chartsshow_web.getSettings().setJavaScriptEnabled(true);
                chartsshow_web.loadUrl("file:///android_asset/echart.html");

            }
        });
        /**
         * js方法的调用必须在html页面加载完成之后才能调用。
         * 用webview加载html还是需要耗时间的，必须等待加载完，在执行代用js方法的代码。
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }

}
