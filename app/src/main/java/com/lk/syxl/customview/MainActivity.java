package com.lk.syxl.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.lk.syxl.customview.entity.PieData;
import com.lk.syxl.customview.view.PieView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PieData> mData = new ArrayList<>();
    private RelativeLayout mMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMain = (RelativeLayout) findViewById(R.id.activity_main);

        PieView pie = new PieView(this);
        mData.add(new PieData("a",12));
        mData.add(new PieData("b",112));
        mData.add(new PieData("c",42));
        mData.add(new PieData("d",62));
        mData.add(new PieData("e",82));
        mData.add(new PieData("f",32));
        pie.setStartAngle(0);
        pie.setData(mData);
        mMain.addView(pie);
    }
}
