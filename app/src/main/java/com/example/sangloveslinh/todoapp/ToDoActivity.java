package com.example.sangloveslinh.todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangloveslinh.todoapp.database.AppToDo;
import com.example.sangloveslinh.todoapp.database.DaoSession;
import com.example.sangloveslinh.todoapp.database.ToDoList;
import com.example.sangloveslinh.todoapp.database.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ToDoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    CheckBox ckComplete;
    EditText edtToDoName, edtdesctiption, edtdeadline;
    TextView txtAddToMyDay;
    ImageButton imgbtnChangeAddToMyDay, imgbtnDeleteDueDate;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    static ArrayList<ToDoList> TO_DO_LISTS = null;
    public static Context context = null;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        XuLyTabView();
        context = getApplication();

    }


    private void XuLyTabView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        final DaoSession database = ((AppToDo) getApplication()).getDaoSession();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View alertDetailView = getLayoutInflater().inflate(R.layout.modify_todo, null);
                ckComplete = alertDetailView.findViewById(R.id.checkboxCompleteTask);
                edtToDoName = alertDetailView.findViewById(R.id.edittexttaskName);
                txtAddToMyDay = alertDetailView.findViewById(R.id.textviewAddToMyDay);
                imgbtnChangeAddToMyDay = alertDetailView.findViewById(R.id.buttonChangeAddMyToDay);
                edtdeadline = alertDetailView.findViewById(R.id.edittextDeadLine);
                edtdesctiption = alertDetailView.findViewById(R.id.edittextNote);
                imgbtnDeleteDueDate = alertDetailView.findViewById(R.id.buttonDeleteDueDate);
                ckComplete = alertDetailView.findViewById(R.id.checkboxCompleteTask);

                AlertDialog.Builder alert = new AlertDialog.Builder(ToDoActivity.this);
                alert.setTitle("Thêm to do");
                alert.setView(alertDetailView);
                alert.setCancelable(false);
                alert.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ToDoActivity.this, "Bạn đã thoát", Toast.LENGTH_SHORT).show();
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

                alert.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToDoList toDoList = new ToDoList();
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



                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(c);
                        toDoList.setDate(formattedDate);

                        database.getToDoListDao().insert(toDoList);
                        Toast.makeText(ToDoActivity.this, "Thêm todo thành công", Toast.LENGTH_SHORT).show();
                        TabMyDay.ReloadList();
                        TabToDo.ReloadList();
                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    static void UpdateToDo(boolean checked, Long id, String title, String description, String deadline, boolean addToMyDay) {
        final DaoSession database = ((AppToDo) context).getDaoSession();
        ToDoList toDoList = database.getToDoListDao().load(id);
        toDoList.setToDoName(title);
        toDoList.setToDoDescription(description);
        toDoList.setDueDate(deadline);
        toDoList.setIsAddToMyDayTab(addToMyDay);
        toDoList.setIsComplete(checked);

        database.getToDoListDao().update(toDoList);
        TabMyDay.ReloadList();
        TabToDo.ReloadList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        final DaoSession database = ((AppToDo) getApplication()).getDaoSession();

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TabMyDay tabMyDay = new TabMyDay();
                    return tabMyDay;
                case 1:
                    TabToDo tabToDo = new TabToDo();
                    return tabToDo;
                case 2:
                    TabNotComplete tabNotComplete = new TabNotComplete();
                    return tabNotComplete;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
