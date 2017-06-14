package com.oo.tpp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class pictures extends AppCompatActivity {
    ViewPager viewPager;
    swipeImage swipeImage;
    public static int imagesID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures2);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        Bundle b = getIntent().getExtras();
        if(b != null)
            imagesID = b.getInt("stSlike");
        swipeImage=new swipeImage(this);
        com.oo.tpp.swipeImage.selectedImages =imagesID;
        viewPager.setAdapter(swipeImage);
    }
}
