package com.example.loginandsqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class medicineMain extends AppCompatActivity {
 private Button medicine_addbt,medicine_delbt,medicine_searchbt,medicine_changebt;//按钮
    private EditText medicine_edit,medicineother_edit,frequency_edit;//编辑框
    String medicinename,medicineothername,medicinefrequency;//用于存放编辑框内容的变量
 private TextView textView;
 MedicineDBHelper dbHelperMedicineDBHelper;
 SQLiteDatabase sqLiteDatabaseMedicineDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_main);
        dbHelperMedicineDBHelper=new MedicineDBHelper(this,"medicine.db",null,1);
        sqLiteDatabaseMedicineDBHelper=dbHelperMedicineDBHelper.getReadableDatabase();
        //初始化控件
        init();
//test
        Button buttontest=findViewById(R.id.button2);
        buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(medicineMain.this,Mine.class);
                startActivity(intent);
            }
        });
        //插入药品
        medicine_addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取编辑框里面的内容
                medicinename=medicine_edit.getText().toString();
                medicineothername=medicineother_edit.getText().toString();
                medicinefrequency=frequency_edit.getText().toString();
                ContentValues values=new ContentValues();
                //medicine varchar(20) primary key, othername varchar(20),frequency integer)";
                values.put("medicine",medicinename);
                values.put("othername",medicineothername);
                values.put("frequency",Integer.valueOf(medicinefrequency));
                Long ret= sqLiteDatabaseMedicineDBHelper.insert("tb_medicine",null,values);
                if(ret==-1){
                    Toast.makeText(medicineMain.this,"新增失败",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(medicineMain.this,"新增成功",Toast.LENGTH_SHORT).show();

                }

            }
        });
        //删除药品
        medicine_delbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取编辑框里面的内容
                medicinename=medicine_edit.getText().toString();
                medicineothername=medicineother_edit.getText().toString();
                medicinefrequency=frequency_edit.getText().toString();
                sqLiteDatabaseMedicineDBHelper.delete("tb_medicine","medicine = ?",new String[]{medicinename});
                Toast.makeText(medicineMain.this,"药品删除成功！",Toast.LENGTH_SHORT).show();
            }
        });
        //查询药品
        medicine_searchbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取编辑框里面的内容
                medicinename=medicine_edit.getText().toString();
                medicineothername=medicineother_edit.getText().toString();
                medicinefrequency=frequency_edit.getText().toString();
                Cursor cursor =sqLiteDatabaseMedicineDBHelper.query("tb_medicine",null,null,null,null,null,null);
                while(cursor.moveToNext()){
                    String name=cursor.getString(cursor.getColumnIndex("medicine"));
                    String othername=cursor.getString(cursor.getColumnIndex("othername"));
                    String frequency=cursor.getString(cursor.getColumnIndex("frequency"));
                    textView.setText(name+" "+othername+" "+frequency);
                }
            }
        });

        //修改
        medicine_changebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("frequency",medicinefrequency);
                sqLiteDatabaseMedicineDBHelper.update("tb_medicine",contentValues,null,null);

            }
        });

    }

    private void init() {
        medicine_addbt =findViewById(R.id.medicine_addbt);
        medicine_delbt=findViewById(R.id.medicine_delbt);
        medicine_searchbt=findViewById(R.id.medicine_searchbt);
        medicine_changebt=findViewById(R.id.medicine_changebt);
        textView=findViewById(R.id.textView);
        medicine_edit=findViewById(R.id.medicine_edit);
        medicineother_edit=findViewById(R.id.medicineother_edit);
        frequency_edit=findViewById(R.id.frequency_edit);
    }
}
