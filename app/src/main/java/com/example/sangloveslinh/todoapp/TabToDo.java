package com.example.sangloveslinh.todoapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TabToDo extends Fragment{

    ListView listToDo;
    View rootView;
    ArrayList<DataListModel> dataListModelArrayList;
    ToDoAdapter toDoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2todo,container,false);
        AnhXa();
        toDoAdapter = new ToDoAdapter(getActivity(),R.layout.todo_item,dataListModelArrayList);
        listToDo.setAdapter(toDoAdapter);
        return rootView;
    }

    private void AnhXa() {
        listToDo = rootView.findViewById(R.id.listviewtodo);
        dataListModelArrayList = new ArrayList<>();
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
        dataListModelArrayList.add(new DataListModel(null,"Vợ yêu của anh","Heheheheh","12/2/2019",1));
    }
}
