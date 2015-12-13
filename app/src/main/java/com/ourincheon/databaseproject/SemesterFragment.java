package com.ourincheon.databaseproject;

import android.app.Activity;
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

/**
 * Created by Youngdo on 2015-11-02.
 */
public class SemesterFragment extends android.support.v4.app.Fragment {
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private android.support.v4.view.PagerAdapter adapter;
    private static final String ARG_POSITION = "position";
    private int positions;


 /*   public static SemesterFragment newInstanse(int position){
        SemesterFragment f = new SemesterFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
    //       Layout을 inflater을하여 View작업을 하는 곳.

*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  positions = getArguments().getInt(ARG_POSITION);
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
        List1 list = new List1(getActivity());
        listView.setAdapter(list);
        return rootView;


    }

    public class List1 extends ArrayAdapter<String>{
        private final Activity context;


        public List1(Activity context) {
            super(context, R.layout.itemlist);
            this.context = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.itemlist,null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView manufac = (TextView) rowView.findViewById(R.id.manufac);
            TextView price = (TextView) rowView.findViewById(R.id.price);
            title.setTextSize(30);
            title.setText("ㅗㅗ");
            manufac.setTextSize(10);
            manufac.setText("ㅗㅗ");
            return rowView;
        }
    }


}
