package com.example.loginandsqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class LoginMain extends AppCompatActivity {
 private  DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
 private Button login_button,register_button,buttontest;
 private EditText password,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        Stetho.initializeWithDefaults(this);
        dbHelper=new DBHelper(LoginMain.this,"user.db",null,1);
         sqLiteDatabase=dbHelper.getReadableDatabase();

     init();//初始化控件
        //登录按钮的实现：
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr=username.getText().toString();
                String passwordStr=password.getText().toString();
                //查询账号和密码
                Cursor cursor = sqLiteDatabase.query("tb_user", new String[]{"username", "password"}, null, null, null, null, null);

                boolean login = false;//账号密码是否匹配
                //从数据库中匹配账号密码
                while (cursor.moveToNext()) {
                    if (usernameStr.equals(cursor.getString(cursor.getColumnIndex("username")))
                            && passwordStr.equals(cursor.getString(cursor.getColumnIndex("password")))) {//用户名匹配后才会去匹配密码：&&逻辑的好处
                        login = true;
                        break;
                    }
                }
                if(login){
                    Intent intent=new Intent(LoginMain.this,navigation.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginMain.this,"账号或密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //注册按钮的实现
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginMain.this,register.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        login_button=findViewById(R.id.login_button);
        register_button=findViewById(R.id.register_button);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
    }
}
