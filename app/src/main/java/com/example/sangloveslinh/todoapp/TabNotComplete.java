package com.example.sangloveslinh.todoapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sangloveslinh.todoapp.database.AppToDo;
import com.example.sangloveslinh.todoapp.database.DaoSession;
import com.example.sangloveslinh.todoapp.database.ToDoList;

import java.util.ArrayList;

public class TabNotComplete extends Fragment {

    ListView listToDo;
    View rootView;
    static ArrayList<DataListModel> dataListModelArrayListTabNotComplete;
    static ToDoAdapter toDoAdapterTabNotComplete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab3notcomplete,container,false);
        AnhXa();
        toDoAdapterTabNotComplete = new ToDoAdapter(getActivity(),R.layout.todo_item,dataListModelArrayListTabNotComplete);
        listToDo.setAdapter(toDoAdapterTabNotComplete);
        return rootView;
    }

    private void GanListView() {
        toDoAdapterTabNotComplete = new ToDoAdapter(getActivity(),R.layout.todo_item,dataListModelArrayListTabNotComplete);
        listToDo.setAdapter(toDoAdapterTabNotComplete);
    }

    protected static void ReloadList() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        dataListModelArrayListTabNotComplete.clear();
        ToDoActivity.TO_DO_LISTS = (ArrayList<ToDoList>) database.getToDoListDao().loadAll();
        for (ToDoList toDoList : ToDoActivity.TO_DO_LISTS) {
            if (toDoList.getIsToDoNotComplete() == true)
                dataListModelArrayListTabNotComplete.add(new DataListModel(toDoList.getToDoId(),null, toDoList.getToDoName().toString(), toDoList.getToDoDescription().toString(), toDoList.getDueDate().toString(),toDoList.getIsAddToMyDayTab(), toDoList.getIsComplete()));
        }
        toDoAdapterTabNotComplete.notifyDataSetChanged();
    }

    private void AnhXa() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        ToDoActivity.TO_DO_LISTS = (ArrayList<ToDoList>) database.getToDoListDao().loadAll();
        listToDo = rootView.findViewById(R.id.listviewnotcomplete);
        dataListModelArrayListTabNotComplete = new ArrayList<>();
        for (ToDoList toDoList : ToDoActivity.TO_DO_LISTS) {
            if (toDoList.getIsToDoNotComplete() == true)
                dataListModelArrayListTabNotComplete.add(new DataListModel(toDoList.getToDoId(),null, toDoList.getToDoName().toString(), toDoList.getToDoDescription().toString(), toDoList.getDueDate().toString(),toDoList.getIsAddToMyDayTab(), toDoList.getIsComplete()));
        }
    }
}
