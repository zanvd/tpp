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
    public static int selectedImages= 0;

    private int [] images1 ={R.drawable.slika11,R.drawable.slika12,R.drawable.slika13, R.drawable.slika14, R.drawable.slika15};
    private int [] images2 ={R.drawable.slika21,R.drawable.slika22,R.drawable.slika23, R.drawable.slika24};
    private int [] images3 ={R.drawable.slika31, R.drawable.slika32};
    private int [] images4 ={R.drawable.slika41};
    private int [] images5 ={R.drawable.slika51,R.drawable.slika52,R.drawable.slika53};
    private int [] images6 ={R.drawable.slika61, R.drawable.slika62, R.drawable.slika63, R.drawable.slika64};
    private int [] images7 ={R.drawable.slika71,R.drawable.slika72};
    private int [] images8 ={R.drawable.slika81, R.drawable.slika82};
    private int [] images9 ={R.drawable.slika9};
    private int [] [] collection = {images1, images2,images3, images4, images5, images6, images7, images8, images9};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public swipeImage(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {

        return collection[selectedImages].length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.activity_swipe_image2,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
        TextView textView = (TextView) itemView.findViewById(R.id.imageCount);
        imageView.setImageResource(collection[selectedImages][position]);
        container.addView(itemView);
        return itemView;
    }

    public void setId(int id) {
        selectedImages = id;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return  (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
