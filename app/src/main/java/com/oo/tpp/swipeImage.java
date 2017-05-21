package com.oo.tpp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Cesar on 21-May-17.
 */

public class swipeImage extends PagerAdapter {

    private int [] images ={R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public swipeImage(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.activity_swipe_image2,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
        TextView textView = (TextView) itemView.findViewById(R.id.imageCount);
        imageView.setImageResource(images[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return  (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
