package com.example.sangloveslinh.todoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sangloveslinh.todoapp.database.AppToDo;
import com.example.sangloveslinh.todoapp.database.DaoSession;

import java.util.List;

public class ToDoAdapter extends BaseAdapter{

    Context context;
    int myLayout;
    List<DataListModel> dataListModels;

    public ToDoAdapter(Context context, int myLayout, List<DataListModel> dataListModels) {
        this.context = context;
        this.myLayout = myLayout;
        this.dataListModels = dataListModels;
    }

    @Override
    public int getCount() {
        return dataListModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout,null);

        // ánh xạ View
        TextView txttitle = convertView.findViewById(R.id.textviewtitletodo);
        TextView txtdescription = convertView.findViewById(R.id.textviewdescription);
        TextView txtdeadline = convertView.findViewById(R.id.textviewdeadline);
//        ImageView imgTodo = convertView.findViewById(R.id.imageviewtodo);
        final CheckBox ckbComplete = convertView.findViewById(R.id.checkboxComplete);

        final DataListModel dataListModel = dataListModels.get(position);

        txttitle.setText(dataListModel.getTitle());
        txtdescription.setText(dataListModel.getDescription());
        txtdeadline.setText(dataListModel.getDeadline());
//        Bitmap bitmap = BitmapFactory.decodeByteArray(dataListModel.getImage(),0,dataListModel.getImage().length);
//        imgTodo.setImageBitmap(bitmap);
        if(dataListModel.isChecked() == true)
            ckbComplete.setChecked(true);

        ckbComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ckbComplete.isChecked() != dataListModel.isChecked()) {
                    ToDoActivity.UpdateToDo(ckbComplete.isChecked(),dataListModel.getId(),dataListModel.getTitle(),dataListModel.getDescription(),dataListModel.getDeadline(),dataListModel.isAddToMyDay());
                }
            }
        });
        return convertView;
    }
}
