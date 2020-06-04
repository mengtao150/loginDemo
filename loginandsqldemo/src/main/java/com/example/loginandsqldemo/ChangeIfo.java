package com.example.loginandsqldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeIfo extends AppCompatActivity {
Button cancel_bt,sure_bt;
EditText edit_name,edit_sex,edit_phone,edit_worknumber;
MineFragment mineFragment;
Bundle bundle;

    //宿主activity中的getTitles()方法
    public String getTitles(){
        String work=edit_worknumber.getText().toString();
        return work;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ifo);
        init();

        //取消按钮
        cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //修改按钮
        sure_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交按钮进行回传值操作

            mineFragment=new MineFragment();
                bundle=new Bundle();
                //获取编辑框里面的值，并放在intent里面进行回传做准备
                String name=edit_name.getText().toString();
                String sex=edit_sex.getText().toString();
                String phone =edit_phone.getText().toString();
                String work=edit_worknumber.getText().toString();
                bundle.putString("namekey",name);
                bundle.putString("sexkey",sex);
                bundle.putString("phonekey",phone);
                bundle.putString("workkey",work);
                mineFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,mineFragment).commit();
                Toast.makeText(ChangeIfo.this,"恭喜修改成功！",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void init() {
        cancel_bt=findViewById(R.id.cancel_bt);
        sure_bt=findViewById(R.id.sure_bt);
        edit_name=findViewById(R.id.edit_name);
        edit_sex=findViewById(R.id.edit_sex);
        edit_phone=findViewById(R.id.edit_phone);
        edit_worknumber=findViewById(R.id.edit_worknumber);

    }

}
