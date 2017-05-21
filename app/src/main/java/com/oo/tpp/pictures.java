package com.oo.tpp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class pictures extends AppCompatActivity {
    ViewPager viewPager;
    swipeImage swipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures2);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        swipeImage=new swipeImage(this);
        viewPager.setAdapter(swipeImage);
    }
}
