package com.example.loginandsqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class register extends AppCompatActivity {
 private EditText username_register,password_register;
 private Button register_button_register,cancel_button_register;
 private    DBHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper=new DBHelper(register.this,"user.db",null,1);
       db=dbHelper.getReadableDatabase();
        init();
        //取消按钮实现：
        cancel_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        //注册按钮实现：
        register_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取注册界面的账号和密码
                String  username=username_register.getText().toString();
                String password=password_register.getText().toString();
                insertData(db,username,password);

            }
        });
    }

    private void init() {
        username_register=findViewById(R.id.username_register);
        password_register=findViewById(R.id.password_register);
        register_button_register=findViewById(R.id.register_button_register);
        cancel_button_register=findViewById(R.id.cancel_button_register);
    }
    public void insertData(SQLiteDatabase sqLiteDatabase,String username,String password){
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long re=sqLiteDatabase.insert("tb_user",null,contentValues);

        if(re!=-1){
            Toast.makeText(register.this,"增加成功,请返回登录界面",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(register.this,"增加失败,用户名已经存在",Toast.LENGTH_SHORT).show();
        }
    }
}
