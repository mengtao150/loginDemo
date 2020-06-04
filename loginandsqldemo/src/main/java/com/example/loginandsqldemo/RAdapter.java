package com.example.loginandsqldemo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.MyViewHolder> {
    List<MedicineClass> listUser;
    MyOnclicker myOnclicker;

    public void setMyOnclicker(MyOnclicker myOnclicker) {
        this.myOnclicker = myOnclicker;
    }

    public interface MyOnclicker{
        public void myOnclick(View v);

    }

    public RAdapter(List<MedicineClass> lUser, Context context){
        listUser=lUser;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText(listUser.get(position).medicine);
        holder.textView2.setText(listUser.get(position).othername);
        holder.textView3.setText(listUser.get(position).frequency);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnclicker.myOnclick( v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.item_frequent);
            textView2=itemView.findViewById(R.id.item_name);
            textView3=itemView.findViewById(R.id.item_othername);
        }
    }
}

