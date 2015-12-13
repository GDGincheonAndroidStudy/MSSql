package com.ourincheon.databaseproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Youngdo on 2015-11-02.
 */
public class SemesterFragment extends android.support.v4.app.Fragment {
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private android.support.v4.view.PagerAdapter adapter;
    private static final String ARG_POSITION = "position";
    private int positions;

    private ArrayList<String> arrayList = new ArrayList<>();
   public static SemesterFragment newInstanse(int position){
        SemesterFragment f = new SemesterFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
    //       Layout을 inflater을하여 View작업을 하는 곳.



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  positions = getArguments().getInt(ARG_POSITION);
        for(int a=0; 100>a;a++){
            arrayList.add(String.valueOf(a+1));
        }
        positions = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    /*Fragement에서 리소스의 뷰들을 인플레이션하는 방법은 2가지가 있다.
     1.View rootView를 선언해서 이 안에 해당 프레그먼트의 리소스파일을 인플레이션해서
      그 안에 있는 뷰들을 rootView.findViewById이러한 형식으로 인플레이션 하던지
     2. getView().findViewById를 통해서 인플레이션 하던지*/
//   Fragment에서는 this대신에 getActivity를 통해 해당 Activity를 넘겨준다.
        adapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        View rootView;
        rootView = inflater.inflate(R.layout.list, null);
        ListView listView = (ListView) rootView.findViewById(R.id.semesterList);
        List1 list = new List1(getActivity(),arrayList);
        listView.setAdapter(list);
        return rootView;
    }

    public class List1 extends ArrayAdapter<String>{
        private final Activity context;

        private ArrayList<String> items;

        public List1(Activity context,ArrayList<String> arrlist)
        {
            super(context, R.layout.itemlist,arrlist);
            this.items = arrlist;
            this.context = context;
        }
        ///
        ///여기서 초기화 할때 arrList!
        ////!!!


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();

            View v = convertView;
            v = inflater.inflate(R.layout.itemlist,null);
            ImageView imageView = (ImageView) v.findViewById(R.id.image);
            TextView title = (TextView) v.findViewById(R.id.title);
            TextView manufac = (TextView) v.findViewById(R.id.manufac);
            TextView price = (TextView) v.findViewById(R.id.price);
            title.setTextSize(30);
            title.setText("ㅗㅗ" + items.get(position));

            manufac.setTextSize(10);
            manufac.setText("ㅗㅗ");
            return v;
        }


    }


}
