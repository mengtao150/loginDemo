package com.example.loginandsqldemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.image.ImageOptions;
import org.xutils.x;


public class MineFragment extends Fragment {
    Button exit,modify;
    TextView nametxt,sextxt,phonetxt,worknumbertxt;
    ImageButton about_image_bt;
    EditText edit_name,edit_sex,edit_phone,edit_worknumber;
    ImageView pictureImage,add_image;
    int count=0;//计数，用于切换更改图片
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //获取控件
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        exit= view.findViewById(R.id.exit);
        modify=view.findViewById(R.id.modify);
        about_image_bt=view.findViewById(R.id.about_image_bt);
        nametxt=view.findViewById(R.id.nametxt);
        sextxt=view.findViewById(R.id.sextxt);
        phonetxt=view.findViewById(R.id.phonetxt);
        // EditText edit_name,edit_sex,edit_phone,edit_worknumber;
        edit_name=view.findViewById(R.id.edit_name);
        edit_sex=view.findViewById(R.id.edit_sex);
        edit_phone=view.findViewById(R.id.edit_phone);
        edit_worknumber=view.findViewById(R.id.edit_worknumber);
        pictureImage=view.findViewById(R.id.image);
        add_image=view.findViewById(R.id.add_image);
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog alertDialog=new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("取消选择");
                alertDialog.setMessage("你是否选择取消");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "更改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageOptions imageOptions=new ImageOptions.Builder().setRadius(20).setFadeIn(true).build();
                        x.image().bind(pictureImage,"https://ku.90sjimg.com/element_origin_min_pic/16/06/07/1857569e41d7225.jpg",imageOptions);
                        Toast.makeText(getActivity(), "图片更改成功!", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDialog.show();


            }
        });

        //退出登录
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("退出");
                alertDialog.setMessage("你是否要退出");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消退出", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "退出成功!", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                });
                alertDialog.show();
            }
        });
        //修改信息
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               //跳转修改信息界面
                Intent intent = new Intent(getActivity(), ChangeIfo.class);
               startActivity(intent);






            }
        });



        //关于按钮，显示D
        about_image_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("APP开发介绍");
                alertDialog.setMessage("该产品是以中药查询系统为主题的一款APP--开发人：18计一孟涛");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "感谢你的支持！", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "感谢你的支持!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();

            }
        });
        return view;
    }






}
