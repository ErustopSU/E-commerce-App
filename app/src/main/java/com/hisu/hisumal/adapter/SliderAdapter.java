package com.hisu.hisumal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hisu.hisumal.R;
import com.hisu.hisumal.model.SliderItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderItem> sliderItems;

    public SliderAdapter(List<SliderItem> sliderItems) {
        this.sliderItems = sliderItems;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_banner_slider_item, container, false);

        SliderItem sliderItem = sliderItems.get(position);

        ImageView sliderImg = view.findViewById(R.id.slider_item_img);
        sliderImg.setImageResource(sliderItem.getImageResource());

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if (sliderItems == null || sliderItems.isEmpty()) return 0;
        return sliderItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View) object);
    }
}