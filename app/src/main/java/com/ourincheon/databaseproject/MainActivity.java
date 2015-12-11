package com.ourincheon.databaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    Button timetable;
    DataStorage dataStorage = new DataStorage();

    /*
    MVVM,
    MVP,
    MVC
    http://tosslab.github.io/android/2015/03/01/01.Android%20mvc%20mvvm%20mvp/
    들어 가셔서 한번 확인해 보셔요 :)
    저는 MVVM을 주로 씁니다
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int grade1 = intent.getIntExtra("grade", 0);


        arrayList = new ArrayList<String>();
        arrayList.add(dataStorage.getInstance().getMainList().get(0));
        arrayList.add(dataStorage.getInstance().getMainList().get(1));
        arrayList.add(dataStorage.getInstance().getMainList().get(2));
        arrayList.add(dataStorage.getInstance().getMainList().get(3));
        arrayList.add(dataStorage.getInstance().getMainList().get(4));
        arrayList.add(dataStorage.getInstance().getMainList().get(5));
        arrayList.add(dataStorage.getInstance().getMainList().get(6));


        initview(grade1);
        setListener();

        //훨씬 보기 좋고 깔끔하게 보여서 보기 좋습니다 후에 리스너 컨트롤 뷰 컨트롤러를 다룰때 좀 더 쉽게 접근 할 수 있답니다:)

    }

    public void initview(int value){
        //View를 초기화 시키는 함수
        //모든 Element를 전역변수화 시킵니다 :)

        TextView TVgrade = (TextView) findViewById(R.id.textView3);
        TVgrade.setText(" " + String.valueOf(value));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        timetable = (Button) findViewById(R.id.button3);
    }

    public void setListener(){
        //클릭 리스너를 초기화.
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Semester.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
