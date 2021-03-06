package com.example.sangloveslinh.todoapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangloveslinh.todoapp.database.AppToDo;
import com.example.sangloveslinh.todoapp.database.DaoSession;
import com.example.sangloveslinh.todoapp.database.ToDoList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TabMyDay extends Fragment {

    ListView listToDo;
    View rootView;
    static ArrayList<DataListModel> dataListModelArrayListTabMyDay;
    static ToDoAdapter toDoAdapterMyDay;
    CheckBox ckComplete;
    EditText edtToDoName, edtdesctiption, edtdeadline;
    TextView txtAddToMyDay;
    ImageButton imgbtnChangeAddToMyDay, imgbtnDeleteDueDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab1myday, container, false);
        AnhXa();
        GanListView();
        showAlertDialogUpdate();

        return rootView;
    }

    private void showAlertDialogUpdate() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        listToDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View alertDetailView = getLayoutInflater().inflate(R.layout.modify_todo, null);
                ckComplete = alertDetailView.findViewById(R.id.checkboxCompleteTask);
                edtToDoName = alertDetailView.findViewById(R.id.edittexttaskName);
                txtAddToMyDay = alertDetailView.findViewById(R.id.textviewAddToMyDay);
                imgbtnChangeAddToMyDay = alertDetailView.findViewById(R.id.buttonChangeAddMyToDay);
                edtdeadline = alertDetailView.findViewById(R.id.edittextDeadLine);
                edtdesctiption = alertDetailView.findViewById(R.id.edittextNote);
                imgbtnDeleteDueDate = alertDetailView.findViewById(R.id.buttonDeleteDueDate);

                final Long idToDoCurrent = dataListModelArrayListTabMyDay.get(position).getId();

                final ToDoList toDoList = database.getToDoListDao().load(idToDoCurrent);
                edtToDoName.setText(toDoList.getToDoName());
                edtdesctiption.setText(toDoList.getToDoDescription());
                if (toDoList.getIsAddToMyDayTab() == true)
                    txtAddToMyDay.setText("Added to My Day");
                else txtAddToMyDay.setText("Add to My Day");
                edtdeadline.setText(toDoList.getDueDate());
                if (toDoList.getIsComplete() == true)
                    ckComplete.setChecked(true);
                else ckComplete.setChecked(false);


                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Cập nhật to do");
                alert.setView(alertDetailView);
                alert.setCancelable(false);
                alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Bạn đã hủy", Toast.LENGTH_SHORT).show();
                    }
                });

                imgbtnChangeAddToMyDay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtAddToMyDay.getText().toString().equals("Add to My Day")) {
                            txtAddToMyDay.setText("Added to My Day");
                        } else txtAddToMyDay.setText("Add to My Day");
                    }
                });

                imgbtnDeleteDueDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtdeadline.setText("");
                    }
                });

                alert.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDoList.setToDoName(edtToDoName.getText().toString());
                        toDoList.setToDoDescription(edtdesctiption.getText().toString());
                        toDoList.setDueDate(edtdeadline.getText().toString());
                        if (txtAddToMyDay.getText().toString().equals("Added to My Day")) {
                            toDoList.setIsAddToMyDayTab(true);
                        } else toDoList.setIsAddToMyDayTab(false);
                        if (ckComplete.isChecked())
                            toDoList.setIsComplete(true);
                        else toDoList.setIsComplete(false);
                        toDoList.setIsAddToToDoListTab(true);
                        toDoList.setIsToDoNotComplete(false);
                        database.getToDoListDao().update(toDoList);
                        Toast.makeText(getActivity(), "Cập nhật todo thành công", Toast.LENGTH_SHORT).show();
                        TabMyDay.ReloadList();
                        TabToDo.ReloadList();
                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    private void GanListView() {
        toDoAdapterMyDay = new ToDoAdapter(getActivity(), R.layout.todo_item, dataListModelArrayListTabMyDay);
        listToDo.setAdapter(toDoAdapterMyDay);
    }

    protected static void ReloadList() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        dataListModelArrayListTabMyDay.clear();
        ToDoActivity.TO_DO_LISTS = (ArrayList<ToDoList>) database.getToDoListDao().loadAll();
        for (ToDoList toDoList : ToDoActivity.TO_DO_LISTS) {
            if (toDoList.getIsAddToMyDayTab() == true)
                dataListModelArrayListTabMyDay.add(new DataListModel(toDoList.getToDoId(), null, toDoList.getToDoName().toString(), toDoList.getToDoDescription().toString(), toDoList.getDueDate().toString(), toDoList.getIsAddToMyDayTab(), toDoList.getIsComplete()));
        }
        toDoAdapterMyDay.notifyDataSetChanged();
    }

    private void AnhXa() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        ToDoActivity.TO_DO_LISTS = (ArrayList<ToDoList>) database.getToDoListDao().loadAll();
        listToDo = rootView.findViewById(R.id.listviewmyday);
        dataListModelArrayListTabMyDay = new ArrayList<>();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);

        for (ToDoList toDoList : ToDoActivity.TO_DO_LISTS) {
            if (toDoList.getIsAddToMyDayTab() == true && toDoList.getDate().toString().equals(formattedDate))
                dataListModelArrayListTabMyDay.add(new DataListModel(toDoList.getToDoId(), null, toDoList.getToDoName().toString(), toDoList.getToDoDescription().toString(), toDoList.getDueDate().toString(), toDoList.getIsAddToMyDayTab(), toDoList.getIsComplete()));
            else {
                toDoList.setIsToDoNotComplete(true);
                toDoList.setIsAddToMyDayTab(false);
                TabNotComplete.ReloadList();
            }
        }

    }


}
