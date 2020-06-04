package com.example.loginandsqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.x;

public class navigation extends AppCompatActivity {
private Fragment MineFragment;
    private Fragment MedicineFragment;
    private Fragment SearchFragment;
    private  Fragment PictureFragment;
private TextView txt_medicine,txt_search,txt_tubiao,txt_mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用
        this.setTitle("药物管理");
        //获取Fragment
        MineFragment=new MineFragment();
        MedicineFragment=new MedicineFragment();
        SearchFragment =new SearchFragment();
        PictureFragment=new PictureFragment();
        txt_mine=findViewById(R.id.txt_mine);
        //跳转我的
        txt_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,MineFragment).commit();
                txt_medicine.setSelected(false);
                txt_search.setSelected(false);
                txt_tubiao.setSelected(false);
                txt_mine.setSelected(true);
            }
        });
        //跳转药物查询界面
        txt_medicine=findViewById(R.id.txt_medicine);
        txt_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,MedicineFragment).commit();
                txt_mine.setSelected(false);
                txt_search.setSelected(false);
                txt_tubiao.setSelected(false);
                txt_medicine.setSelected(true);
            }
        });
        //搜索
        txt_search=findViewById(R.id.txt_search);
        txt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,SearchFragment).commit();
                txt_mine.setSelected(false);
                txt_medicine.setSelected(false);
                txt_tubiao.setSelected(false);
                txt_search.setSelected(true);
            }
        });
        //图片
        txt_tubiao=findViewById(R.id.txt_tubiao);
        txt_tubiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,PictureFragment).commit();
                txt_mine.setSelected(false);
                txt_medicine.setSelected(false);
                txt_search.setSelected(false);
                txt_tubiao.setSelected(true);
            }
        });
    }
}
