package com.example.loginandsqldemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Mine extends AppCompatActivity {
Button exit,modify;
TextView nametxt,sextxt,phonetxt,worknumbertxt;
ImageButton about_image_bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        init();
        //退出登录
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Mine.this).create();
                alertDialog.setTitle("退出");
                alertDialog.setMessage("你是否要退出");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Mine.this, "取消退出", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Mine.this, "退出成功!", Toast.LENGTH_SHORT).show();
                        finish();
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
                Intent intent = new Intent(Mine.this, ChangeIfo.class);
                startActivityForResult(intent, 1);

            }
        });
        //关于按钮，显示D
        about_image_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Mine.this).create();
                alertDialog.setTitle("APP开发介绍");
                alertDialog.setMessage("该产品是以中药查询系统为主题的一款APP--开发人：18计一孟涛");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Mine.this, "感谢你的支持！", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Mine.this, "感谢你的支持!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&requestCode==1){

            worknumbertxt=findViewById(R.id.worknumbertxt);
            nametxt.setText(data.getStringExtra("namekey"));
            sextxt.setText(data.getStringExtra("sexkey"));
            phonetxt.setText(data.getStringExtra("phonekey"));
            worknumbertxt.setText(data.getStringExtra("workkey"));
        }
    }

    private void init() {
        exit=findViewById(R.id.exit);
        modify=findViewById(R.id.modify);
        about_image_bt=findViewById(R.id.about_image_bt);
        nametxt=findViewById(R.id.nametxt);
        sextxt=findViewById(R.id.sextxt);
        phonetxt=findViewById(R.id.phonetxt);

    }
}
