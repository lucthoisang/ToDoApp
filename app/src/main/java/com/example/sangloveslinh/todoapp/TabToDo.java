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

public class TabToDo extends Fragment {

    ListView listToDo;
    View rootView;
    static ArrayList<DataListModel> dataListModelArrayListTabToDo;
    static ToDoAdapter toDoAdapterTabToDo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2todo, container, false);
        AnhXa();
        GanListView();
        XuLyListView();
        return rootView;
    }

    private void XuLyListView() {
    }

    private void GanListView() {
        toDoAdapterTabToDo = new ToDoAdapter(getActivity(), R.layout.todo_item, dataListModelArrayListTabToDo);
        listToDo.setAdapter(toDoAdapterTabToDo);
    }

    protected static void ReloadList() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        dataListModelArrayListTabToDo.clear();
        ToDoActivity.TO_DO_LISTS = (ArrayList<ToDoList>) database.getToDoListDao().loadAll();
        for (ToDoList toDoList : ToDoActivity.TO_DO_LISTS) {
            if (toDoList.getIsAddToMyDayTab() == true)
                dataListModelArrayListTabToDo.add(new DataListModel(toDoList.getToDoId(), null, toDoList.getToDoName().toString(), toDoList.getToDoDescription().toString(), toDoList.getDueDate().toString(), toDoList.getIsAddToMyDayTab(), toDoList.getIsComplete()));
        }
        toDoAdapterTabToDo.notifyDataSetChanged();
    }

    private void AnhXa() {
        final DaoSession database = ((AppToDo) ToDoActivity.context).getDaoSession();
        ToDoActivity.TO_DO_LISTS = (ArrayList<ToDoList>) database.getToDoListDao().loadAll();
        listToDo = rootView.findViewById(R.id.listviewtodo);
        dataListModelArrayListTabToDo = new ArrayList<>();
        for (ToDoList toDoList : ToDoActivity.TO_DO_LISTS) {
            if (toDoList.getIsAddToToDoListTab() == true)
                dataListModelArrayListTabToDo.add(new DataListModel(toDoList.getToDoId(), null, toDoList.getToDoName().toString(), toDoList.getToDoDescription().toString(), toDoList.getDueDate().toString(), toDoList.getIsAddToMyDayTab(), toDoList.getIsComplete()));
        }
    }
}
