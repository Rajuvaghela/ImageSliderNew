package com.example.raju.myapplication;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    CirclePageIndicator indicator;
    ViewPager intro_images;
    private ViewPagerAdapter mAdapter;
    ArrayList<Integer> array_image;
    public int pos = 0;
    public Runnable mRun;
    private final Handler handler = new Handler();
    Button btn_dialog;
    Dialog dialog;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        array_image = new ArrayList<Integer>();
        array_image.add(R.drawable.model2);
        array_image.add(R.drawable.model3);
        array_image.add(R.drawable.model4);
        activity=this;

        btn_dialog=(Button)findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);


    }
    private void setTopSlider() {
        mAdapter = new ViewPagerAdapter(MainActivity2.this, array_image);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        intro_images.setCurrentItem(pos, true);
        indicator.setViewPager(intro_images);

        mRun = new Runnable() {
            @Override
            public void run() {
                if (pos < mAdapter.getCount()) {
                    intro_images.setCurrentItem(pos, true);
                    handler.postDelayed(this, 3000);
                    pos++;
                } else {
                    pos = 0;
                    intro_images.setCurrentItem(pos, true);
                    handler.postDelayed(this, 3000);
                    indicator.setViewPager(intro_images);
                    pos++;
                }
            }
        };
        handler.post(mRun);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog:
                OpenDialog();
                break;
        }
    }

    private void OpenDialog() {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
     //   dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        intro_images = (ViewPager) dialog.findViewById(R.id.intro_images);
        indicator = (CirclePageIndicator) dialog.findViewById(R.id.indicator);
        setTopSlider();

        dialog.show();
    }
}