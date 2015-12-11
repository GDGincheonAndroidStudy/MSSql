package com.ourincheon.databaseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Youngdo on 2015-11-02.
 */
public class login extends Activity {
    ArrayList<String> Student_code = new ArrayList<String>();
    ArrayList<String> Student_passwd = new ArrayList<String>();
    EditText name;
    EditText pass;
    Button login;
    int identify = 0;
    DataStorage dataStorage = new DataStorage();

    //데이터넣는거
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (Button) findViewById(R.id.button);
        MssqlTask mssqlTask = new MssqlTask();
        mssqlTask.execute();
        name = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test", String.valueOf(Student_code.size()));
                for (int i = 0; i < Student_code.size(); i++) {
                    //Log.e("test1", Student_passwd.get(i));
                    //string 열에서는 equal을 써서 해야하는듯 == 을 쓰면 안됨
                    try {
                        if ((Student_code.get(i).equals(name.getText().toString())) &&
                                (Student_passwd.get(i).equals(pass.getText().toString()))) {

                            identify = i;
                            Toast.makeText(login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, Splash.class);
                            intent.putExtra("identify", identify);
                            startActivity(intent);
                            break;
                        } else {
                            Toast.makeText(login.this, "다시 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e) {
                        Toast.makeText(login.this, "다시 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    //내가 연결한 code의 position을 가지고 조인된 값 불러오기
    public class MssqlTask extends AsyncTask<String, Integer, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            Connection conn;
            ArrayList<String> list = new ArrayList<String>();
            try {
                int i = 0;
                Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://117.16.244.162/201101658", "201101658", "dnflwlq5");
                Statement stmt = conn.createStatement();

                ResultSet reset = stmt.executeQuery("select * from NEW_student");
                while (reset.next()) {
                    Student_code.add(i, reset.getString(1));
                    Student_passwd.add(i, reset.getString(4));
                    Log.e("whiletest" + i, reset.getString(1));
                    Log.e("whiletest" + i, reset.getString(4));
                    i++;
                }//datastorage에 저장이 안되는거
                Log.e("branch1", "branch1");
                if (Student_code.size() > 0) {
                    dataStorage.getInstance().setstudent_code(Student_code);
                    //dataStorage.getM_instance().setstudent_code(Student_code);
                    Log.e("data1", "ㅗㅗㅗ");
                } else {
                    Log.e("data", "데이터 저장 실패");
                }

                reset.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                Log.e("fail", "fail");
                //dataStorage.getM_instance().setstudent_code(Student_code);

            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
        }
    }


}
