package com.example.loginandsqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MedicineFragment extends Fragment  {


    private Button medicine_addbt,medicine_delbt,medicine_searchbt,medicine_changebt;//按钮
    private EditText medicine_edit,medicineother_edit,frequency_edit;//编辑框
    String medicinename,medicineothername,medicinefrequency;//用于存放编辑框内容的变量
    Button buttontest;
    private TextView textView;
    MedicineDBHelper dbHelperMedicineDBHelper;
    SQLiteDatabase sqLiteDatabaseMedicineDBHelper;
    List<MedicineClass> listUser; //创建MedicineClass对象
    RAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        dbHelperMedicineDBHelper = new MedicineDBHelper(getActivity(), "medicine.db", null, 1);
        sqLiteDatabaseMedicineDBHelper = dbHelperMedicineDBHelper.getReadableDatabase();
        //获取控件的值
        medicine_addbt = view.findViewById(R.id.medicine_addbt);
        medicine_delbt = view.findViewById(R.id.medicine_delbt);
        medicine_searchbt = view.findViewById(R.id.medicine_searchbt);
        medicine_changebt = view.findViewById(R.id.medicine_changebt);
        textView = view.findViewById(R.id.textView);
        medicine_edit = view.findViewById(R.id.medicine_edit);
        medicineother_edit = view.findViewById(R.id.medicineother_edit);
        frequency_edit = view.findViewById(R.id.frequency_edit);
        //-------------------
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);//获取到recyclerView；
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//以LinearLayoutManager形式展示
        listUser = new ArrayList<>();//实例化ArrayList
        adapter = new RAdapter(listUser, getActivity());
        //为adapter添加点击事件
        adapter.setMyOnclicker(new RAdapter.MyOnclicker() {
            @Override
            public void myOnclick(View v) {
                TextView tv = v.findViewById(R.id.item_name);
                String userId = tv.getText().toString();
                //db.delete(DBHelper.TABLE_NAME,"userId=?",new String[]{userId});
                //refresh();
                Toast.makeText(getActivity(), tv.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        //为recyclerView绑定adapter
        recyclerView.setAdapter(adapter);
        //药物添加
        medicine_addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicine = medicine_edit.getText().toString();
                String medicineother = medicineother_edit.getText().toString();
                int frequency=Integer.parseInt(frequency_edit.getText().toString());
                ContentValues contentValues = new ContentValues();
                //String sql="create table tb_medicine(medicine varchar(20) primary key, othername varchar(20),frequency integer)";//创建药物名称，别名，频度
                contentValues.put("medicine", medicine);
                contentValues.put("othername", medicineother);
                contentValues.put("frequency", frequency);
                long ret = sqLiteDatabaseMedicineDBHelper.insert("tb_medicine", null, contentValues);
                if (ret != -1) {
                    Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "添加失败，药品已经存在", Toast.LENGTH_SHORT).show();
                }

            }
        });
//药物修改
        medicine_changebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicine = medicine_edit.getText().toString();
                String medicineother = medicineother_edit.getText().toString();
                int frequency = Integer.parseInt(frequency_edit.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put("medicine", medicine);
                contentValues.put("othername", medicineother);
                contentValues.put("frequency", frequency);
                int ret = sqLiteDatabaseMedicineDBHelper.update("tb_medicine", contentValues, "medicine=?", new String[]{medicine});
                if (ret > 0) {
                    Toast.makeText(getActivity(), "rignt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
                refresh();
            }
        });
        //药物删除
        medicine_delbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicine = medicine_edit.getText().toString();
                ContentValues contentValues = new ContentValues();
                //String db_sql="create table tb_user(userId integer primary key,userName varchar(20),className Text)";
                int ret = sqLiteDatabaseMedicineDBHelper.delete("tb_medicine", "medicine=?", new String[]{"" + medicine});
                if (ret > 0) {
                    Toast.makeText(getActivity(), "rignt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
                refresh();
            }
        });
        //药物搜索
        medicine_searchbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listUser.clear();
                //  String medicine=medicine_edit.getText().toString();
                //                String medicineother=medicineother_edit.getText().toString();
                Cursor cursor = sqLiteDatabaseMedicineDBHelper.query("tb_medicine", null, null, null, null, null, null);
                //String sql="create table tb_medicine(medicine varchar(20) primary key, othername varchar(20),frequency integer)";//创建药物名称，别名，频度
                while (cursor.moveToNext()) {
                    MedicineClass user = new MedicineClass();
                    int frequency = cursor.getInt(cursor.getColumnIndex("frequency"));
                    String medicine = cursor.getString(cursor.getColumnIndex("medicine"));
                    String medicineother = cursor.getString(cursor.getColumnIndex("othername"));
  /**
 * public String medicine;
 *     public String  othername;
 *     public String frequency;
 */
                    user.medicine = medicine;
                    user.othername = medicineother;
                    user.frequency = frequency + "";
                    listUser.add(user);
                }
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

        private void refresh () {

        }
    }

    /*  buttontest=view.findViewById(R.id.button2);
      buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Mine.class);
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
                    Toast.makeText(getActivity(),"新增失败",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),"新增成功",Toast.LENGTH_SHORT).show();

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
                Toast.makeText(getActivity(),"药品删除成功！",Toast.LENGTH_SHORT).show();
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
        });*/


