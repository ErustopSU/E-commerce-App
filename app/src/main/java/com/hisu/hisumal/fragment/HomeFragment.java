package com.hisu.hisumal.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import com.hisu.hisumal.R;
import com.hisu.hisumal.adapter.ProductAdapter;
import com.hisu.hisumal.adapter.ProductTopSellAdapter;
import com.hisu.hisumal.adapter.SliderAdapter;
import com.hisu.hisumal.model.Product;
import com.hisu.hisumal.model.SliderItem;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {

    private static final long DELAY_TIME = 3 * 1000; //secs

    //Banner Slider
    private ViewPager2 bannerViewPager;
    private CircleIndicator3 circleIndicator;
    private List<SliderItem> sliderItems;
    private Handler mBannerSliderHandler;
    private Runnable mBannerRunnable;

    //Top sell list
    private RecyclerView topSellRecyclerView;

    //Product List
    private RecyclerView productRecyclerView;
    private List<Product> productList;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initFragmentUI(view);

        //Banner Slider
        initSliderViewPager();
        startBannerSlider();
        addAutoRunEventForViewPager();

        //Product Top Sell
        topSellRecyclerView.setAdapter(new ProductTopSellAdapter(getActivity(), productList));
        topSellRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        //Product List
        productRecyclerView.setAdapter(productAdapter);
        productRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    private void initFragmentUI(View view) {
        bannerViewPager = view.findViewById(R.id.banner_view_pager);
        circleIndicator = view.findViewById(R.id.banner_slider_indicator);
        sliderItems = initSliderItems();

        mBannerSliderHandler = new Handler();
        mBannerRunnable = () -> {
            if (bannerViewPager.getCurrentItem() == sliderItems.size() - 1)
                bannerViewPager.setCurrentItem(0);
            else
                bannerViewPager.setCurrentItem(bannerViewPager.getCurrentItem() + 1);
        };

        topSellRecyclerView = view.findViewById(R.id.top_sell_recycler_view);

        productRecyclerView = view.findViewById(R.id.list_product_recycler_view);
        productList = initProductData();
        productAdapter = new ProductAdapter(getActivity(), productList);
    }

    //Todo: change method to fetch data from database or api
    private List<Product> initProductData() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(products.size() + 1, R.drawable.laptop_1,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, true, 4.5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_2,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, false, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_3,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, true, 4.5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_4,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, false, 4.25));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, true, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, false, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, true, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, false, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, true, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, false, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, true, 5));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", "Laptop", 20000000, 15, false, 5));

        return products;
    }

    private List<SliderItem> initSliderItems() {
        List<SliderItem> items = new ArrayList<>();

        items.add(new SliderItem(R.drawable.slider_1));
        items.add(new SliderItem(R.drawable.slider_2));
        items.add(new SliderItem(R.drawable.slider_7));

        return items;
    }

    private void initSliderViewPager() {
        SliderAdapter sliderAdapter = new SliderAdapter(sliderItems);
        bannerViewPager.setAdapter(sliderAdapter);
        circleIndicator.setViewPager(bannerViewPager);
    }

    public void startBannerSlider() {
        mBannerSliderHandler.postDelayed(mBannerRunnable, DELAY_TIME);
    }

    private void addAutoRunEventForViewPager() {
        bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mBannerSliderHandler.removeCallbacks(mBannerRunnable);
                startBannerSlider();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerSliderHandler.removeCallbacks(mBannerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        startBannerSlider();
    }
}