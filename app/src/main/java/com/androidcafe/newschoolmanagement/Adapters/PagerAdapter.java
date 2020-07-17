package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Model.Banner;
import com.androidcafe.newschoolmanagement.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    private Context mContext;
    public List<Banner> banners;


    public PagerAdapter(Context mContext, List<Banner> banners) {
        this.mContext = mContext;
        this.banners = banners;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.image_slider_layout_item, null);
        ImageView imageView = (ImageView)inflate.findViewById(R.id.iv_auto_image_slider);
        TextView textView = (TextView)inflate.findViewById(R.id.tv_auto_image_slider);
        Picasso.get().load(banners.get(position).getImage()).into(imageView);
        textView.setText(banners.get(position).getDescription());

        container.addView(inflate);
        return inflate;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }
}



