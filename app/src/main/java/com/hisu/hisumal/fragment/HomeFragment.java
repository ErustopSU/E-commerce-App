package com.hisu.hisumal.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.hisu.hisumal.R;
import com.hisu.hisumal.adapter.ProductAdapter;
import com.hisu.hisumal.adapter.ProductTopSellAdapter;
import com.hisu.hisumal.adapter.SliderAdapter;
import com.hisu.hisumal.model.Product;
import com.hisu.hisumal.model.ProductSpecification;
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

        ProductSpecification specification = new ProductSpecification(
            1, "AMD Ryzen 5 5600H", "Windows 11 SL 64 Bit","DDR4 8GB (1 x 8GB) 3200MHz; 2 slots, up to 32GB",
                "Geforce GTX 1650 4GB", "15.6 FullHD (1920x1080). 144Hz, IPS Panel",
                "512GB SSD NVMe M.2 PCIe Gen 3x4","1x3.5mm Audio Jack, 1xHDMI, 1xRJ45 for LAN Gigabit Ethernet, 1xUSB 3.1 Type-C, 3xUSB 3.2 Gen 1 Type-A",
                "RGB Backlight","4 Cell, 57Whr",2.2f

        );

        products.add(new Product(products.size() + 1, R.drawable.laptop_1,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "The MSI 15.6\" GE Series GE66 Raider Dragonshield Gaming Laptop combines both performance and aesthetics for gamers who want bit of flair. The bottom edge of the palm rest and its keys support customizable RGB lighting, which users can set to fit their style. This limited edition features the MSI Dragonshield logo and a sci-fi spaceship-themed design. Specs-wise, it's equipped with a 2.4 GHz Intel Core i9-10980HK eight-core processor, 32GB of DDR4 RAM, a 1TB NVMe PCIe M.2 SSD, and an NVIDIA GeForce RTX 2070 SUPER graphics card. These enable the system to quickly load applications, multitask efficiently, and play graphically demanding games. MSI has also paired the hardware with a 1080p Full HD 300 Hz IPS display. Connectivity options include USB Type-A and Type-C, HDMI, Mini DisplayPort, SD media cards, Gigabit LAN, Wi-Fi 6, and Bluetooth 5.1. Other integrated features includes a webcam, microphones, speakers, and a combo audio jack. The operating system installed is Windows 10 Home.",
                "ASUS", 20000000, 15, true, 4.5, 2, specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_2,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, false, 5, 1, specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_3,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, true, 4.5,3, specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_4,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, false, 4.25, 2,specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_5,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, true, 5, 2,specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_6,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, false, 5, 2,specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_7,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, true, 5,2, specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_8,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, false, 5, 2,specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_9,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, true, 5, 2,specification));
        products.add(new Product(products.size() + 1, R.drawable.laptop_10,
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                "ASUS", 20000000, 15, false, 5,2, specification));

        return products;
    }

    private List<SliderItem> initSliderItems() {
        List<SliderItem> items = new ArrayList<>();

        items.add(new SliderItem(R.drawable.slider_1));
        items.add(new SliderItem(R.drawable.slider_2));
        items.add(new SliderItem(R.drawable.slider_3));

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